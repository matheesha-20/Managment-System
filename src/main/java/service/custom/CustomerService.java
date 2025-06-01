package service.custom;

import model.Customer;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
    boolean updateCustomer(Customer customer);
    Customer searchCustomer(String id);
}
