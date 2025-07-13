package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ReservationService;
import service.custom.RoomService;
import starter.Starter;
import util.ServiceType;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Customercontroller implements Initializable {

    public Label C_ID ;
    public JFXTextField C_NIC ;
    public JFXTextField Search_NIC;
    public JFXTextField C_name;
    public JFXTextField C_email;
    public JFXTextField C_phn;
    public JFXTextField C_adres;
    public Label C_loylp;
    public TableView tbl;
    public TableColumn tablcid;
    public TableColumn tablnic;
    public TableColumn tablname;
    public TableColumn tablemail;
    public TableColumn tblphn;
    public TableColumn tbladres;
    public TableColumn tbllop;


    CustomerService customerservice = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    RoomService roomservice = ServiceFactory.getInstance().getServiceType(ServiceType.ROOM);
    ReservationService reservationservice = ServiceFactory.getInstance().getServiceType(ServiceType.RESERVATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            int id =customerservice.getCustomerIDs().size()+1;
            String formattedId = String.format("C%04d", id);
            C_ID.setText("CUSTOMER ID - " + formattedId);

            C_loylp.setText("Loyal-Points - 0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void search(ActionEvent actionEvent) throws SQLException {
        String formattedId = String.format("C%04d", Integer.parseInt(customerservice.searchCustomer(Search_NIC.getText()).getId()));
        C_ID.setText("CUSTOMER ID - "+ formattedId);
        C_NIC.setText(customerservice.searchCustomer(Search_NIC.getText()).getNic());
        C_name.setText(customerservice.searchCustomer(Search_NIC.getText()).getName());
        C_email.setText(customerservice.searchCustomer(Search_NIC.getText()).getEmail());
        C_phn.setText(customerservice.searchCustomer(Search_NIC.getText()).getPhone());
        C_adres.setText(customerservice.searchCustomer(Search_NIC.getText()).getAddress());
        C_loylp.setText("Loyal-Points - "+ customerservice.searchCustomer(Search_NIC.getText()).getLoyalpoints());
    }

    public void add(ActionEvent actionEvent) throws SQLException {
        customerservice.addCustomer(new Customer(String.valueOf(customerservice.getCustomerIDs().size() + 1),C_NIC.getText(),C_name.getText(),C_email.getText(),C_phn.getText(),C_adres.getText(),0));

        C_ID.setText(String.valueOf(customerservice.getCustomerIDs().size()+1));
        C_NIC.setText("");
        C_name.setText("");
        C_email.setText("");
        C_phn.setText("");
        C_adres.setText("");
    }

    public void update(ActionEvent actionEvent) {
    }

    public void delete(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void viewall(ActionEvent actionEvent) throws IOException {
        Starter.switchscne("/view/Viewallcustomers.fxml");

    }

    public void reload(ActionEvent actionEvent) {
    }
}
