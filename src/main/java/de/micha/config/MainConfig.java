package de.micha.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.inject.Named;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@ComponentScan(basePackages = {"de.micha"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*test.*"))
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@EnableTransactionManagement
@PropertySource("${props.path:classpath:}/application.properties")
@Import({DatabaseConfig.class})
class MainConfig implements ResourceLoaderAware {


    private ResourceLoader resourceLoader;

    private static final String USER_TEST_DATA_FILE_PATH = "classpath:user-test-data.csv";

    @Bean
    public Resource testDataResource() {
        return resourceLoader.getResource(USER_TEST_DATA_FILE_PATH);
    }

    @Bean
    public @Named("index") String  getIndex(){
        return "appatwork";
    }
    @Bean
    public @Named("type") String getType(){
        return "patient";
    }


    @Bean Client elasticService() throws UnknownHostException {
       return TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
                //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9200));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
