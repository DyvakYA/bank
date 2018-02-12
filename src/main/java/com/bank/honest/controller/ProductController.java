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

import static com.bank.honest.controller.BaseController.ITEMS_PER_PAGE;

/**
 * Created by User on 2/11/2018.
 */
@RestController
public class ProductController {

    @Autowired
    protected ProductService productService;

    @RequestMapping("/products")
    public List<ProductDTO> products(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<ProductDTO> result = productService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping("/products/{id}")
    public ProductDTO product(
            @PathVariable(value = "id") long productId) {
        ProductDTO result = productService.findProduct(productId);
        return result;
    }
}
