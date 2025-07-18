package service.custom;

import dto.Employee;
import dto.Supplier;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService extends SuperService {
    Boolean addSupplier(Supplier supplier) throws SQLException;
    Boolean updateSupplier(Supplier supplier) throws SQLException;
    Boolean deleteSupplier(String id) throws SQLException;
    Employee searchByIdSupplier (String id);
    List<Employee> getAll();
}
