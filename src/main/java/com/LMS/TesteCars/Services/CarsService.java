package com.LMS.TesteCars.Services;

import com.LMS.TesteCars.Dao.CarsDao;
import com.LMS.TesteCars.Dtos.CarsDto;
import com.LMS.TesteCars.Entitys.Cars;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class CarsService {
    @Autowired
    private CarsDao carsDao;

    @Autowired
    private SeqGeneratorService seqGeneratorService;

    @Autowired
    private ConnectToApiService connectToApiService;

    public List<Cars> getAllCars() { return carsDao.findAll(); }

    public CarsDto carsHaveId (CarsDto carsDto) {
        if (carsDto.get_id() == null) {
            ObjectId id = new ObjectId();
            carsDto.set_id(id.toString());
            return carsDto;
        }
        return carsDto;
    }
    public HttpEntity<CarsDto> requestToCreateCars (CarsDto carsDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(carsDto, headers);
    }
    private void validationDataCarsDto (CarsDto carsDto) throws Exception {
        if (carsDto.get_id() == null) {
            throw new Exception("O campo Id se encontra nulo.");
        }
        if (carsDao.existsById(carsDto.get_id())) {
            throw new Exception("O campo Id deve ser único. Por favor preencha com um id distinto.");
        }
        if (carsDto.getTitle() == null) {
            throw new Exception("O campo Título em branco. Por favor preencha todos os campos.");
        }
    }
    public Cars save (CarsDto carsDto) throws Exception {
        Cars cars = new Cars();

        this.validationDataCarsDto(carsDto);

        cars.set_id(carsDto.get_id());
        cars.setAge(carsDto.getAge());
        cars.setPrice(carsDto.getPrice());
        cars.setBrand(carsDto.getBrand());
        cars.setTitle(carsDto.getTitle());
        cars.setAge(carsDto.getAge());
        carsDao.save(cars);
        return cars;
    }

}
