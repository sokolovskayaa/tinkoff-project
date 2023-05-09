//package ru.tinkoff.edu.java.scrapper.repository.jpa;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
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
//public class JpaLinkRepositoryTest extends IntegrationEnvironment {
//
//    @Autowired
//    private JpaLinkRepository linkRepository;
//    @Autowired
//    private JpaChatRepository chatRepository;
//
//    @Test
//    @Rollback
//    @Transactional
//    public void addLinkTest() {
//        linkRepository.addLink("test1");
//        Assertions.assertFalse(linkRepository.findAllByUrl("test1").isEmpty());
//    }
//
//    @Test
//    @Rollback
//    @Transactional
//    public void removeLinkTest() {
//        linkRepository.addLink("test1");
//        linkRepository.removeLinkById(linkRepository.findAllByUrl("test1").get(0).getId());
//        Assertions.assertTrue(linkRepository.findAllByUrl("test1").isEmpty());
//    }
//
//    @Test
//    @Rollback
//    @Transactional
//    public void findAllLinksInChatTest() {
//        linkRepository.addLink("test1");
//        linkRepository.addLink("test2");
//        linkRepository.addLink("test3");
//        Chat chat = new Chat();
//        chat.setId(1L);
//        chatRepository.save(chat);
//        Link link1 = linkRepository.findAllByUrl("test1").get(0);
//        Link link2 = linkRepository.findAllByUrl("test2").get(0);
//        Link link3 = linkRepository.findAllByUrl("test3").get(0);
//        linkRepository.addChatLink(1L, link1.getId());
//        linkRepository.addChatLink(1L, link2.getId());
//        linkRepository.addChatLink(1L, link3.getId());
//        Assertions.assertEquals(3, linkRepository.findAllLinksInChat(1L).size());
//    }
//
//    @Test
//    @Rollback
//    @Transactional
//    public void RemoveChatLinkTest() {
//        linkRepository.addLink("test1");
//        linkRepository.addLink("test2");
//        linkRepository.addLink("test3");
//        Chat chat = new Chat();
//        chat.setId(1L);
//        chatRepository.save(chat);
//        Link link1 = linkRepository.findAllByUrl("test1").get(0);
//        Link link2 = linkRepository.findAllByUrl("test2").get(0);
//        Link link3 = linkRepository.findAllByUrl("test3").get(0);
//        linkRepository.addChatLink(1L, link1.getId());
//        linkRepository.addChatLink(1L, link2.getId());
//        linkRepository.addChatLink(1L, link3.getId());
//        linkRepository.removeChatLink(1L, link1.getId());
//        Assertions.assertEquals(2, linkRepository.findAllLinksInChat(1L).size());
//    }
//
//    @Test
//    @Rollback
//    @Transactional
//    public void getChatLinksByUrlAndChatIdTest() {
//        linkRepository.addLink("test1");
//        linkRepository.addLink("test2");
//        linkRepository.addLink("test3");
//        Chat chat = new Chat();
//        chat.setId(1L);
//        chatRepository.save(chat);
//        Link link1 = linkRepository.findAllByUrl("test1").get(0);
//        Link link2 = linkRepository.findAllByUrl("test2").get(0);
//        Link link3 = linkRepository.findAllByUrl("test3").get(0);
//        linkRepository.addChatLink(1L, link1.getId());
//        linkRepository.addChatLink(1L, link2.getId());
//        linkRepository.addChatLink(1L, link3.getId());
//        Assertions.assertFalse(linkRepository.getLinkIdByUrlAndChatId(1L, "test1").isEmpty());
//    }
//
//    @Test
//    @Rollback
//    @Transactional
//    public void getChatLinksByLinkIdTest() {
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
//        Assertions.assertFalse(linkRepository.getChatLinksByLinkId(link1.getId()).isEmpty());
//    }
//}
