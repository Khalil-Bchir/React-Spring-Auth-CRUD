package com.restaurant.restaurant.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
    @Size(min = 1, max = 20)
    private String name;

//    @NotEmpty
    private String description;

    @Min(0)
    private double price;

    @ManyToOne
//    @NotNull(message = "Menu should not be null")
    private Menu menu;

    private String image;

}
