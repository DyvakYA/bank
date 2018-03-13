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
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="product_name", nullable = false)
    private Currency currency;

    @Column(name="buy_course", nullable = true)
    private long buyCourse;

    @Column(name="sell_course", nullable = true)
    private long sellCourse;

    @Column(name="description", nullable = true)
    private String description;

    public ProductDTO toDTO() {
        return ProductDTO.builder()
                .currency(currency)
                .buyCourse(buyCourse)
                .sellCourse(sellCourse)
                .description(description)
                .build();
    }

    public static Product fromDTO(ProductDTO dto) {
        return Product.builder()
                .currency(dto.getCurrency())
                .buyCourse(dto.getBuyCourse())
                .sellCourse(dto.getSellCourse())
                .description(dto.getDescription())
                .build();
    }
}
