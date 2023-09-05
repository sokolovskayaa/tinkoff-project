package ru.tinkoff.edu.java.scrapper.dto.repository.jdbc;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tinkoff.edu.java.scrapper.dto.repository.ILink;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Link implements ILink {
    private Long id;
    private String url;
    private OffsetDateTime updatedAt;
}
