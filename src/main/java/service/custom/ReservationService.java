package service.custom;

import dto.Customer;
import service.SuperService;

public interface ReservationService extends SuperService {
    boolean addreservation(Customer customer);
}
