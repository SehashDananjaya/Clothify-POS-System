package service.custom;

import dto.Product;
import entity.ProductEntity;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends SuperService {

    Boolean addProduct(Product product) throws SQLException;
    Boolean updateProduct(Product product) throws SQLException;
    Product searchById (String id) throws SQLException;
    List<Product>getAll();

}
