package service.custom;

import dto.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);

    Integer updateCustomer(Customer customer) throws SQLException;
    Customer searchCustomer(String id) throws SQLException;
    List<Customer> getAll() throws SQLException;
    List<String> getCustomerIDs() throws SQLException;
}