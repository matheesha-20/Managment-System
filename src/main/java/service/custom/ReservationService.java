package service.custom;

import dto.Customer;
import dto.Reservation;
import service.SuperService;

import java.sql.SQLException;

public interface ReservationService extends SuperService {
    Integer addreservation(Reservation reservation) throws SQLException;
    Double pricecaculate (Integer adults,Integer childern,Double price_adult,Double price_childern,Long num_of_days);
}
