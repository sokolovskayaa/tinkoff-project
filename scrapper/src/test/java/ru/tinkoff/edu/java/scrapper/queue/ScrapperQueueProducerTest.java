//package ru.tinkoff.edu.java.scrapper.queue;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
//import ru.tinkoff.edu.java.scrapper.updater.ScrapperQueueProducer;
//import ru.tinkoff.edu.java.scrapper.updater.Updater;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Slf4j
//public class ScrapperQueueProducerTest extends IntegrationEnvironment {
//
//    @Autowired
//    private Updater updater;
//
//    @Test
//    public void ScrapperQueueProducerBeanTest() {
//        Assertions.assertTrue(updater instanceof ScrapperQueueProducer);
//    }
//}
