package model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Customer {


    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int loyalpoints;

}
