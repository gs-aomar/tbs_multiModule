package com.gainsight.tbs.POJO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "TBS")
@Data
public class ticket
{
    @Id
    private int ticketNo;
    private Status status;
    private String time;
    private Type type;
}
