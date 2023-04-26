package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Chat;
@Repository
@Primary

public interface JpaChatRepository extends JpaRepository<Chat, Long>  {

}
