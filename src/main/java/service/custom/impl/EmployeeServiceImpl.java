package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import entity.ProductEntity;
import org.modelmapper.ModelMapper;
import service.custom.EmployeeService;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Boolean addEmployee(Employee employee) throws SQLException {

        EmployeeEntity employeeEntity= new ModelMapper().map(employee,EmployeeEntity.class);

        return CrudUtil.execute("INSERT INTO employee (name,company,Address) VALUES (?,?,?)",
                employee.getName(),employee.getCompany(),employee.getEmailAddress());

    }

    @Override
    public Boolean updateEmployee(Employee employee) throws SQLException {

        EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);

        String sql = "UPDATE employee SET name= ?,company= ?,Address= ? WHERE id =?";

        Boolean bo = CrudUtil.execute(sql,
                employeeEntity.getName(),
                employeeEntity.getCompany(),
                employeeEntity.getEmailAddress(),
                employeeEntity.getId());
        return bo;
    }

    @Override
    public Boolean deleteEmployee(String id) throws SQLException {
        String SQL ="DELETE FROM Employee WHERE id = ?";

        Boolean b = CrudUtil.execute(SQL, id);
        return b;
    }

    @Override
    public Employee searchByIdEmployee(String id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return List.of();
    }
}
