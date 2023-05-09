package ru.tinkoff.edu.java.bot.configuration;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {

    private final ApplicationConfig config;

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(config.queueProperties().queue())
            .withArgument(
                "x-dead-letter-exchange",
                config.queueProperties().exchange() + ".dlx"
            )
            .build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(config.queueProperties().exchange());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
            .to(exchange())
            .with(config.queueProperties().key());
    }

    @Bean
    public FanoutExchange dlx() {
        return new FanoutExchange(config.queueProperties().exchange() + ".dlx");
    }

    @Bean
    public Queue dlq() {
        return QueueBuilder
            .durable(config.queueProperties().queue() + ".dlq")
            .build();
    }

    @Bean
    public Binding dlb() {
        return BindingBuilder.bind(dlq()).to(dlx());
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    public ClassMapper classMapper() {
        var classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.scrapper.dto.request.*");
        classMapper.setIdClassMapping(Map.of(
            "ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest",
            LinkUpdateRequest.class
        ));
        return classMapper;
    }

    @Bean
    public MessageConverter converter(final ClassMapper classMapper) {
        var jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }
}
