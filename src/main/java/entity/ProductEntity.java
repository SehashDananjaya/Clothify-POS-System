package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity {
    private Integer id;
    private String Name;
    private String Category;
    private Double Price;
}
