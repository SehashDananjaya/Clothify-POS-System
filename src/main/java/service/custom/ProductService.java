package service.custom;

import dto.Product;
import entity.ProductEntity;

import java.util.List;

public interface ProductService {

    Boolean addProduct(Product product);
    Boolean updateProduct(Product product);
    Product searchById (String id);
    List<Product>getAll();
}
