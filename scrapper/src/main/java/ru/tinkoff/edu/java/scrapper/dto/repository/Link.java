package ru.tinkoff.edu.java.scrapper.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    private int id;
    private String url;
    private OffsetDateTime updatedAt;
}
