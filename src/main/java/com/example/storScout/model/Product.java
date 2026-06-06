package com.example.storScout.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Product {
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String imageUrl;
}
