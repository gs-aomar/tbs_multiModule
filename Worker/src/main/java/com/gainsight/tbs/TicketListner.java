package com.gainsight.tbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class TicketListner
{
    public static void main(String[] args)
    {
        SpringApplication.run(TicketListner.class, args);
    }
}
