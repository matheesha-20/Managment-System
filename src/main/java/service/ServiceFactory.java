package service;

import service.custom.impl.CustomerServiceimpl;
import service.custom.impl.Reservationserviceimpl;
import service.custom.impl.RoomServiceimpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){ return instance==null?instance=new ServiceFactory():instance;}

    public <T extends SuperService> T getServiceType(ServiceType type){
        switch (type){
            case CUSTOMER: return (T) new CustomerServiceimpl();
            case ROOM: return (T) new RoomServiceimpl();
            case RESERVATION: return (T) new Reservationserviceimpl();
        }
        return null;
    }

}
