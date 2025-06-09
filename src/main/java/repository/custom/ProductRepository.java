package repository.custom;

import entity.ProductEntity;
import repository.SuperRepository;

public interface ProductRepository extends SuperRepository {
    Boolean addProduct(ProductEntity productEntity);
}
