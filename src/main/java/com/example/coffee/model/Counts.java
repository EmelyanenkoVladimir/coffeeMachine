package com.example.coffee.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "counts")
@Data
@NoArgsConstructor
public class Counts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count_of_order")
    private Integer countOfOrder;

    @Column(name = "count_of_coffee")
    private Integer countOfCoffee;

    @Column(name = "count_of_water")
    private Integer countOfWater;

    @Column(name = "count_of_milk")
    private Integer countOfMilk;

    @Column(name = "count_of_cream")
    private Integer countOfCream;

    @Column(name = "count_of_filter")
    private Integer countOfFilter;
}
