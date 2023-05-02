package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;

import java.util.List;

public interface JpaLinkUpdaterRepository extends JpaRepository<Link, Long> {
    @Query(value = "update link set updated_at = now() where link.id = :id", nativeQuery = true)
    @Modifying
    void updateLinkById(@Param("id") long id);

    @Query(value = "select chat_id from chat_link, link where chat_link.link_id = link.id and link.id = ?1", nativeQuery = true)
    List<Long> getChats(long linkId);
}
