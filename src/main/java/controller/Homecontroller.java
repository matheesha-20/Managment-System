package controller;

import javafx.event.ActionEvent;
import starter.Starter;

import java.io.IOException;

public class Homecontroller {
    public void clickres(ActionEvent actionEvent) throws IOException {
    Starter.switchscne("/view/Reservation.fxml");

    }

    public void clickcusbtn(ActionEvent actionEvent) throws IOException {
        Starter.switchscne("/view/Customerform.fxml");
    }
}
