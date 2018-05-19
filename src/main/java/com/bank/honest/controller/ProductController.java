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

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 2/11/2018.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDTO> products(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<ProductDTO> result = productService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDTO product(@PathVariable(value = "id") long productId) {
        ProductDTO result = productService.findProduct(productId);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody ProductDTO dto) {

        Product product = Product.builder()
                .currency(dto.getCurrency())
                .buyCourse(dto.getBuyCourse())
                .sellCourse(dto.getSellCourse())
                .description(dto.getDescription())
                .build();
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id[]") Long[] ids) {
        productService.deleteProducts(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ProductDTO dto) {

        Product product = Product.builder()
                .currency(dto.getCurrency())
                .buyCourse(dto.getBuyCourse())
                .sellCourse(dto.getSellCourse())
                .description(dto.getDescription())
                .build();
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{pattern}", method = RequestMethod.GET)
    public ProductDTO productByPattern(@PathVariable(value = "pattern") String pattern) {
        ProductDTO result = productService.findProduct(pattern);
        return result;
    }
}
