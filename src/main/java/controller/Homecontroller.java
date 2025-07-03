package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import starter.Starter;

import java.io.IOException;

public class Homecontroller {
    public void clickres(ActionEvent actionEvent) throws IOException {
    Starter.switchscne("/view/Reservation.fxml");

    }
}
