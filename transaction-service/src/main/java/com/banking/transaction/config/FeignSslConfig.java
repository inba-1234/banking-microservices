package com.banking.transaction.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.net.ssl.SSLContext;

@Configuration
public class FeignSslConfig {

    @Value("${mtls.client.key-store}")
    private Resource keyStore;

    @Value("${mtls.client.key-store-password}")
    private String keyStorePassword;

    @Value("${mtls.client.trust-store}")
    private Resource trustStore;

    @Value("${mtls.client.trust-store-password}")
    private String trustStorePassword;

    @Bean
    public Client feignClient() throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore.getURL(), keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                .build();

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();

        return new ApacheHttpClient(httpClient);
    }
}
