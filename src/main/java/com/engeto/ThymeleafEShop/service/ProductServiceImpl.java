package com.engeto.ThymeleafEShop.service;

import com.engeto.ThymeleafEShop.model.Product;
import com.engeto.ThymeleafEShop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository prodRepo;

    public ProductServiceImpl(ProductRepository prodRepo) {
        this.prodRepo = prodRepo;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        return prodRepo.getAll();
    }

    @Override
    public Product getById(int id) throws SQLException {
        Product product = prodRepo.findById(id);
        if (product != null) {
            return product;
        } else {
            throw new RuntimeException("Fastener not found for id= " + id);
        }
    }

    @Override
    public void save(Product product) throws SQLException {
        prodRepo.save(product);
    }

    public Product updateFastener(int id, Model model) throws SQLException {
        Product product = prodRepo.findById(id);
        return product;
    }

    @Override
    public void updatePrice(Product product) throws SQLException {
        prodRepo.updatePrice(product);
    }

    @Override
    public void deleteAllNotForSale() throws SQLException {
        prodRepo.deleteNotForSale();
    }
}
