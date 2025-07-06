package service.custom.impl;

import dto.Room;
import javafx.scene.image.Image;
import service.custom.RoomService;
import util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomServiceimpl implements RoomService {

    @Override
    public boolean addRoom(Room room) {
        return false;
    }

    @Override
    public boolean deleteRoom(String id) {
        return false;
    }

    @Override
    public boolean updateRoom(Room room) {
        return false;
    }

    @Override
    public Room searchRoom(String id) throws SQLException {
        ResultSet resultSet = Crudutil.execute("SELECT * FROM room WHERE room_number=?", id);
        while (resultSet.next()) {
            return new Room(
                    resultSet.getInt("room_number"),
                    resultSet.getString("room_type"),
                    resultSet.getString("capacity"),
                    resultSet.getDouble("price_adult"),
                    resultSet.getDouble("price_children"),
                    resultSet.getString("status"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("has_ac"),
                    resultSet.getBoolean("has_wifi"),
                    resultSet.getBoolean("has_bathroom"),
                    resultSet.getBoolean("has_tv")
            );
        }
        return null;
    }

    @Override
    public List<Room> getAll() throws SQLException {
        ResultSet resultSet = Crudutil.execute("SELECT * FROM room");
        ArrayList<Room> roomArrayList = new ArrayList<>();
        while (resultSet.next()) {
            roomArrayList.add(new Room(
                    resultSet.getInt("room_number"),
                    resultSet.getString("room_type"),
                    resultSet.getString("capacity"),
                    resultSet.getDouble("price_adult"),
                    resultSet.getDouble("price_children"),
                    resultSet.getString("status"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("has_ac"),
                    resultSet.getBoolean("has_wifi"),
                    resultSet.getBoolean("has_bathroom"),
                    resultSet.getBoolean("has_tv")
            ));
        }
        return roomArrayList;
    }

    @Override
    public List<String> getRoomIDs() throws SQLException {
        List<Room> all = getAll();
        ArrayList<String> roomIdList = new ArrayList<>();
        all.forEach(room->{
            roomIdList.add(String.valueOf(room.getRoomId()));
        });
        return roomIdList;
    }

    @Override
    public Image setimage(int x) {
        ArrayList<Image> imagelist = new ArrayList<>();
        imagelist.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/room1.jpg"))));
        imagelist.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/room2.jpg"))));
        return imagelist.get(x);
    }


}
