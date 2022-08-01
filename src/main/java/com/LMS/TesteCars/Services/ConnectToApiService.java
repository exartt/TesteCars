package com.LMS.TesteCars.Services;

import com.LMS.TesteCars.Dtos.CarsDto;
import com.LMS.TesteCars.Entitys.Cars;
import com.LMS.TesteCars.Enum.RequestType;
import com.LMS.TesteCars.JMS.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ConnectToApiService {

    private final Logger logger = LoggerFactory.getLogger(ConnectToApiService.class);
    @Autowired
    private LogService logService;

    @Autowired
    private Consumer consumer;
    private static final String URI = "http://api-test.bhut.com.br:3000/api/cars";

    public List<Cars> connectionExternalApiToGetListCars() {
        RestTemplate restTemplate = new RestTemplate();
        List<Cars> carsList = restTemplate.getForObject(URI, ArrayList.class);
        logService.saveExibicaoListagem();
        return carsList;
    }

    public void connectionExternalApiToCreateCar (HttpEntity<CarsDto> request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CarsDto> response = restTemplate.postForEntity(URI, request, CarsDto.class);
        this.responseStatus(response);
    }

    private void responseStatus (ResponseEntity<CarsDto> response) {
        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Carro cadastrado com sucesso");
            logService.saveCadastroNovoCarro(Objects.requireNonNull(response.getBody()).get_id());
        } else {
            logger.error("Erro ao cadastrar carro.");
            logService.saveLogError(RequestType.Create, response.getStatusCode().toString());
        }
    }

}
