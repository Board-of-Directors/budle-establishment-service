package ru.nsu.fit.directors.establishmentservice.configuration.properties;

import com.amazonaws.auth.AWSCredentials;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "amazon.s3")
public class AmazonProperties implements AWSCredentials {
    private String AWSAccessKeyId;
    private String AWSSecretKey;
    private String serviceEndpoint;
    private String signingRegion;
}
