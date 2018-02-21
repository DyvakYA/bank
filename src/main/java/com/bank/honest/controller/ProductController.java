package com.bank.honest.controller;

import com.bank.honest.model.dto.ProductDTO;
import com.bank.honest.model.entity.Product;
import com.bank.honest.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 2/11/2018.
 */
@RestController
public class ProductController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> products(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<ProductDTO> result = productService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDTO product(@PathVariable(value = "id") long productId) {
        ProductDTO result = productService.findProduct(productId);
        return result;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestParam String name,
                                       @RequestParam Long buyCourse,
                                       @RequestParam Long sellCourse,
                                       @RequestParam String description) {

        Product product = Product.builder()
                .name(name)
                .buyCourse(buyCourse)
                .sellCourse(sellCourse)
                .description(description)
                .build();
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id", required = false) Long id) {
        productService.deleteProducts(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id[]", required = false) Long[] ids) {
        productService.deleteProducts(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestParam String name,
                                       @RequestParam Long buyCourse,
                                       @RequestParam Long sellCourse,
                                       @RequestParam String description) {

        Product product = Product.builder()
                .name(name)
                .buyCourse(buyCourse)
                .sellCourse(sellCourse)
                .description(description)
                .build();
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products/search/{pattern}", method = RequestMethod.GET)
    public ProductDTO productByPattern(@PathVariable(value = "pattern") String pattern) {
        ProductDTO result = productService.findProduct(pattern);
        return result;
    }
}
