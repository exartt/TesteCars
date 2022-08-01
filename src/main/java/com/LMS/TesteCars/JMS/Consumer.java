package com.LMS.TesteCars.JMS;

import com.LMS.TesteCars.Entitys.Cars;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Consumer {

    private final JmsTemplate jmsTemplateTopic;

    @Autowired
    private Produtor produtor;

    @JmsListener(destination = "${activemq.name}", subscription = "signQueue")
    public void onReceiverQueue(String mensagem) {
        log.info(mensagem);
        try {
            Gson gson = new Gson();
            Cars cars = gson.fromJson(mensagem, Cars.class);
            jmsTemplateTopic.convertAndSend(mensagem, "O carro de id: " + cars.get_id() + " foi cadastrado.");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @JmsListener(destination = "${activemq.topic-name}", containerFactory = "jmsFactoryTopic", subscription = "signTopic")
    public void listen(String mensagem) {
        log.info(mensagem);
        try {
            Gson gson = new Gson();
            Cars cars = gson.fromJson(mensagem, Cars.class);
            jmsTemplateTopic.convertAndSend(mensagem, "O carro de id: " + cars.get_id() + " foi cadastrado.");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
