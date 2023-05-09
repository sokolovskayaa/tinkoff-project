package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
        @NotNull String test,
        @NotNull Scheduler scheduler,
        @NotNull @Name("database-access-type") AccessType accessType,
        @NotNull @Name("use-queue") boolean useQueue,
        @NotNull @Name("queue-properties") QueueProperties queueProperties
) {
    public record Scheduler(Duration interval) {
    }

    public enum AccessType {
        JDBC, JPA, JOOQ
    }

    public record QueueProperties(
            String exchange,
            String queue,
            String key,
            String dlx
    ) {
    }
}
