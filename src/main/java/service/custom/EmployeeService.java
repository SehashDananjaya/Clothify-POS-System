package service.custom;

import dto.Employee;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
    Boolean addEmployee(Employee employee) throws SQLException;
    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(String id) throws SQLException;
    Employee searchByIdEmployee (String id);
    List<Employee> getAll();
}
