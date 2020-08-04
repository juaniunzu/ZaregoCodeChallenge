package com.example.zaregocodechallenge.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    private String name;
    private String lastName;
    private String phoneNumber;

}
