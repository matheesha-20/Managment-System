package controller;

import db.DBConnection;
import dto.Customer;
import dto.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import dto.Reservation;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ReservationService;
import service.custom.RoomService;
import starter.Starter;
import util.ServiceType;



import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static javafx.scene.image.Image.*;

public class Resrvationcontroller implements Initializable {

    public ComboBox cusnic;
    public ComboBox roomid;
    public TextField cusnicn;
    public TextField cusnamn;
    public TextField cusphn;
    public TextField cusemil;
    public Label loyalp;
    public Label date;
    public Label time;
    public TextField cusaddress;
    public CheckBox air;
    public CheckBox bath;
    public CheckBox tv;
    public CheckBox wifi;
    public Label priadul;
    public Label prichild;
    public SplitMenuButton split;
    public DatePicker checkin;
    public TextField cNIC;

    Connection connection =DBConnection.getInstance().getConnection();
    List <Reservation> reservationlist = new ArrayList<>();

    public ImageView img;
    public Label avlible;
    public Text title;
    public Text price;
    public Label description;
    public Label pri;
    public TextField Children;
    public TextField adult;
    public TextField roomnum;
    public TextField checkout;
    public TextField cname;
    public TextField cpn;

    public ComboBox status;
    double Price;

    CustomerService customerservice = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    RoomService roomservice = ServiceFactory.getInstance().getServiceType(ServiceType.ROOM);
    ReservationService reservationservice = ServiceFactory.getInstance().getServiceType(ServiceType.RESERVATION);


    public Resrvationcontroller() throws SQLException {
    }

    public void add(ActionEvent actionEvent) throws SQLException {

        String nic = (String) cusnic.getValue();
        String roomnum = this.roomnum.getText();
        Integer adults = Integer.valueOf(adult.getText());
        Integer children = Integer.valueOf(Children.getText());
        LocalDate Checkin = LocalDate.parse(String.valueOf(checkin.getValue()));
        LocalDate Checkout = LocalDate.parse(checkout.getText());
        String Status = status.getValue().toString();

        Price = reservationservice.pricecaculate(adults,children,roomservice.searchRoom(roomnum).getRoomPrice_adults(),roomservice.searchRoom(roomnum).getRoomPrice_children(), ChronoUnit.DAYS.between(Checkin,Checkout));

        pri.setText(String.format("€%.2f", Price));


        Reservation reservation=new Reservation(customerservice.searchCustomer((String) cusnic.getValue()).getId(),roomnum,Checkin,Checkout,Price,Status,adults,children);
        reservationservice.addreservation(reservation);



    }

    public void clickstatus(ActionEvent actionEvent) {
    }

    public void homebtn(ActionEvent actionEvent) throws IOException {
        Starter.switchscne("/view/Home.fxml");
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void cusnicclick(ActionEvent actionEvent) {
        cusnic.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setvalusetocustomertext((String) newValue);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void selectbtn(ActionEvent actionEvent) throws SQLException {
        Room room = roomservice.searchRoom((String) roomid.getValue());
        roomnum.setText(String.valueOf(room.getRoomId()));

         Random r = new Random();
        Image image = roomservice.setimage(r.nextInt(2));

        img.setFitWidth(263);
        img.setFitHeight(204);
        img.setPreserveRatio(false);
        img.setImage(image);
        title.setText(roomservice.searchRoom((String) roomid.getValue()).getRoomName());
        description.setWrapText(true);
        description.setMaxWidth(200);
        description.setText(roomservice.searchRoom((String) roomid.getValue()).getDescription());
        air.setSelected(roomservice.searchRoom((String) roomid.getValue()).getHas_ac());
        bath.setSelected(roomservice.searchRoom((String) roomid.getValue()).getHas_bathroom());
        wifi.setSelected(roomservice.searchRoom((String) roomid.getValue()).getHas_wifi());
        tv.setSelected(roomservice.searchRoom((String) roomid.getValue()).getHas_tv());
        priadul.setText(String.valueOf(roomservice.searchRoom((String) roomid.getValue()).getRoomPrice_adults()));
        prichild.setText(String.valueOf(roomservice.searchRoom((String) roomid.getValue()).getRoomPrice_children()));
    }

    public void cancbtn(ActionEvent actionEvent) throws IOException {
    }

    public void roomidclick(ActionEvent actionEvent) {
    }

    public void addnewcus(ActionEvent actionEvent) throws SQLException {
        Customer customer = new Customer(
                "0",
                cusnicn.getText(),
                cusnamn.getText(),
                cusemil.getText(),
                cusphn.getText(),
                cusaddress.getText(),
                10
        );
        customerservice.addCustomer(customer);
    }

    public void upcusbtn(ActionEvent actionEvent) throws SQLException {
        Customer customer = new Customer(
            customerservice.searchCustomer((String) cusnic.getValue()).getId(),
            cusnicn.getText(),
            cusnamn.getText(),
            cusemil.getText(),
            cusphn.getText(),
            cusaddress.getText(),
                (Integer.parseInt(loyalp.getText()))
        );

        customerservice.updateCustomer(customer);
    }

    public void setdateandtime(){
        date.setText("  Date :- "+String.valueOf(LocalDate.now()));

        // -----------TIME-----------
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    LocalTime localtime = LocalTime.now();
                    time.setText("  Time :- "+localtime.getHour()+":"+localtime.getMinute()+":"+localtime.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void loadcusids() throws SQLException {
        List<String> cusids = customerservice.getCustomerNICs();
        cusnic.setItems(FXCollections.observableArrayList(cusids));
    }

    private void loadroomids() throws SQLException {
        List<String> roomids = roomservice.getRoomIDs();
        roomid.setItems(FXCollections.observableArrayList(roomids));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setdateandtime();

        try {
            loadcusids();
            loadroomids();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        cusnic.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setvalusetocustomertext((String) newValue);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        roomid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setvalusetoroomtext((String) newValue);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setvalusetocustomertext(String cusid) throws SQLException {
        Customer customer = customerservice.searchCustomer(cusid);
        cusnicn.setText(customer.getNic());
        cname.setText(customer.getName());
        cusnamn.setText(customer.getName());
        cpn.setText(customer.getPhone());
        cname.setText(customer.getName());
        cusemil.setText(customer.getEmail());
        cusaddress.setText(customer.getAddress());
        cusphn.setText(customer.getPhone());
        loyalp.setText(String.valueOf(customer.getLoyalpoints()));
    }

    private void setvalusetoroomtext(String roomid) throws SQLException {

        Room room = roomservice.searchRoom(roomid);
        avlible.setText(room.getRoomStatus());
    }

    public void filterbtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/filters.fxml"))));
        stage.initStyle(StageStyle.UNIFIED);
        stage.setTitle("FILTERS");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void searchbtn(ActionEvent actionEvent) {
    }

    public void searchcustomer(KeyEvent keyEvent) throws SQLException {
        cname.setText(customerservice.searchCustomer(cNIC.getText()).getName());
    }
}
