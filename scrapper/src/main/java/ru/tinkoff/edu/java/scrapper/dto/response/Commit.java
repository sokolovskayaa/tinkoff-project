package ru.tinkoff.edu.java.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Commit(
        String sha,
        @JsonProperty("node_id")
        String nodeId) {
    @Override
    public String toString() {
        return sha;
    }
}