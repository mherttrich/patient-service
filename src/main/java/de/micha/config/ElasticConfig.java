package de.micha.config;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
class ElasticConfig {
    @Bean
    public @Named("index") String  getIndex(){
        return "appatwork";
    }
    @Bean
    public @Named("type") String getType(){
        return "patient";
    }


    @Bean
    Client elasticService() throws UnknownHostException {
        return TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9200));
    }

}

