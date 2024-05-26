package com.dmarquinar.evaluation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;

}