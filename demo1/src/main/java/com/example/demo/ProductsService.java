package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private static     ProductsRepository ProductsRepository;
    public ProductsService(ProductsRepository ProductsRepository) {
        this.ProductsRepository = ProductsRepository;
    }

    public static List<Products> getAllProducts() {
        return ProductsRepository.findAll();
    }

    public static Products getProductsById(int id) {
        return ProductsRepository.findById(id).orElseThrow(()-> new IllegalStateException("Product not found with id: " + id));
    }


    public void insertProducts(Products products) {
        ProductsRepository.save(products);
    }


    public void updateProducts(Products products) {
        ProductsRepository.save(products);
    }

    public void deleteProducts(int id) {
        ProductsRepository.deleteById(id);
    }
}
