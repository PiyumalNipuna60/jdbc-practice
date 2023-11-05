package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Tm.CustomerTm;
import lk.ijse.dto.CustomerDto;
import lk.ijse.model.CustomerModel;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    public TableColumn <?, ?>colTel;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> image;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }
    public void clear(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        CustomerDto dto = new CustomerDto(id,name,address,tel);

        CustomerModel model = new CustomerModel();

        try {
            boolean isUpdated = model.editCustomer(dto);
            System.out.println(isUpdated);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer updated!").show();
                loadAllData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        CustomerDto dto = new CustomerDto(id,name,address,tel);

        CustomerModel model = new CustomerModel();
        boolean isTrue=model.saveCustomer(dto);

        if (isTrue){
        new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
        clear();
        loadAllData();
       }else {
            new Alert(Alert.AlertType.ERROR,"Somthing Wrong").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String Id = txtId.getText();

        CustomerModel model = new CustomerModel();

        int resultset = model.deleteCustomer(Id);

        if (resultset > 0) {
            new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted").show();
            loadAllData();
        }
        else {
            new Alert(Alert.AlertType.CONFIRMATION,"Customer not found").show();
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        CustomerModel model = new CustomerModel();

        CustomerDto resultSet = model.searchCustomer(id);

        if(resultSet==null){
            System.out.println("SOMETHING WENT WRONG...");
        }else {
        txtId.setText(resultSet.getId());
        txtName.setText(resultSet.getName());
        txtAddress.setText(resultSet.getAddress());
        txtTel.setText(String.valueOf(resultSet.getTel()));
        }
    }

    private void loadAllData() {
        CustomerModel customerModel = new CustomerModel();
        ArrayList<CustomerTm> all = customerModel.getAll();

        tblCustomer.setItems(FXCollections.observableList(all));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //table column
        //colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        loadAllData();

    }
}

