package com.gainsight.tbs.rabbitMqConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig
{
    @Bean
    Queue studentQueue()
    {
        return new Queue("bootcamper-omar.student.queue",false);
    }

    @Bean
    Queue employeeQueue()
    {
        return new Queue("bootcamper-omar.employee.queue",false);
    }

    @Bean
    Queue genericQueue()
    {
        return new Queue("bootcamper-omar.generic.queue",false);
    }

    @Bean
    TopicExchange topicExchange()
    {
        return new TopicExchange("bootcamper-omar.exchange");
    }

    @Bean
    Binding bindingTostudentQueue(Queue studentQueue, TopicExchange topicExchange)
    {
        return BindingBuilder.bind(studentQueue)
                .to(topicExchange)
                .with("test.student");
    }

    @Bean
    Binding bindingToemployeeQueue(Queue employeeQueue,TopicExchange topicExchange)
    {
        return BindingBuilder.bind(employeeQueue)
                .to(topicExchange)
                .with("test.employee");
    }

    @Bean
    Binding bindingTogenericQueue(Queue genericQueue,TopicExchange topicExchange)
    {
        return BindingBuilder.bind(genericQueue)
                .to(topicExchange)
                .with("test.*");
    }

    @Bean
    MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
