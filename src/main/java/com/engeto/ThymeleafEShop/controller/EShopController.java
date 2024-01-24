package com.engeto.ThymeleafEShop.controller;

import com.engeto.ThymeleafEShop.model.Product;
import com.engeto.ThymeleafEShop.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class EShopController {
    private ProductServiceImpl productServiceimpl;

    public EShopController(ProductServiceImpl productServiceimpl) {
        this.productServiceimpl = productServiceimpl;
    }

    @GetMapping("/")
    public String loadAllAvailableItems(Model model) throws SQLException {
        model.addAttribute("allprodlist", productServiceimpl.getAllProducts());
        return "index";
    }

    @GetMapping("/showDetail/{id}")
    public String loadPruductById(@PathVariable(value = "id") int id, Model model) throws SQLException {
        model.addAttribute("product", productServiceimpl.getById(id));
        return "productDetail";
    }

    @GetMapping("/addnew")
    public String addNewProduct(Model model) throws SQLException {
        Product product = new Product();
        model.addAttribute("product", product);
        return "newProduct";
    }

    @PostMapping("/save")
    public String saveItem(@ModelAttribute("product") Product product) throws SQLException {
        productServiceimpl.save(product);
        return "redirect:/";
    }


    @GetMapping("/deleteNotForSale")
    public String deleteOutOfSaleItems() throws SQLException {
        productServiceimpl.deleteAllNotForSale();
        return "redirect:/";
    }

    @GetMapping("FormForUpdateProductPrice")
    public String updatePriceById(Model model) throws SQLException {
        Product updatedProd = new Product();
        model.addAttribute("product", updatedProd);
        return "updateProductPrice";
    }

    @PostMapping("/updatePrice")
    public String updateProduct(@ModelAttribute("product") Product product) throws SQLException {
        productServiceimpl.updatePrice(product);
        return "redirect:/";
    }
}
