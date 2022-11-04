package com.gainsight.tbs.Services;

import com.gainsight.tbs.DTO.DTOconverter;
import com.gainsight.tbs.DTO.TicketDTO;
import com.gainsight.tbs.POJO.ticket;
import com.gainsight.tbs.repository.PostRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLayerImpl implements ServiceLayer{

    @Autowired
    PostRepo repo;

    @Autowired
    DTOconverter dTOconverter;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<TicketDTO> fetchTicket() {
        logger.info("Hii fetching all the information of the tickets from database");
        List<ticket> tickets= repo.findAll();
        List<TicketDTO> ticketDTOS = dTOconverter.ticketListToTicketDTOListConvertr(tickets);
        return ticketDTOS;
    }

    @Override
    public TicketDTO putTicket(TicketDTO ticketDTO) {
        logger.info("Hii saving the information of the tickets to the database");
        ticket Ticket = new ticket();
        Ticket = dTOconverter.ticketDTOToTicketConvertr(ticketDTO);
        repo.save(Ticket);
        return ticketDTO;
    }


}
