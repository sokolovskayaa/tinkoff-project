package ru.tinkoff.edu.java.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record StackoverflowQuestion(
        @JsonProperty("accepted_answer_id") int acceptedAnswerId,
        @JsonProperty("answer_count") int answerCount,
        @JsonProperty("last_activity_date") OffsetDateTime lastActivityDate){

};
