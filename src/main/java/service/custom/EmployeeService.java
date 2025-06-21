package service.custom;

import dto.Employee;
import dto.Product;
import service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    Boolean addEmployee(Employee employee);
    Boolean updateEmployee(Employee employee);
    Employee searchByIdEmployee (String id);
    List<Employee> getAll();
}
