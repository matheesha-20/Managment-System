package service.custom.impl;

import model.Customer;
import service.custom.CustomerService;

public class CustomerServiceimpl implements CustomerService {

    @Override
    public boolean addCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) {
        return null;
    }
}
