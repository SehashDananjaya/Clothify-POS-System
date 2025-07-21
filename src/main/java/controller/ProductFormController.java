package controller;

import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.CrudUtil;
import util.ServiceType;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {


    ProductService productService= ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);


    @FXML
    private ComboBox cmbSupplier;

    @FXML
    private TableColumn colCategory;

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colImagePath;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableColumn colSupplier;

    @FXML
    private TableView tblProduct;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtImgPath;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtSize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));
        colImagePath.setCellValueFactory(new PropertyValueFactory<>("imgPath"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadTable();
        loadSupplierName();




    }

    @FXML
    void btnAddOnAction(ActionEvent event) {


        String name = txtName.getText();
        String category = txtCategory.getText();
        String size = txtSize.getText();
        Double price= Double.parseDouble(txtPrice.getText());
        Integer quantityOnHand = Integer.parseInt(txtQtyOnHand.getText());
        String imgPath = txtImgPath.getText();
       // String supplier =  txtSupplier.getText();
        String supplier =  cmbSupplier.getValue().toString();
        String description = txtDesc.getText();

        Product product = new Product(name,category,size,price,quantityOnHand,imgPath,supplier,description);


        try {
            Boolean b =productService.addProduct(product);

            if (b) {
                new Alert(Alert.AlertType.INFORMATION,"Product Added").show();
                loadTable();
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
        String id = txtId.getText();

        try {
            Boolean b = productService.deleteProduct(id);
            loadTable();
            clearFields();

            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Product Deleted").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Product Not Deleted").show();
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
    void btnUpdateOnAction(ActionEvent event) {

        try {

        Product product = new Product(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),txtCategory.getText(),
                txtSize.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                txtImgPath.getText(),
                cmbSupplier.getValue().toString(),
                txtDesc.getText());

            Boolean b = productService.updateProduct(product);

            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Product Updated").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Product Not Updated").show();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loadTable();

    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {

        loadTable();
        clearFields();

    }

    private void clearFields() {
            txtName.clear();
            txtCategory.clear();
            txtSize.clear();
            txtPrice.clear();
            txtQtyOnHand.clear();
            txtImgPath.clear();
            cmbSupplier.getSelectionModel().clearSelection();
            txtDesc.clear();
    }

    private void loadTable(){

        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM product");
            while(resultSet.next()){
                productObservableList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                       resultSet.getString("category"),
                        resultSet.getString("size"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantityOnHand"),
                        resultSet.getString("imgPath"),
                        resultSet.getString("supplier"),
                        resultSet.getString("description")
                ));
            }
            tblProduct.setItems(productObservableList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    private void fillFieldsFromTable(){
        Product selectedProduct = (Product) tblProduct.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            txtId.setText(selectedProduct.getId().toString());
            txtName.setText(selectedProduct.getName());
            txtCategory.setText(selectedProduct.getCategory());
            txtSize.setText(selectedProduct.getSize());
            txtPrice.setText(String.valueOf(selectedProduct.getPrice()));
            txtQtyOnHand.setText(String.valueOf(selectedProduct.getQuantityOnHand()));
            txtImgPath.setText(selectedProduct.getImgPath());
           // txtSupplier.setText(selectedProduct.getSupplier());
            cmbSupplier.setValue(selectedProduct.getSupplier());
            txtDesc.setText(selectedProduct.getDescription());

        } else {
            new Alert(Alert.AlertType.INFORMATION,"Select the field from table").show();

        }
    }

    public void loadSupplierName(){
        try {
            List<String> supplierNames = supplierService.getSupplierNames();
            cmbSupplier.setItems(FXCollections.observableArrayList(supplierNames));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
