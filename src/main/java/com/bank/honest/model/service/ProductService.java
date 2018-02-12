package com.bank.honest.model.service;

import com.bank.honest.model.dao.ProductRepository;
import com.bank.honest.model.dto.ProductDTO;
import com.bank.honest.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/11/2018.
 */
@Service
public class ProductService extends BaseService {

    @Autowired
    private ProductRepository productRepository;


    public List<ProductDTO> findAll(Pageable pageable) {
        List<Product> products = productRepository.findAll(pageable).getContent();
        List<ProductDTO> result = new ArrayList<>();
        for (Product product : products) {
            result.add(product.toDTO());
        }
        return result;
    }

    @Transactional
    public boolean addProduct(String name, long buyCourse, long sellCourse, String description) {
        if (productRepository.existsByName(name))
            return false;

        Product product = Product.builder()
                .name(name)
                .buyCourse(buyCourse)
                .sellCourse(sellCourse)
                .description(description)
                .build();
        productRepository.save(product);

        return true;
    }

    @Transactional(readOnly = true)
    public ProductDTO findProduct(long productId) {
        Product product = productRepository.findOne(productId);
        ProductDTO result = product.toDTO();
        return result;
    }
}
