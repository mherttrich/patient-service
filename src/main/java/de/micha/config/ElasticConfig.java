package de.micha.config;


import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
class ElasticConfig {

    @Value("${elastic.host}")
    String elastichost;

    @Value("${elastic.port}")
    int elasticport;

    private Client client;

    @Bean
    Client elasticService() throws UnknownHostException {
        client =  new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
    }

    @PreDestroy
    void shutdown(){
        if (client != null) {
            client.close();
        }
    }

}

