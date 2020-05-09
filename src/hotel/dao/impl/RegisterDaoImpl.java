package hotel.dao.impl;

import hotel.dao.DbHelper;
import hotel.dao.RegisterDao;
import hotel.model.Register;
import hotel.model.Room;
import hotel.model.Worker;
import hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegisterDaoImpl implements RegisterDao {
    @Override
    public List<Register> getRegisterList() throws Exception {
        List<Register> registerList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,\n"
                + "       R.ID,\n"
                + "       R.NAME,\n"
                + "       R.SURNAME,\n"
                + "       R.DOB,\n"
                + "       R.FATHER_NAME,\n"
                + "       R.ADULT_COUNT,\n"
                + "       R.CHILD_COUNT,\n"
                + "       R.PHONE,\n"
                + "       R.EMAIL,\n"
                + "       R.CHECK_IN,\n"
                + "       R.CHECK_OUT,\n"
                + "       W.ID WORKER_ID,\n"
                + "       W.NAME WORKER_NAME,\n"
                + "       W.SURNAME WORKER_SURNAME,\n"
                + "       RM.ID ROOM_ID,\n"
                + "       RM.ROOM_NUMBER,\n"
                + "       RM.ROOM_TYPE\n"
                + "  FROM REGISTER R \n"
                + "  INNER JOIN WORKER W \n"
                + "  ON R.WORKER_ID = W.ID\n"
                + "  INNER JOIN ROOM RM\n"
                + "  ON R.ROOM_ID = RM.ID\n"
                + " WHERE R.ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Register register = new Register();
                    register.setR(rs.getLong("r"));
                    register.setId(rs.getLong("ID"));
                    register.setName(rs.getString("NAME"));
                    register.setSurname(rs.getString("SURNAME"));
                    register.setDob(rs.getDate("DOB"));
                    register.setFatherName(rs.getString("FATHER_NAME"));
                    register.setAdultCount(rs.getString("ADULT_COUNT"));
                    register.setChildCount(rs.getString("CHILD_COUNT"));
                    register.setPhone(rs.getString("PHONE"));
                    register.setEmail(rs.getString("EMAIL"));
                    register.setCheck_in(rs.getDate("CHECK_IN"));
                    register.setCheck_out(rs.getDate("CHECK_OUT"));
                    Worker worker = new Worker();
                    worker.setId(rs.getLong("WORKER_ID"));
                    worker.setName(rs.getString("WORKER_NAME"));
                    worker.setSurname(rs.getString("WORKER_SURNAME"));
                    Room room = new Room();
                    room.setId(rs.getLong("ROOM_ID"));
                    room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                    register.setWorker(worker);
                    register.setRoom(room);
                    registerList.add(register);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return registerList;
    }

    @Override
    public boolean addRegister(Register register) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO REGISTER(ID,NAME,SURNAME,DOB,FATHER_NAME,ADULT_COUNT,CHILD_COUNT,PHONE,EMAIL,CHECK_IN,CHECK_OUT,WORKER_ID,ROOM_ID) "
                + "VALUES(REG_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, register.getName());
                ps.setString(2, register.getSurname());
                ps.setDate(3, new java.sql.Date(register.getDob().getTime()));
                ps.setString(4, register.getFatherName());
                ps.setString(5, register.getAdultCount());
                ps.setString(6, register.getChildCount());
                ps.setString(7, register.getPhone());
                ps.setString(8, register.getEmail());
                ps.setDate(9, new java.sql.Date(register.getCheck_in().getTime()));
                ps.setDate(10, new java.sql.Date(register.getCheck_out().getTime()));
                ps.setLong(11, register.getWorker().getId());
                ps.setLong(12, register.getRoom().getId());
                ps.execute();
                isAdded = true;
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return isAdded;
    }

    @Override
    public Register getRegisterById(Long registerId) throws Exception {
        Register register = new Register();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT R.ID,R.NAME,R.SURNAME,R.DOB,R.FATHER_NAME,R.ADULT_COUNT,R.CHILD_COUNT,R.PHONE,R.EMAIL,R.CHECK_IN,R.CHECK_OUT, R.WORKER_ID, R.ROOM_ID FROM REGISTER R"
                + " WHERE R.ACTIVE = 1 AND R.ID=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, registerId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    register.setId(rs.getLong("ID"));
                    register.setName(rs.getString("NAME"));
                    register.setSurname(rs.getString("SURNAME"));
                    register.setDob(rs.getDate("DOB"));
                    register.setFatherName(rs.getString("FATHER_NAME"));
                    register.setAdultCount(rs.getString("ADULT_COUNT"));
                    register.setChildCount(rs.getString("CHILD_COUNT"));
                    register.setPhone(rs.getString("PHONE"));
                    register.setEmail(rs.getString("EMAIL"));
                    register.setCheck_in(rs.getDate("CHECK_IN"));
                    register.setCheck_out(rs.getDate("CHECK_OUT"));
                    Worker worker = new Worker();
                    worker.setId(rs.getLong("WORKER_ID"));
                    Room room = new Room();
                    room.setId(rs.getLong("ROOM_ID"));
                    register.setWorker(worker);
                    register.setRoom(room);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return register;
    }

    @Override
    public boolean updateRegister(Register register) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = " UPDATE REGISTER SET NAME=?,SURNAME=?,DOB=?,FATHER_NAME=?,ADULT_COUNT=?,CHILD_COUNT=?,PHONE=?,EMAIL=?,CHECK_IN=?,CHECK_OUT=?,WORKER_ID=? "
                + " WHERE ID =?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, register.getName());
                ps.setString(2, register.getSurname());
                ps.setDate(3, (Date) register.getDob());
                ps.setString(4, register.getFatherName());
                ps.setString(5, register.getAdultCount());
                ps.setString(6, register.getChildCount());
                ps.setString(7, register.getPhone());
                ps.setString(8, register.getEmail());
                ps.setDate(9, (Date) register.getCheck_in());
                ps.setDate(10, (Date) register.getCheck_out());
                ps.setLong(11, register.getWorker().getId());
                ps.setLong(12, register.getId());
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
    public boolean deleteRegister(Long registerId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE REGISTER SET ACTIVE = 0" + " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, registerId);
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
    public List<Register> getRegisterByRoomId(Long roomId) throws Exception {
        List<Register> registerList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT DISTINCT RE.ID,RE.NAME, RE.SURNAME FROM PAYMENT P\n" +
                "INNER JOIN ROOM R ON P.ROOM_ID = R.ID\n" +
                "INNER JOIN REGISTER RE ON P.REG_ID = RE.ID\n" +
                "WHERE P.ACTIVE = 1 AND R.ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1,roomId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Register register = new Register();
                    register.setId(rs.getLong("ID"));
                    register.setName(rs.getString("NAME"));
                    register.setSurname(rs.getString("SURNAME"));
                    registerList.add(register);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return registerList;
    }
}
