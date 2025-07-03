package service.custom;

import dto.Room;
import javafx.scene.image.Image;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface
RoomService extends SuperService {
    boolean addRoom(Room room);
    boolean deleteRoom(String id);
    boolean updateRoom(Room room);
    Room searchRoom(String id) throws SQLException;
    List<Room> getAll() throws SQLException;
    List<String> getRoomIDs() throws SQLException;
    Image setimage(int x);
}
