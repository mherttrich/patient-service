package de.micha.config;


import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
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

        return new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));


    }

}

