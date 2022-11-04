package com.gainsight.tbs.DTO;

import com.gainsight.tbs.POJO.Status;
import com.gainsight.tbs.POJO.Type;
import lombok.Data;


@Data
public class TicketDTO
{
    private int ticketNo;
    private Status status;
    private String time;
    private Type type;

}
