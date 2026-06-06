package com.example.storScout.config;

import com.example.storScout.client.StoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    @Bean
    public StoreClient storeClient(RestClient.Builder builder) {
        RestClient client = builder
                .baseUrl(System.getProperty("FAKE_STORE_API_BASE_URL"))
                .build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(client);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(StoreClient.class);
    }
}
