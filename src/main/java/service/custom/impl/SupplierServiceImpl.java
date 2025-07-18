package service.custom.impl;

import dto.Employee;
import dto.Supplier;
import entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import service.custom.SupplierService;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public Boolean addSupplier(Supplier supplier) throws SQLException {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        String sql = "INSERT INTO supplier (name,company,EmailAddress) VALUES (?,?,?)";
        return CrudUtil.execute(sql,
                supplierEntity.getName(),
                supplierEntity.getCompany(),
                supplierEntity.getEmailAddress());
    }

    @Override
    public Boolean updateSupplier(Supplier supplier) throws SQLException {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);

        String sql = "UPDATE supplier SET name= ?,company= ?,emailAddress= ? WHERE id =?";

        return CrudUtil.execute(sql,
                supplierEntity.getName(),
                supplierEntity.getCompany(),
                supplierEntity.getEmailAddress(),
                supplierEntity.getId());

    }

    @Override
    public Boolean deleteSupplier(String id) throws SQLException {
        String SQL ="DELETE FROM Supplier WHERE id = ?";

        return CrudUtil.execute(SQL, id);

    }

    @Override
    public Employee searchByIdSupplier(String id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return List.of();
    }
}
