package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity {

    private Integer id;
    private String name;
    private String category;
    private String size;
    private Double Price;
    private Integer quantityOnHand;
    private String imgPath;
    private String supplier;
    private String description;
}
