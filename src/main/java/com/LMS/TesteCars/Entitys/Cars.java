package com.LMS.TesteCars.Entitys;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "Cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cars implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "cars_sequence";

    @Id
    @NotNull
    private String _id;
    @NotNull
    private String title;
    private String brand;
    private String price;
    private Integer age;

    @Override
    public String toString() {
        return String.format("Informações do carro a ser criado. -> " +
                        "_Id=%s, Title=%s, Brand=%s, Price=%s, Age=%s}",
                get_id(), getTitle(), getBrand(), getPrice(), getAge());
    }

}
