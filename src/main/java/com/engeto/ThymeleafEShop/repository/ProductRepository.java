package com.engeto.ThymeleafEShop.repository;

import com.engeto.ThymeleafEShop.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/engeto",
            "user1",
            "Klacicek456?1"
    );
    Statement statement = connection.createStatement();

    public ProductRepository() throws SQLException {
    }

    public List<Product> getAll() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM fastener");

        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setPartNumber(resultSet.getString("partnumber"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setIsForSale(resultSet.getInt("isforsale"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setImage(resultSet.getString("image"));
            products.add(product);
        }
        return products;
    }

    public Product findById(int id) throws SQLException {
        String query = "SELECT * FROM fastener WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        Product product = new Product();
        if (resultSet.next()) {
            product.setId(resultSet.getInt("id"));
            product.setPartNumber(resultSet.getString("partnumber"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setIsForSale(resultSet.getInt("isforsale"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setImage(resultSet.getString("image"));
        }
        return product;
    }

    public void save (Product product) throws SQLException {
        int saleInfo;
        if (product.getIsForSale()==true){ saleInfo = 1;
        } else {saleInfo = 0;}

        String query = "INSERT INTO fastener (partnumber,name,description,isforsale,price,image) VALUES ('"
                + product.getPartNumber() + "', '"
                + product.getName() + "', '"
                + product.getDescription() + "', '"
                + saleInfo + "', '"
                + product.getPrice() + "', '"
                + product.getImage() + "')";
        statement.executeUpdate(query);
    }

    public void updatePrice(Product product) throws SQLException {
        String query = "UPDATE fastener SET "
                + "price = '" + product.getPrice() + "'WHERE id = " + product.getId();
        statement.executeUpdate(query);
    }

    public void deleteNotForSale() throws SQLException{
        String query = "DELETE FROM fastener WHERE isforsale = 0";
        statement.executeUpdate(query);
}

}
