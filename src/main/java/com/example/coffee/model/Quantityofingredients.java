package com.example.coffee.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "quantityofingredients")
@Data
@NoArgsConstructor
public class Quantityofingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level_of_coffee")
    private String levelOfCoffee;

    @Column(name = "level_of_water")
    private String levelOfWater;

    @Column(name = "level_of_milk")
    private String levelOfMilk;

    @Column(name = "level_of_cream")
    private String levelOfCream;
}
