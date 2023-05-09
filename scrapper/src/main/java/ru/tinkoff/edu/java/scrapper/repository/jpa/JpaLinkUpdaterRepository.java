package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;

import java.util.List;

@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public interface JpaLinkUpdaterRepository extends JpaRepository<Link, Long> {
    @Query(value = "update link set updated_at = now() where link.id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void updateLinkById(@Param("id") long id);

    @Transactional
    @Query(value = "select chat_id from chat_link, link where chat_link.link_id = link.id and link.id = ?1", nativeQuery = true)
    List<Long> getChats(long linkId);
}
