package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Chat;
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public interface JpaChatRepository extends JpaRepository<Chat, Long> {

}
