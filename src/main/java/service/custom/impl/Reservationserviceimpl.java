package service.custom.impl;

import db.DBConnection;
import dto.Customer;
import dto.Reservation;
import service.custom.ReservationService;
import util.Crudutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reservationserviceimpl implements ReservationService {

    @Override
    public Integer addreservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservation (customer_id, room_number, check_in, check_out, price, status, num_of_adults, num_of_children) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return Crudutil.execute(
                sql,
                reservation.getCustomer_id(),
                reservation.getRoom_number(),
                reservation.getCheck_in(),
                reservation.getCheck_out(),
                reservation.getPrice(),
                reservation.getStatus(),
                reservation.getNum_of_adults(),
                reservation.getNum_of_children()

        );
    }

    @Override
    public Double pricecaculate(Integer adults, Integer childern, Double price_adult, Double price_childern,Long num_of_days) {

        return price_adult*adults+price_childern*childern*num_of_days;
    }

}
