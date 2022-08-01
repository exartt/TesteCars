package com.LMS.TesteCars.Dao;

import com.LMS.TesteCars.Entitys.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface LogDao extends MongoRepository<Log, Integer> {
}
