package ru.tinkoff.edu.java.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StackoverflowQuestions(
    @JsonProperty("items") List<StackoverflowQuestion> questions
) {
}
