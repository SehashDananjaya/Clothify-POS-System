package repository;

import repository.custom.ProductRepository;
import repository.custom.impl.ProductRepositoryImpl;
import util.RepositoryType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){};

    public static DaoFactory getInstance() {
        return instance != null ? instance : new DaoFactory();
    }

    public <T extends SuperRepository>T getRepositoryType(RepositoryType repositoryType){

        switch (repositoryType){
            case PRODUCT: return (T) new ProductRepositoryImpl();
            //case ITEM: return (T) new ItemRepositoryImpl();
            //case ORDER:return (T) new OrderRepositoryImpl();
        }

        return null;
    };

}
