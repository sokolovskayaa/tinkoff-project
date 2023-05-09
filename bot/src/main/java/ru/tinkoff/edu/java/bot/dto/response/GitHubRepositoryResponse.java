package ru.tinkoff.edu.java.bot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubRepositoryResponse(
    int id,
    String name,
    @JsonProperty("full_name") String fullName,
    OwnerResponse owner,
    @JsonProperty("updated_at") OffsetDateTime updatedAt
) {
    public record OwnerResponse(String name, String email, String login, int id)
    { }
}
