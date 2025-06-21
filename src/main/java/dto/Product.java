package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private Integer id;
    private String name;
    private String category;
    private String size;
    private Double Price;
    private Integer quantityOnHand;
    private String imgPath;
    private String supplier;
    private String description;

    public Product(String name,String category,String size,Double price,Integer quantityOnHand,String imgPath,String supplier,String description){
        this.name=name;
        this.category=category;
        this.size=size;
        this.Price=price;
        this.quantityOnHand=quantityOnHand;
        this.imgPath=imgPath;
        this.supplier=supplier;
        this.description=description;
    }
}
