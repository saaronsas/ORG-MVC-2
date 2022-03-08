package com.dev.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dev.springboot.model.Marca;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Serie {

    @Id
    @GeneratedValue
    private Integer id;
    private String nombre_serie;

    @OneToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

}