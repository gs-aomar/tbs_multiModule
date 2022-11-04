package com.gainsight.tbs.Services;

import com.gainsight.tbs.DTO.DTOconverter;
import com.gainsight.tbs.DTO.TicketDTO;
import com.gainsight.tbs.POJO.Status;
import com.gainsight.tbs.POJO.ticket;
import com.gainsight.tbs.repository.PostRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public TicketDTO checkValidityOfTicket(TicketDTO ticketDTO)
    {

        ticket checkTicket = repo.findByTicketNo(ticketDTO.getTicketNo());
        if (checkTicket == null)
        {
            putTicket(ticketDTO);
        }
        else
        {
            if (checkTicket.getStatus() == Status.INPROGRESS)
            {
                if (ticketDTO.getStatus() == Status.INPROGRESS)
                {
                    logger.info("Ticket is already Inprogress please wait...");
                }
                else if(ticketDTO.getStatus() == Status.BOOKED)
                {
                    logger.info("Ticket is Booked successfully.");
                }
                else
                {
                    logger.info("Sorry Ticket is Failed to book");
                }
                putTicket(ticketDTO);
            }
            else if (checkTicket.getStatus() == Status.BOOKED)
            {
                if (ticketDTO.getStatus() == Status.INPROGRESS)
                {
                    logger.info("Sorry Ticket is already Booked.");
                }
                else if(ticketDTO.getStatus() == Status.BOOKED)
                {
                    logger.info("Ticket is already Booked.");
                    putTicket(ticketDTO);
                }
                else
                {
                    logger.info("Ticket is already Booked.Can't Book again");
                }
            }
            else
            {
                if (ticketDTO.getStatus() == Status.INPROGRESS)
                {
                    logger.info("Sorry Ticket is Failed to book");
                }
                else if(ticketDTO.getStatus() == Status.BOOKED)
                {
                    logger.info("Sorry Ticket is Failed to book");
                }
                else
                {
                    logger.info("Ticket is already Failed to book");
                    putTicket(ticketDTO);
                }
            }
        }
        return ticketDTO;
    }


}
