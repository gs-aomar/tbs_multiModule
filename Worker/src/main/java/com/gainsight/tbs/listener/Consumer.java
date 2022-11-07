package com.gainsight.tbs.listener;

import com.gainsight.tbs.DTO.TicketDTO;
import com.gainsight.tbs.Services.ServiceLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServiceLayer serviceLayer ;

    @RabbitListener(queues = "bootcamper-omar.student.queue")
    void msgReceiveFromStudentQueue(TicketDTO ticketDTO)
    {
        logger.info("[bootcamper-omar.student.queue] ,{}, {}]",ticketDTO.getType(),ticketDTO.getTicketNo());
        serviceLayer.checkValidityOfTicketAndPut(ticketDTO);
    }

    @RabbitListener(queues = "bootcamper-omar.employee.queue")
    void msgReceiveFromEmployeeQueue(TicketDTO ticketDTO)
    {
        logger.info("[bootcamper-omar.employee.queue] ,{}, {}]",ticketDTO.getType(),ticketDTO.getTicketNo());
        serviceLayer.checkValidityOfTicketAndPut(ticketDTO);
    }

    @RabbitListener(queues = "bootcamper-omar.generic.queue")
    void msgReceiveFromGenericQueue(TicketDTO ticketDTO)
    {
        logger.info("[bootcamper-omar.generic.queue] ,{}, {}]",ticketDTO.getType(),ticketDTO.getTicketNo());
        serviceLayer.checkValidityOfTicketAndPut(ticketDTO);
    }
}
