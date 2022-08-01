package com.LMS.TesteCars.Dao;

import com.LMS.TesteCars.Entitys.Cars;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarsDao extends MongoRepository<Cars, String> {
}
