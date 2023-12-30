package com.example.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class AzureBlobConfig {

    @Value("${spring.cloud.azure.storage.connection.string}")
    private String connectionString;

    @Value("${spring.cloud.azure.storage.container.name}")
    private String containerName;

    @Bean
    public BlobServiceClient clobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient() {
        return clobServiceClient()
                .getBlobContainerClient(containerName);
    }
}