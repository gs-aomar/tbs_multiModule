package com.gainsight.tbs.DTO;

import com.gainsight.tbs.POJO.ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOconverter
{
    public TicketDTO ticketToTicketDTOConvertr(ticket ticket)
    {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketNo(ticket.getTicketNo());
        ticketDTO.setStatus(ticket.getStatus());
        ticketDTO.setTime(ticket.getTime());
        ticketDTO.setType(ticket.getType());
        return ticketDTO;
    }

    public ticket ticketDTOToTicketConvertr(TicketDTO ticketDTO)
    {
        ticket Ticket = new ticket();
        Ticket.setTicketNo(ticketDTO.getTicketNo());
        Ticket.setStatus(ticketDTO.getStatus());
        Ticket.setTime(ticketDTO.getTime());
        Ticket.setType(ticketDTO.getType());
        return Ticket;
    }

    public List<TicketDTO> ticketListToTicketDTOListConvertr(List<ticket> tickets)
    {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (com.gainsight.tbs.POJO.ticket ticket:tickets)
        {
            TicketDTO ticketDTO = ticketToTicketDTOConvertr(ticket);
            ticketDTOS.add(ticketDTO);
        }
        return ticketDTOS;
    }
}
