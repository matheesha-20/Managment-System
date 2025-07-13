package service.custom;

import dto.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    Boolean addCustomer(Customer customer) throws SQLException;
    boolean deleteCustomer(String id);

    Boolean updateCustomer(Customer customer) throws SQLException;
    Customer searchCustomer(String id) throws SQLException;
    List<Customer> getAll() throws SQLException;
    List<String> getCustomerIDs() throws SQLException;
    List<String> getCustomerNICs() throws SQLException;
}