package de.micha.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.micha.exception.ElasticException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;

@Repository
public class ElasticDao {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticDao.class);

    @Inject
    private Client elasticClient;

    @Inject
    private ObjectMapper mapper;

    public <T> Optional<T> get(String index, String type, String id, Class<T> clazz){

        GetResponse response = elasticClient.prepareGet(index, type, id).get();
        if (response.isSourceEmpty()){
            return  Optional.empty();
        }
        try {
            T document = mapper.readValue(response.getSourceAsString(), clazz);
            return Optional.of(document);
        } catch (IOException e) {
            throw new ElasticException("Failed to load document.", e);
        }
    }


    public <T> void save(String index, String type, T document) {
        try {
            String json = mapper.writeValueAsString(document);

            IndexResponse response = elasticClient.prepareIndex(index, type)
                    .setSource(json)
                    .get();
            if (response.status() != RestStatus.CREATED){
                throw new RuntimeException("RestStatus:" + response.status());
            }
        } catch (JsonProcessingException e) {
            LOG.error("failed to save {}",  type , e);
            throw new ElasticException("failed to save " + type,  e);
        }


    }
}
