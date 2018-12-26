package com.bank.honest.model.service;

import com.bank.honest.model.dao.ProductRepository;
import com.bank.honest.model.dto.ProductDTO;
import com.bank.honest.model.entity.Product;
import com.bank.honest.model.entity.enums.Currency;
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
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(Pageable pageable) {
        List<Product> products = productRepository.findAll(pageable).getContent();
        List<ProductDTO> result = new ArrayList<>();
        for (Product product : products) {
            result.add(product.toDTO());
        }
        return result;
    }

    @Transactional(readOnly = true)
    public ProductDTO findProduct(Long productId) {
        Product product = productRepository.findOne(productId);
        ProductDTO result = product.toDTO();
        return result;
    }

    public ProductDTO findProduct(String name) {
        Product product = productRepository.findProductByCurrency(Currency.valueOf(name));
        ProductDTO result = product.toDTO();
        return result;
    }


    @Transactional
    public void deleteProducts(Long[] toDelete) {
        for (long id : toDelete)
            productRepository.delete(id);
    }
}
