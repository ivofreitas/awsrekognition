package br.com.api.gurgel.apirecognition.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RekognitionConfig {

    private ProfileCredentialsProvider profileCredentialsProvider() {
        return new ProfileCredentialsProvider();
    }

    @Bean
    public AmazonRekognition getAmazonRekognition() {
        return AmazonRekognitionClientBuilder.standard()
                .withCredentials(profileCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
