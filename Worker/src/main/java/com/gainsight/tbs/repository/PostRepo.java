package com.gainsight.tbs.repository;

import com.gainsight.tbs.DTO.TicketDTO;
import com.gainsight.tbs.POJO.ticket;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostRepo extends MongoRepository<ticket,Integer>
{
        public ticket findByTicketNo(int ticketNo);
}
