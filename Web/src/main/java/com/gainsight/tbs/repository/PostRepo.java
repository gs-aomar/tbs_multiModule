package com.gainsight.tbs.repository;

import com.gainsight.tbs.POJO.ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<ticket,Integer>
{

}
