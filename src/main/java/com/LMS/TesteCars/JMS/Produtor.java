package com.LMS.TesteCars.JMS;

import com.LMS.TesteCars.Dtos.CarsDto;
import com.LMS.TesteCars.Entitys.Cars;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Produtor {

    private final JmsTemplate jmsTemplate;

    @Value("${activemq.name}")
    private String topic;

    public void send(Cars cars){
        Gson gson = new Gson();
        String jsonPerson = gson.toJson(cars);
        jmsTemplate.convertAndSend(topic, jsonPerson);
    }
}
