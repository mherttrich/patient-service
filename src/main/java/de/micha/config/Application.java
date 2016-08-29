package de.micha.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@Import(MainConfig.class)
public class Application extends SpringBootServletInitializer {

    // to run with tomcat-embed-core
    // java -jar foo-1.0-SNAPSHOT.jar

    // pass VMarg -Dtomcat.logdir=/tmp
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}