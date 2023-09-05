//package ru.tinkoff.edu.java.scrapper.repository.jpa;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
//import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Chat;
//import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Slf4j
//public class JpaLinkUpdaterRepositoryTest extends IntegrationEnvironment {
//    @Autowired
//    private JpaLinkUpdaterRepository repository;
//
//    @Autowired
//    private JpaLinkRepository linkRepository;
//    @Autowired
//    private JpaChatRepository chatRepository;
//
//
//    @Test
//    @Rollback
//    @Transactional
//    public void getAllLinksTest() {
//        linkRepository.addLink("test1");
//        linkRepository.addLink("test2");
//        linkRepository.addLink("test3");
//        Assertions.assertEquals(3, repository.findAll().size());
//    }
//
//    @Test
//    @Rollback
//    @Transactional
//    public void getChatsTest() {
//        linkRepository.addLink("test1");
//        linkRepository.addLink("test2");
//        linkRepository.addLink("test3");
//        Chat chat = new Chat();
//        chat.setId(1L);
//        chatRepository.save(chat);
//        chat.setId(2L);
//        chatRepository.save(chat);
//        Link link1 = linkRepository.findAllByUrl("test1").get(0);
//        Link link2 = linkRepository.findAllByUrl("test2").get(0);
//        linkRepository.addChatLink(1L, link1.getId());
//        linkRepository.addChatLink(2L, link1.getId());
//        linkRepository.addChatLink(1L, link2.getId());
//        Assertions.assertEquals(2, repository.getChats(link1.getId()).size());
//        Assertions.assertEquals(1, repository.getChats(link2.getId()).size());
//    }
//
//    @Test
//    @Rollback
//    @Transactional
//    public void updateLinkTest() {
//        linkRepository.addLink("test1");
//        repository.updateLinkById(linkRepository.findAllByUrl("test1").get(0).getId());
//        Assertions.assertEquals(1, repository.findAll().size());
//    }
//}
