package ru.tinkoff.edu.java.scrapper.dto.repository.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatLink {
    private Long chatId;
    private Long linkId;
}
