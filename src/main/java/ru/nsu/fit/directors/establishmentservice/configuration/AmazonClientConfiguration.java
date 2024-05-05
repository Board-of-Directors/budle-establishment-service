package ru.nsu.fit.directors.establishmentservice.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.fit.directors.establishmentservice.configuration.properties.AmazonProperties;

@Configuration
public class AmazonClientConfiguration {
    @Bean
    public AmazonS3 amazonClient(AmazonProperties amazonProperties) {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(amazonProperties))
            .withEndpointConfiguration(
                new AmazonS3ClientBuilder.EndpointConfiguration(
                    amazonProperties.getServiceEndpoint(),
                    amazonProperties.getSigningRegion()
                )
            )
            .build();
    }
}
