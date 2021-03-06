package de.micha.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.micha.domain.ElasticType;
import de.micha.exception.ElasticException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Repository
public class ElasticDao {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticDao.class);

    @Inject
    private Client elasticClient;

    @Inject
    private ObjectMapper mapper;

    public <T extends ElasticType> Optional<T> get(String index, String type, String id, Class<T> clazz){

        GetResponse response = elasticClient.prepareGet(index, type, id).get();

        if (response.isSourceEmpty()){
            return  Optional.empty();
        }
        try {
            T document = mapper.readValue(response.getSourceAsString(), clazz);
            //want the autogenerated elastic id (later might not search by id)
            document.setId(response.getId());
            return Optional.of(document);
        } catch (IOException e) {
            throw new ElasticException("Failed to map document.", e);
        }
    }


    public <T> List<T> search(String index, String type,  Map<String,String> args, Class<T> clazz) throws IOException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();


        args.forEach((k, v) ->{
            queryBuilder.must(termQuery(k, v.toLowerCase()));
        });


        SearchRequestBuilder requestBuilder = elasticClient.prepareSearch(index)
                .setTypes(type)
                .setQuery(queryBuilder);


        SearchResponse response = requestBuilder
                .setFrom(0)
                .setSize(100)
                .get();
        //System.out.println("hits: " +response.getHits().totalHits());

        ArrayList<T> result = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            T document = mapper.readValue(hit.getSourceAsString(), clazz);
            result.add(document);
        }
        return result;

    }


    public <T> void save(String index, String type, T document) {
        try {
            String json = mapper.writeValueAsString(document);

            IndexResponse response = elasticClient.prepareIndex(index, type)
                    .setSource(json)
                    .get();
            if (response.status() != RestStatus.CREATED){
                throw new ElasticException("RestStatus:" + response.status());
            }
        } catch (JsonProcessingException e) {
            LOG.error("failed to save {}",  type , e);
            throw new ElasticException("failed to save " + type,  e);
        }
    }
}
