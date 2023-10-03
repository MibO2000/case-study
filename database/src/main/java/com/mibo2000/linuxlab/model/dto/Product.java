package com.mibo2000.linuxlab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : MibO2000
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BasicDummyId {
    String title;
    String description;
    Long price;
    Double discountPercentage;
    Double rating;
    Long stock;
    String brand;
    String category;
    String thumbnail;
    List<String> images;
}
