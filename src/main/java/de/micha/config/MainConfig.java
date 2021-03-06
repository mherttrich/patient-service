package de.micha.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
@ComponentScan(basePackages = {"de.micha"}, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*test.*"))
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})

//@EnableTransactionManagement
@PropertySource("${props.path:classpath:}/application.yml")
@Import({ElasticConfig.class})
class MainConfig implements ResourceLoaderAware {


    private ResourceLoader resourceLoader;

    private static final String USER_TEST_DATA_FILE_PATH = "classpath:user-test-data.csv";


    @Bean
    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    public Resource testDataResource() {
        return resourceLoader.getResource(USER_TEST_DATA_FILE_PATH);
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
