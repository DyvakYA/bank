package com.bank.honest.model.entity;

import com.bank.honest.model.dto.ProductDTO;
import lombok.*;

import javax.persistence.*;

/**
 * Created by User on 2/11/2018.
 */

@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="product_name")
    private String name;

    @Column(name="buy_course")
    private long buyCourse;

    @Column(name="sell_course")
    private long sellCourse;

    @Column(name="description")
    private String description;

    public Product(String name, long buyCourse, long sellCourse, String description) {
        this.name = name;
        this.buyCourse = buyCourse;
        this.sellCourse = sellCourse;
        this.description = description;
    }

    public ProductDTO toDTO() {
        return new ProductDTO(name, buyCourse, sellCourse, description);
    }

    public static Product fromDTO(ProductDTO dto) {
        return new Product(dto.getName(), dto.getBuyCourse(), dto.getSellCourse(), dto.getDescription());
    }
}
