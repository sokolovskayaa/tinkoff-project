package ru.tinkoff.edu.java.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record GitHubRepositoryResponse(
    int id,
    String name,
    @JsonProperty("full_name") String fullName,
    OwnerResponse owner,
    @JsonProperty("updated_at") OffsetDateTime updatedAt,
    @JsonProperty("pushed_at") OffsetDateTime pushedAt) {
    @Override
    public String toString() {
        return "GitHubRepositoryResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", fullName='" + fullName + '\'' +
            ", owner=" + owner +
            ", updatedAt=" + updatedAt +
            '}';
    }

    public record OwnerResponse(String name, String email, String login, int id) { }
}
