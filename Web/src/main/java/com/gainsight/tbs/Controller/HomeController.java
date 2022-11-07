package com.gainsight.tbs.Controller;

import com.gainsight.tbs.DTO.TicketDTO;
import com.gainsight.tbs.POJO.Type;
import com.gainsight.tbs.Services.ServiceLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/record")
public class HomeController
{


    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ServiceLayer serviceLayer;

    @Autowired
    private TopicExchange topicExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("")
    public List<TicketDTO> getCollection()
    {
        return serviceLayer.fetchTicket();
    }

    @PostMapping("")
    public TicketDTO putCollection(@RequestBody TicketDTO ticketDTO)
    {
        return serviceLayer.putTicket(ticketDTO);
    }

    @PostMapping("/bootcamper-omar.student")
    public String sendMsgToStudentQueue(@RequestBody TicketDTO ticketDTO)
    {
        ticketDTO.setType(Type.student);
//        serviceLayer.putTicket(ticketDTO);
        rabbitTemplate.convertAndSend(topicExchange.getName(),"test.student",ticketDTO);
        logger.info("Successfully send msg to Student Queue and Generic Queue.");
        return "Successfully send msg to Student Queue.";
    }

    @PostMapping("/bootcamper-omar.employee")
    public String sendMsgToemployeeQueue(@RequestBody TicketDTO ticketDTO)
    {
        ticketDTO.setType(Type.employee);
//        serviceLayer.putTicket(ticketDTO);
        rabbitTemplate.convertAndSend(topicExchange.getName(),"test.employee",ticketDTO);
        logger.info("Successfully send msg to Employee Queue.");
        return "Successfully send msg to Employee Queue and Generic Queue.";
    }

    @PostMapping("/bootcamper-omar.generic")
    public String sendMsgTogenericQueue(@RequestBody TicketDTO ticketDTO)
    {
        ticketDTO.setType(Type.generic);
//        serviceLayer.putTicket(ticketDTO);
        rabbitTemplate.convertAndSend(topicExchange.getName(),"test.*",ticketDTO);
        logger.info("Successfully send msg to Generic Queue.");
        return "Successfully send msg to Generic Queue.";
    }
}

/*
MVC architecture

create @service class inside service class call repo methods
when using same url , move it at class level instead of method level
make ticketId as id, shouldn't allow you to insert duplicate records with same ticketId
implement slf4j

 */