package com.dataman.base.filestore;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Mongo client (modern driver) - created only when {@code dataman.mongo.host} is configured. */
@Configuration
@EnableConfigurationProperties(MongoFileProperties.class)
public class MongoFileConfiguration {

    @Bean(destroyMethod = "close")
    @ConditionalOnProperty(prefix = "dataman.mongo", name = "host")
    public MongoClient fileMongoClient(MongoFileProperties p) {
        MongoClientSettings.Builder b = MongoClientSettings.builder()
                .applyToClusterSettings(c -> c.hosts(List.of(new ServerAddress(p.getHost(), p.getPort()))));
        if (p.getUsername() != null && !p.getUsername().isBlank()) {
            b.credential(MongoCredential.createCredential(p.getUsername(), p.getAuthSource(),
                    p.getPassword() == null ? new char[0] : p.getPassword().toCharArray()));
        }
        return MongoClients.create(b.build());
    }
}
