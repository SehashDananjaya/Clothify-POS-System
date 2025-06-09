package repository.custom.impl;

import entity.ProductEntity;
import repository.custom.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Boolean addProduct(ProductEntity productEntity) {
        System.out.println("Product Added");
        return null;
    }
}
