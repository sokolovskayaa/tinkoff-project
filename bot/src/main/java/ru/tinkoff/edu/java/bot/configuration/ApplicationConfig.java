package ru.tinkoff.edu.java.bot.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull String test,
    @NotNull @Name("use-queue") boolean useQueue,
    @NotNull @Name("queue-properties") QueueProperties queueProperties
) {
    public record QueueProperties(
        String exchange,
        String queue,
        String key
    ) {
    }
}
