package controller;

import db.DBConnection;
import dto.Customer;
import dto.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import dto.Reservation;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static javafx.scene.image.Image.*;

public class Resrvationcontroller implements Initializable {

    public ComboBox cusid;
    public ComboBox roomid;
    public TextField cusidn;
    public TextField cusnamn;
    public TextField cusphn;
    public TextField cusemil;
    public Label loyalp;
    public Label date;
    public Label time;
    public TextField cusaddress;
    Connection connection =DBConnection.getInstance().getConnection();
    List <Reservation> reservationlist = new ArrayList<>();

    public ImageView img;
    public Label avlible;
    public Text title;
    public Text price;
    public TextArea description;
    public Label pri;
    public TextField childern;
    public TextField adults;
    public TextField roomnum;
    public TextField checkout;
    public TextField cname;
    public TextField cid;
    public TextField cpn;
    public TextField checkin;
    public ComboBox status;

    CustomerService customerservice = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    RoomService roomservice = ServiceFactory.getInstance().getServiceType(ServiceType.ROOM);
    ReservationService reservationservice = ServiceFactory.getInstance().getServiceType(ServiceType.RESERVATION);


    public Resrvationcontroller() throws SQLException {
    }

    public void setImg(ImageView img) {
        img.getImage();
    }


    public void btn(ActionEvent actionEvent) throws SQLException {


        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM customer");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(1));
            System.out.print(resultSet.getString(2));
            System.out.print(resultSet.getString(3));
            System.out.print(resultSet.getString(4));
            System.out.print(resultSet.getString(5));
            System.out.print(resultSet.getString(6));
            System.out.println(resultSet.getString(7));
        }


    }


    public void add(ActionEvent actionEvent) throws SQLException {
        Integer rid = 0;
        String id = cid.getText();
        String name = cname.getText();
        String phone = cpn.getText();
        String roomnum = this.roomnum.getText();
        String adults = this.adults.getText();
        String children = this.childern.getText();
        LocalDate checkin = LocalDate.parse(this.checkin.getText());
        LocalDate checkout = LocalDate.parse(this.checkout.getText());
        String status = this.status.getValue().toString();
        double price = 0;


        Reservation reservation=new Reservation(rid,id,roomnum,checkin,checkout,price,status);
        reservationlist.add(reservation);



    }

    public void clickstatus(ActionEvent actionEvent) {
    }

    public void homebtn(ActionEvent actionEvent) throws IOException {
        Starter.switchscne("/view/Home.fxml");
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void cusidclick(ActionEvent actionEvent) {
        cusid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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

        img.setImage(image);
    }

    public void cancbtn(ActionEvent actionEvent) {
    }

    public void roomidclick(ActionEvent actionEvent) {
    }

    public void addnewcus(ActionEvent actionEvent) {
    }

    public void upcusbtn(ActionEvent actionEvent) throws SQLException {
        Customer customer = new Customer(
            cusidn.getText(),
            cusnamn.getText(),
            cusemil.getText(),
            cusphn.getText(),
            cusaddress.getText(),
            Integer.parseInt(loyalp.getText())
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
        List<String> cusids = customerservice.getCustomerIDs();
        cusid.setItems(FXCollections.observableArrayList(cusids));
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

        cusid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
        cusidn.setText(customer.getId());
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
}
