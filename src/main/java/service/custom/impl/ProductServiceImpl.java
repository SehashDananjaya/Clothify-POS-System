package service.custom.impl;

import dto.Product;
import entity.ProductEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductRepository;
import service.custom.ProductService;
import util.RepositoryType;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository = DaoFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);
    @Override
    public Boolean addProduct(Product product) {
        ProductEntity productEntity= new ModelMapper().map(product,ProductEntity.class);
        productRepository.addProduct(productEntity);
        return null;
    }

    @Override
    public Boolean updateProduct(Product product) {
        return null;
    }

    @Override
    public Product searchById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }
}
