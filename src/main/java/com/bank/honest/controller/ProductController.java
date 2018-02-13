package com.bank.honest.controller;

import com.bank.honest.model.dto.ProductDTO;
import com.bank.honest.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by User on 2/11/2018.
 */
@RestController
public class ProductController {

    final int DEFAULT_GROUP_ID = -1;
    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected ProductService productService;

    @RequestMapping("/products")
    public List<ProductDTO> products(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<ProductDTO> result = productService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping("/product/{id}")
    public ProductDTO product(
            @PathVariable(value = "id") long productId) {
        ProductDTO result = productService.findProduct(productId);
        return result;
    }

    @RequestMapping("/products/{name}")
    public ProductDTO accountByName(
            @PathVariable(value = "name") String name) {
        ProductDTO result = productService.findProducts(name);
        return result;
    }


}
