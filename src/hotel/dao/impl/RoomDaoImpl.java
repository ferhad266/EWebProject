package hotel.dao.impl;

import hotel.dao.DbHelper;
import hotel.dao.RoomDao;
import hotel.model.Room;
import hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public List<Room> getRoomList() throws Exception {
        List<Room> roomList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,ROOM_NUMBER,ROOM_SITUATION,PRICE,HUMAN_COUNT,ROOM_TYPE FROM ROOM\r\n" + "WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Room room = new Room();
                    room.setR(rs.getLong("r"));
                    room.setId(rs.getLong("ID"));
                    room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                    room.setRoomSituation(rs.getString("ROOM_SITUATION"));
                    room.setPrice(rs.getFloat("PRICE"));
                    room.setHumanCount(rs.getInt("HUMAN_COUNT"));
                    room.setRoomType(rs.getString("ROOM_TYPE"));
                    roomList.add(room);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return roomList;
    }

    @Override
    public boolean addRoom(Room room) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO ROOM(ID,ROOM_NUMBER,ROOM_SITUATION,PRICE,HUMAN_COUNT,ROOM_TYPE)\r\n"
                + "VALUES(ROOM_SEQ.NEXTVAL,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, room.getRoomNumber());
                ps.setString(2, room.getRoomSituation());
                ps.setFloat(3, room.getPrice());
                ps.setInt(4, room.getHumanCount());
                ps.setString(5, room.getRoomType());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connetion is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public Room getRoomById(Long roomId) throws Exception {
        Room room = new Room();
        List<Room> roomList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,ROOM_NUMBER,ROOM_SITUATION,PRICE,HUMAN_COUNT,ROOM_TYPE FROM ROOM\r\n"
                + " WHERE ACTIVE = 1 AND ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    room.setR(rs.getLong("r"));
                    room.setId(rs.getLong("ID"));
                    room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                    room.setRoomSituation(rs.getString("ROOM_SITUATION"));
                    room.setPrice(rs.getFloat("PRICE"));
                    room.setHumanCount(rs.getInt("HUMAN_COUNT"));
                    room.setRoomType(rs.getString("ROOM_TYPE"));
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return room;
    }

    @Override
    public boolean updateRoom(Room room) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE ROOM SET ROOM_NUMBER=?,ROOM_SITUATION=?,PRICE=?,HUMAN_COUNT=?,ROOM_TYPE=?" + " WHERE ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, room.getRoomNumber());
                ps.setString(2, room.getRoomSituation());
                ps.setFloat(3, room.getPrice());
                ps.setInt(4, room.getHumanCount());
                ps.setString(5, room.getRoomType());
                ps.setLong(6, room.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connetion is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteRoom(Long roomId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE ROOM SET ACTIVE = 0" + " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connetion is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Room> searchRoomData(String keyword) throws Exception {
        List<Room> roomList = new ArrayList<Room>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,ROOM_NUMBER,ROOM_SITUATION,PRICE,HUMAN_COUNT,ROOM_TYPE FROM ROOM R WHERE R.ACTIVE = 1\r\n"
                + "AND LOWER(R.ROOM_NUMBER) LIKE LOWER(?) OR LOWER(R.ROOM_SITUATION) LIKE LOWER(?) OR LOWER(R.PRICE) LIKE LOWER(?) OR LOWER(R.HUMAN_COUNT) LIKE LOWER(?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Room room = new Room();
                    room.setId(rs.getLong("ID"));
                    room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                    room.setRoomSituation(rs.getString("ROOM_SITUATION"));
                    room.setPrice(rs.getFloat("PRICE"));
                    room.setHumanCount(rs.getInt("HUMAN_COUNT"));
                    room.setRoomType(rs.getString("ROOM_TYPE"));
                    roomList.add(room);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomList;
    }
}
