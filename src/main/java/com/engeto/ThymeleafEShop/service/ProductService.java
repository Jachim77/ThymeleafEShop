package com.engeto.ThymeleafEShop.service;

import com.engeto.ThymeleafEShop.model.Product;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts() throws SQLException;

    Product getById(int id) throws SQLException;

    void save(Product product) throws SQLException;

    void updatePrice(Product product) throws SQLException;

    void deleteAllNotForSale() throws SQLException;
}
