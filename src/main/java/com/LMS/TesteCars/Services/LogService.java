package com.LMS.TesteCars.Services;

import com.LMS.TesteCars.Dao.CarsDao;
import com.LMS.TesteCars.Dao.LogDao;
import com.LMS.TesteCars.Entitys.Log;
import com.LMS.TesteCars.Enum.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private SeqGeneratorService seqGeneratorService;
    public List<Log> getAllCars() { return logDao.findAll(); }

    public void saveExibicaoListagem () {
        Log log = new Log();
        log.setId(seqGeneratorService.incrementSequenceNumber(Log.SEQUENCE_NAME));
        log.setData_hora(LocalDateTime.now());
        log.setRequestType(RequestType.List.label);
        logDao.save(log);
    }

    public void saveCadastroNovoCarro (String carId) {
        Log log = new Log();
        log.setId(seqGeneratorService.incrementSequenceNumber(Log.SEQUENCE_NAME));
        log.setData_hora(LocalDateTime.now());
        log.setCarId(carId);
        log.setRequestType(RequestType.Create.label);
        log.setMensagem("Cadastro efetuado com sucesso!");
        logDao.save(log);
    }

    public void saveLogError (RequestType type, String error) {
        Log log = new Log();
        log.setId(seqGeneratorService.incrementSequenceNumber(Log.SEQUENCE_NAME));
        log.setData_hora(LocalDateTime.now());
        log.setRequestType(type.label);
        if (type.label.equals("Listagem")) {
            log.setMensagem("Um erro inesperado ocorreu ao trazer lista de carros. Erro: " + error);
        } else {
            log.setMensagem("Um erro inesperado ocorreu ao cadastrar o carro. Erro: " + error);
        }
        logDao.save(log);
    }

}
