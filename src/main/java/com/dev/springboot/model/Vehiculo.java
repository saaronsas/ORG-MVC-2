package com.dev.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue
    private Integer id;
    private String matricula;
    private String color;
    @OneToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

}