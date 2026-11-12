package com.intercar.intercarmotion.Entity;

import jakarta.persistence.*;

import java.util.List;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    private String password;
    private String email;
    private String favourite;

    //relacion 1 usuario a 1 o muchos coches
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="car_brand_id" , nullable = false)
    private List<Brand> brands;


}
