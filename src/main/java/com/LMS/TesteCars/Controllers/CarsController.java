package com.LMS.TesteCars.Controllers;


import com.LMS.TesteCars.Dao.CarsDao;
import com.LMS.TesteCars.Dtos.CarsDto;
import com.LMS.TesteCars.Entitys.Cars;
import com.LMS.TesteCars.Entitys.Log;
import com.LMS.TesteCars.Enum.RequestType;
import com.LMS.TesteCars.JMS.Produtor;
import com.LMS.TesteCars.Services.CarsService;
import com.LMS.TesteCars.Services.ConnectToApiService;
import com.LMS.TesteCars.Services.LogService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class CarsController {

    @Autowired
    private CarsDao carsDao;
    @Autowired
    private CarsService carsService;
    @Autowired
    private ConnectToApiService connectionExternalApiToGetListCars;
    @Autowired
    private LogService logService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Produtor produtor;

    @RequestMapping(value = "/listcars", method = RequestMethod.GET)
    public List<Cars> carsList () throws RuntimeException {
        try {
            return connectionExternalApiToGetListCars.connectionExternalApiToGetListCars();
        } catch (Exception e) {
            logService.saveLogError(RequestType.List, e.getMessage());
            throw new RuntimeException("Erro inesperado ao buscar a listagem de carros. " + e.getMessage());
        }
    }

    @RequestMapping(value = "/createcar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void save (@RequestBody CarsDto carsDto) throws Exception {
        try {
            CarsDto cars = carsService.carsHaveId(carsDto);
            produtor.send(new Cars(carsDto.get_id(), carsDto.getTitle(), carsDto.getBrand(), carsDto.getPrice(), carsDto.getAge()));
            connectionExternalApiToGetListCars.connectionExternalApiToCreateCar(carsService.requestToCreateCars(cars));
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao criar novo registro de carro. Erro: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public List<Log> getLog () {
        try {
            return logService.getAllCars();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao trazer os logs. Erro: " + e.getMessage());
        }
    }

}
