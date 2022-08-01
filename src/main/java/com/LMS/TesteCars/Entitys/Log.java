package com.LMS.TesteCars.Entitys;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "Logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Transient
    public static final String SEQUENCE_NAME = "logs_sequence";

    @Id
    @NotNull
    private long id;
    @NotNull
    private LocalDateTime data_hora;
    @ManyToOne
    private String carId;
    private String mensagem;

    private String requestType;
}
