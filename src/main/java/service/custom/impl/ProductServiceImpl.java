package service.custom.impl;

import dto.Product;
import entity.ProductEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductRepository;
import service.custom.ProductService;
import util.CrudUtil;
import util.RepositoryType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    //ProductRepository productRepository = DaoFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);
    @Override
    public Boolean addProduct(Product product) throws SQLException {

        ProductEntity productEntity= new ModelMapper().map(product,ProductEntity.class);

       return CrudUtil.execute("INSERT INTO product (name,category,size,price,quantityOnHand,imgPath,supplier,description) VALUES (?,?,?,?,?,?,?,?)",
                productEntity.getName(),productEntity.getCategory(),productEntity.getSize(),productEntity.getPrice(),productEntity.getQuantityOnHand(),productEntity.getImgPath(),productEntity.getSupplier(),productEntity.getDescription());

    }

    @Override
    public Boolean updateProduct(Product product) throws SQLException {

        ProductEntity productEntity= new ModelMapper().map(product,ProductEntity.class);

        String sql = "UPDATE Product SET name=?, category=?, size=?, price=?, quantityOnHand=?, imgPath=?, supplier=?, description=? WHERE id=?";


        Boolean result = CrudUtil.execute(
                sql,
                productEntity.getName(),
                productEntity.getCategory(),
                productEntity.getSize(),
                productEntity.getPrice(),
                productEntity.getQuantityOnHand(),
                productEntity.getImgPath(),
                productEntity.getSupplier(),
                productEntity.getDescription(),
                productEntity.getId()
        );

        return result;


    }

    @Override
    public Product searchById(String id) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE id=?", id);
        if (resultSet.next()) {
            return new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getString("size"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantityOnHand"),
                    resultSet.getString("imgPath"),
                    resultSet.getString("supplier"),
                    resultSet.getString("description")
            );
        }




        return null;
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }
}
