package controller;

import dto.Employee;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.CrudUtil;
import util.ServiceType;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);



    @FXML
    private TableColumn colEmpCompany;

    @FXML
    private TableColumn colEmpEmail;

    @FXML
    private TableColumn colEmpId;

    @FXML
    private TableColumn colEmpName;

    @FXML
    private TableView tblEmployee;

    @FXML
    private TextField txtEmpCompany;

    @FXML
    private TextField txtEmpEmailAddress;

    @FXML
    private TextField txtEmpID;

    @FXML
    private TextField txtEmpName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));



        loadTable();

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String name = txtEmpName.getText();
        String company = txtEmpCompany.getText();
        String emailAddress =txtEmpEmailAddress.getText();

        Employee employee = new Employee(name,company,emailAddress);

        try {
            Boolean b = employeeService.addEmployee(employee);
            loadTable();

            if (b) {
                new Alert(Alert.AlertType.INFORMATION,"Employee Added").show();

            }else{
                new Alert(Alert.AlertType.INFORMATION,"Failed to Add").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        fillFieldsFromTable();
        String id = txtEmpID.getText();

        try {
            Boolean b = employeeService.deleteEmployee(id);
            loadTable();
            clearFields();

            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Employee "+id+" Deleted").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Employee Not Deleted").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

        fillFieldsFromTable();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        loadTable();
        clearFields();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {


        Employee employee = new Employee(
                Integer.parseInt(txtEmpID.getText()),
                txtEmpName.getText(),
                txtEmpCompany.getText(),
                txtEmpEmailAddress.getText()
        );
        try {
            Boolean b = employeeService.updateEmployee(employee);
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Employee Updated").show();
                loadTable();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Employee Not Updated").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadTable(){

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
            while (resultSet.next()) {
                employeeObservableList.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("company"),
                        resultSet.getString("Address")

                ));
            }
            tblEmployee.setItems(employeeObservableList);

        }catch(SQLException e){
                throw new RuntimeException(e);
            }

    }

    private void fillFieldsFromTable(){
        Employee selectedEmployee = (Employee) tblEmployee.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            txtEmpID.setText(selectedEmployee.getId().toString());
            txtEmpName.setText(selectedEmployee.getName());
            txtEmpCompany.setText(selectedEmployee.getCompany());
            txtEmpEmailAddress.setText(selectedEmployee.getEmailAddress());


        } else {
            new Alert(Alert.AlertType.INFORMATION,"Select the field from table").show();

        }
    }

    private void clearFields() {
        txtEmpID.clear();
        txtEmpName.clear();
        txtEmpCompany.clear();
        txtEmpEmailAddress.clear();
    }


}
