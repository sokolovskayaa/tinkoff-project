package ru.tinkoff.edu.java.scrapper.dto.repository.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tinkoff.edu.java.scrapper.dto.repository.ILink;

import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "link")
public class Link implements ILink {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
