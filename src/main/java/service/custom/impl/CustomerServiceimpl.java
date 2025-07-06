package service.custom.impl;

import dto.Customer;
import service.custom.CustomerService;
import util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public Integer updateCustomer(Customer customer) throws SQLException {
        String sql = """
        UPDATE customer
        SET Name = ?, Email = ?, PhoneNumber = ?, Address = ?, Loyalpoints = ?
        WHERE CustomerID = ?
    """;

        return Crudutil.execute(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getLoyalpoints(),
                customer.getId()
        );
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException {

        ResultSet resultSet = Crudutil.execute("SELECT * FROM customer WHERE CustomerID=?", id);
        while (resultSet.next()) {
           return new Customer(
                    resultSet.getString("CustomerID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Email"),
                    resultSet.getString("PhoneNumber"),
                    resultSet.getString("Address"),
                    resultSet.getInt("Loyalpoints")
            );
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        ResultSet resultSet = Crudutil.execute("SELECT * FROM customer");
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        while (resultSet.next()) {
            customerArrayList.add(new Customer(
                    resultSet.getString("CustomerID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Email"),
                    resultSet.getString("PhoneNumber"),
                    resultSet.getString("Address"),
                    resultSet.getInt("Loyalpoints")
            ));
        }
        return customerArrayList;
    }

    @Override
    public List<String> getCustomerIDs() throws SQLException {
        List<Customer> all = getAll();
        ArrayList<String> customerIdList = new ArrayList<>();
        all.forEach(customer->{
            customerIdList.add(customer.getId());
        });
        return customerIdList;
    }
}
