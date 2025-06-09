package controller;

import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.custom.ProductService;
import util.ServiceType;

public class ProductFormController {


    ProductService productService= ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colImagePath;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private TableView<?> tblProduct;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtImgPath;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtSupplier;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Product product = new Product();
        Boolean b =productService.addProduct(product);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

}
