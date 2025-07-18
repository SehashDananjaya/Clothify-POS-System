package controller;

import dto.Employee;
import dto.Supplier;
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
import service.custom.SupplierService;
import util.CrudUtil;
import util.ServiceType;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {


    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);


    @FXML
    private TableColumn colSComapny;

    @FXML
    private TableColumn colSEmail;

    @FXML
    private TableColumn colSId;

    @FXML
    private TableColumn colSItem;

    @FXML
    private TableColumn colSName;

    @FXML
    private TableView tblSupplier;

    @FXML
    private TextField txtSComapny;

    @FXML
    private TextField txtSEmail;

    @FXML
    private TextField txtSId;

    @FXML
    private TextField txtSItem;

    @FXML
    private TextField txtSName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSComapny.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        //colSItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        loadTable();

    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

        Supplier supplier = new Supplier(
                txtSName.getText(),
                txtSComapny.getText(),
                txtSEmail.getText()
        );

        try {
            Boolean b = supplierService.addSupplier(supplier);
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Supplier Added").show();
                loadTable();
                clearField();

            }else{
                new Alert(Alert.AlertType.INFORMATION,"Supplier not Added").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        fillFieldsFromTable();
        String id = txtSId.getText();

        try {
            Boolean b = supplierService.deleteSupplier(id);
            loadTable();
            clearField();

            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Supplier "+id+" Deleted").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Supplier Not Deleted").show();
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
    void btnRefreshTableOnAction(ActionEvent event) {

        loadTable();
        clearField();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        Supplier supplier = new Supplier(
                Integer.parseInt(txtSId.getText()),
                txtSName.getText(),
                txtSComapny.getText(),
                txtSEmail.getText());

        try {
            Boolean b = supplierService.updateSupplier(supplier);
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Supplier Updated").show();
                loadTable();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Supplier Not Updated").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadTable() {

        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");
            while (resultSet.next()) {
                supplierObservableList.add(new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("company"),
                        resultSet.getString("emailAddress")
                ));
            }
            tblSupplier.setItems(supplierObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void clearField() {
            txtSId.clear();
            txtSName.clear();
            txtSComapny.clear();
            txtSEmail.clear();
    }

    private void fillFieldsFromTable(){
        Supplier selectedSupplier = (Supplier) tblSupplier.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            txtSId.setText(selectedSupplier.getId().toString());
            txtSName.setText(selectedSupplier.getName());
            txtSComapny.setText(selectedSupplier.getCompany());
            txtSEmail.setText(selectedSupplier.getEmailAddress());


        } else {
            new Alert(Alert.AlertType.INFORMATION,"Select the field from table").show();

        }
    }


}

