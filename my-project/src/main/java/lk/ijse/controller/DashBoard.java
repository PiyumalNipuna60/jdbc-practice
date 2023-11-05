package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashBoard implements Initializable {
    public Pane paneDashboard;
    public AnchorPane mainPane;
    public Label lblDate;
    public Label lblTime;

    public void btnDashboard(ActionEvent actionEvent) {
    }

    public void btnCustomer(ActionEvent actionEvent) {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
            paneDashboard.getChildren().setAll(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItem(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item.fxml"))));
        stage.show();
         Stage window = (Stage) mainPane.getScene().getWindow();
         window.close();
    }
    public void btnLogout(ActionEvent actionEvent) {
    }

    public void btnOrder(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Oredr.fxml"))));
        stage.show();
        Stage window = (Stage) mainPane.getScene().getWindow();
        window.close();
    }


    /*-----DATE AND TIME GENERATE------*/
    private void generateRealTime() {
        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
           lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateRealTime();
    }
}
