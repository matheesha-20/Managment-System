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
    public Boolean addCustomer(Customer customer) throws SQLException {    String sql = """
    INSERT INTO customer (CustomerNIC, Name, Email, PhoneNumber, Address, LoyalPoints)
    VALUES (?, ?, ?, ?, ?, ?)
""";

        return Crudutil.execute(sql,
                customer.getNic(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getLoyalpoints()
        );
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public Boolean updateCustomer(Customer customer) throws SQLException {
        String sql = """
        UPDATE customer
        SET CustomerNIC = ?, Name = ?, Email = ?, PhoneNumber = ?, Address = ?, Loyalpoints = ?
        WHERE CustomerID = ?
    """;

        return Crudutil.execute(sql,
                customer.getNic(),
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

        ResultSet resultSet = Crudutil.execute("SELECT * FROM customer WHERE CustomerNIC=?", id);

        while (resultSet.next()) {
            return  new Customer(
                    resultSet.getString("CustomerID"),
                    resultSet.getString("CustomerNIC"),
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
                    resultSet.getString("CustomerNIC"),
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

    @Override
    public List<String> getCustomerNICs() throws SQLException {
        List<Customer> all = getAll();
        ArrayList<String> customerNICList = new ArrayList<>();
        all.forEach(customer->{
            customerNICList.add(customer.getNic());
        });
        return customerNICList;
    }
}
