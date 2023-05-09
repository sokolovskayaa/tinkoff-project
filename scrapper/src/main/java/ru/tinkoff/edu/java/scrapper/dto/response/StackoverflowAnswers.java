package ru.tinkoff.edu.java.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StackoverflowAnswers(
    @JsonProperty("items")
    List<StackoverflowAnswer> answers
) {
    @Override
    public String toString() {
        return "StackoverflowAnswers{"
            + "answers="
            + answers
            + '}';
    }

    public record StackoverflowAnswer(
        @JsonProperty("answer_id")
        int answerId
    ) {
        @Override
        public String toString() {
            return "StackoverflowAnswer{" +
                "answerId=" + answerId +
                '}';
        }
    }

}
