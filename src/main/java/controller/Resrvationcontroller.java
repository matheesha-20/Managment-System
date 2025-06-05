package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Resrvationcontroller {
    public ImageView img;
    public Label avlible;
    public Text title;
    public Text price;
    public TextArea description;

    public void setImg(ImageView img) {
        img.getImage();
    }


    public void btn(ActionEvent actionEvent) throws SQLException {
        Connection connection =DBConnection.getInstance().getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM customer");
        while (resultSet.next()) System.out.println(resultSet.getString("firstname"));

    }
}
