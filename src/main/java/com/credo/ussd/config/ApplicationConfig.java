package com.credo.ussd.config;

import com.credo.ussd.client.FlutterWaveClient;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static com.credo.ussd.constants.Api.FLW_BASE_URL;
import static com.credo.ussd.utils.Util.getAuthHeader;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationConfig {
    @Value("${flw-secret-key}")
    String FLW_AUTH;

    @Bean
    public FlutterWaveClient flutterWaveClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(FLW_BASE_URL)
                .defaultHeaders(header -> header.addAll(getAuthHeader(FLW_AUTH)))
                .build();

        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build()
                .createClient(FlutterWaveClient.class);
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper;
    }
}
