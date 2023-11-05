package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Tm.ItemTm;
import lk.ijse.dto.ItemDto;
import lk.ijse.model.ItemModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    public AnchorPane mainPane;
    @FXML
    private TableView <ItemTm>tableItem;
    @FXML
    private TextField txtItemCode;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtQty;
    @FXML
    private TableColumn <?,?> colDesc;
    @FXML
    private TableColumn <?,?>colCode;
    @FXML
    private TableColumn <?,?>colPrice;
    @FXML
    private TableColumn<?,?> colQty;

    @FXML

    public void btnAdd(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        ItemDto dto = new ItemDto(itemCode,description,price,qty);

        ItemModel model = new ItemModel();
        boolean isTrue = model.saveItem(dto);

        if (isTrue){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Saved").show();
            clear();
            loadAllData();
        }else {
            new Alert(Alert.AlertType.ERROR,"Somthing Wrong").show();
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        ItemModel model = new ItemModel();

        ItemDto resultset = model.searchItem(itemCode);

        if (resultset == null){
            System.out.println("SOMETHING WENT WRONG...");
        }else {
            txtItemCode.setText(resultset.getItemCode());
            txtDescription.setText(resultset.getDescription());
            txtPrice.setText(String.valueOf(resultset.getPrice()));
            txtQty.setText(String.valueOf(resultset.getQty()));
        }

    }

    public void btnClear(ActionEvent actionEvent) {clear();}
        public void clear(){
            txtItemCode.clear();
            txtDescription.clear();
            txtPrice.clear();
            txtQty.clear();
        }

    public void btnUpdate(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        ItemDto dto = new ItemDto(itemCode,description,price,qty);
        ItemModel model = new ItemModel();

        try {
            boolean isUpdated = model.updateItem(dto);
            System.out.println(isUpdated);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        ItemModel model = new ItemModel();

        int resultset = model.deleteItem(itemCode);

        if (resultset > 0){
            new Alert(Alert.AlertType.CONFIRMATION,"Item deleted").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Item not found").show();
        }
    }

    private void loadAllData() {
        ItemModel ItemModel = new ItemModel();
        ArrayList<ItemTm> all = ItemModel.getAll();

        tableItem.setItems(FXCollections.observableList(all));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Description"));

        colCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));

        loadAllData();

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"))));
        stage.show();
        Stage window = (Stage) mainPane.getScene().getWindow();
        window.close();
    }

    public void btnCustomer(ActionEvent actionEvent) {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
            mainPane.getChildren().setAll(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItem(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item.fxml"))));
        stage.show();
      //  Stage window = (Stage) mainPane.getScene().getWindow();
      //  window.close();
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item.fxml"))));
        stage.show();
        Stage window = (Stage) mainPane.getScene().getWindow();
        window.close();
    }

    public void btnOrder(ActionEvent actionEvent) {
    }
}