//package ru.tinkoff.edu.java.scrapper.configuration.access;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
//import ru.tinkoff.edu.java.scrapper.service.LinkService;
//import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
//import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Slf4j
//public class JpaAccessConfigTest extends IntegrationEnvironment {
//    @Autowired
//    LinkService service;
//
//    @Test
//    public void jpaAccessConfigTest() {
//        Assertions.assertTrue(service instanceof JpaLinkService);
//    }
//}
