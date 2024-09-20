package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.configs;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    private String BASE_URL = "http://localhost:9000";

    @Bean
    @Qualifier(value = "employees")
    public RestClient getRestClient(){
        RestClient client = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)    // denotes that the data/content received should be of json type
                .build();
        return client;
    }

}
