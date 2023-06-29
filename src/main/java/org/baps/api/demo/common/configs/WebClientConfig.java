package org.baps.api.demo.common.configs;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Supplier;

@Configuration
public class WebClientConfig {

    @Value("${client.url}")
    private String clientUrl;

    private final Supplier<HttpHeaders> defaultHeaders = () -> {
        final MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
        headersMap.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        return HttpHeaders.readOnlyHttpHeaders(headersMap);
    };

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient client1(final WebClient.Builder webClientBuilder) {
        return webClientBuilder
            .clone()
            .baseUrl(clientUrl)
            .defaultHeaders(httpHeaders -> httpHeaders.addAll(defaultHeaders.get()))
            .build();
    }

}
