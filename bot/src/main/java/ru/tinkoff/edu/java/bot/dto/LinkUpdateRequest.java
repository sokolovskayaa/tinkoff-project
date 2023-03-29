package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkUpdateRequest {
    private Long id;
    private String url;
    private String description;
    private List<Long> tgChatIds;
}
