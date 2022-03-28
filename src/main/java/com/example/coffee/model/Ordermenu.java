package com.example.coffee.model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ordermenu")
@Data
@NoArgsConstructor
public class Ordermenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_of_order")
    private String typeOfOrder;

    @Column(name = "quantity_of_coffee")
    private Integer quantityOfCoffee;

    @Column(name = "quantity_of_water")
    private Integer quantityOfWater;

    @Column(name = "quantity_of_milk")
    private Integer quantityOfMilk;

    @Column(name = "quantity_of_cream")
    private Integer quantityOfCream;

    @OneToMany(mappedBy = "orderMenu",fetch = FetchType.LAZY)
    private List<Orders> ordersList;
}
