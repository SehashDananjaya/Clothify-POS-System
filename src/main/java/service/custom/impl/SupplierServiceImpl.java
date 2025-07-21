package service.custom.impl;

import dto.Employee;
import dto.Supplier;
import entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import service.custom.SupplierService;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Supplier searchByIdSupplier(String id) {
        return null;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");
        ArrayList<SupplierEntity> supplierEntityArrayList = new ArrayList<>();

        while (resultSet.next()){
            supplierEntityArrayList.add(new SupplierEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("company"),
                    resultSet.getString("emailAddress")
            ));
        }

        ArrayList<Supplier> supplierArrayList = new ArrayList<>();

        supplierEntityArrayList.forEach(supplierEntity -> {
            supplierArrayList.add(new ModelMapper().map(supplierEntity,Supplier.class));
        });
        return supplierArrayList;
    }

    public List<String> getSupplierNames() throws SQLException {

        List<Supplier> supplierList = getAll();

        ArrayList<String> supplierNameList = new ArrayList<>();

        supplierList.forEach(supplier -> {
            supplierNameList.add(supplier.getName());
        });
        return supplierNameList;
    }
}
