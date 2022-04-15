package com.example.coffee.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "levelofingredients")
@Data
@NoArgsConstructor
public class LevelOfIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level_of_coffee")
    private Integer levelOfCoffee;

    @Column(name = "level_of_water")
    private Integer levelOfWater;

    @Column(name = "level_of_milk")
    private Integer levelOfMilk;

    @Column(name = "level_of_cream")
    private Integer levelOfCream;
}
