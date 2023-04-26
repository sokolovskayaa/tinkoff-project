package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;

import java.util.List;
@Repository
@Primary

public interface JpaLinkUpdaterRepository extends JpaRepository<Link, Long> {
    @Query(value = "update link set updated_at = now() where link.id = ?1", nativeQuery = true)
    void updateLinkById(long id);

    @Query(value = "select chat_id from chat_link, link where chat_link.link_id = link.id and link.id = ?1", nativeQuery = true)
    List<Long> getChats(long linkId);
}
