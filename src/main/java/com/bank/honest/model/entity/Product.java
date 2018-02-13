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

    public ProductDTO toDTO() {
        return ProductDTO.builder()
                .name(name)
                .buyCourse(buyCourse)
                .sellCourse(sellCourse)
                .description(description)
                .build();
    }

    public static Product fromDTO(ProductDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .buyCourse(dto.getBuyCourse())
                .sellCourse(dto.getSellCourse())
                .description(dto.getDescription())
                .build();
    }
}
