package entity;

import java.time.LocalDate;

public class Reservationentity {
    private Integer id;
    private Integer customerId;
    private Integer roomId;
    private LocalDate check_inDate;
    private LocalDate check_outDate;
    private Double totalPrice;
    private String status;
}
