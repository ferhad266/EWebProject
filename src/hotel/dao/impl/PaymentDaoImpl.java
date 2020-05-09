package hotel.dao.impl;

import hotel.dao.DbHelper;
import hotel.dao.PaymentDao;
import hotel.model.*;
import hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public List<Payment> getPaymentList() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,P.ID,\n"
                + "       R.ID REGISTER_ID,\n"
                + "       R.NAME REGISTER_NAME,\n"
                + "       R.SURNAME REGISTER_SURNAME,\n"
                + "       W.ID WORKER_ID,\n"
                + "       W.NAME WORKER_NAME,\n"
                + "       W.SURNAME WORKER_SURNAME,\n"
                + "       RM.ID ROOM_ID,\n"
                + "       RM.ROOM_NUMBER,\n"
                + "       P.AMOUNT,\n"
                + "       P.PAY_DATE\n"
                + "  FROM PAYMENT P\n"
                + "       INNER JOIN REGISTER R\n"
                + "          ON P.REG_ID = R.ID\n"
                + "       INNER JOIN WORKER W\n"
                + "          ON P.WORKER_ID = W.ID\n"
                + "       INNER JOIN ROOM RM\n"
                + "          ON P.ROOM_ID = RM.ID\n"
                + " WHERE P.ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("ID"));
                    Worker worker = new Worker();
                    Register register = new Register();
                    Room room = new Room();
                    payment.setR(rs.getLong("r"));
                    worker.setId(rs.getLong("WORKER_ID"));
                    worker.setName(rs.getString("WORKER_NAME"));
                    worker.setSurname(rs.getString("WORKER_SURNAME"));
                    payment.setWorker(worker);
                    register.setId(rs.getLong("REGISTER_ID"));
                    register.setName(rs.getString("REGISTER_NAME"));
                    register.setSurname(rs.getString("REGISTER_SURNAME"));
                    room.setId(rs.getLong("ROOM_ID"));
                    room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                    payment.setWorker(worker);
                    payment.setRoom(room);
                    payment.setRegister(register);
                    payment.setAmount(rs.getFloat("AMOUNT"));
                    payment.setPayDate(rs.getDate("PAY_DATE"));
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO PAYMENT(ID,AMOUNT,PAY_DATE,REG_ID,ROOM_ID,WORKER_ID)\n"
                + " VALUES(PAY_SEQ.NEXTVAL,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setFloat(1, payment.getAmount());
                ps.setDate(2, new java.sql.Date(payment.getPayDate().getTime()));
                ps.setLong(3, payment.getRegister().getId());
                ps.setLong(4, payment.getRoom().getId());
                ps.setLong(5, payment.getWorker().getId());
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
    public Payment getPaymentById(Long paymentId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID,P.REG_ID,P.ROOM_ID,P.WORKER_ID,P.AMOUNT,P.PAY_DATE FROM PAYMENT P"
                + " WHERE P.ACTIVE = 1 AND P.ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Worker worker = new Worker();
                    Register register = new Register();
                    Room room = new Room();
                    payment.setId(rs.getLong("ID"));
                    worker.setId(rs.getLong("WORKER_ID"));
                    payment.setWorker(worker);
                    register.setId(rs.getLong("REG_ID"));
                    room.setId(rs.getLong("ROOM_ID"));
                    payment.setWorker(worker);
                    payment.setRoom(room);
                    payment.setRegister(register);
                    payment.setAmount(rs.getFloat("AMOUNT"));
                    payment.setPayDate(rs.getDate("PAY_DATE"));
                }
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payment;
    }

    @Override
    public boolean updatePayment(Payment payment) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE PAYMENT SET AMOUNT=?,REG_ID=?,ROOM_ID=?,WORKER_ID=?" + " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setFloat(1, payment.getAmount());
                ps.setLong(2, payment.getRegister().getId());
                ps.setLong(3, payment.getRoom().getId());
                ps.setLong(4, payment.getWorker().getId());
                ps.setLong(5, payment.getId());
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
    public boolean deletePayment(Long paymentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE PAYMENT SET ACTIVE = 0" + " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentId);
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
    public List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT ROWNUM r,P.ID,\n"
                + "       R.ID REGISTER_ID,\n"
                + "       R.NAME REGISTER_NAME,\n"
                + "       R.SURNAME REGISTER_SURNAME,\n"
                + "       W.ID WORKER_ID,\n"
                + "       W.NAME WORKER_NAME,\n"
                + "       W.SURNAME WORKER_SURNAME,\n"
                + "       RM.ID ROOM_ID,\n"
                + "       RM.ROOM_NUMBER,\n"
                + "       P.AMOUNT,\n"
                + "       P.PAY_DATE\n"
                + "  FROM PAYMENT P\n"
                + "       INNER JOIN REGISTER R\n"
                + "          ON P.REG_ID = R.ID\n"
                + "       INNER JOIN WORKER W\n"
                + "          ON P.WORKER_ID = W.ID\n"
                + "       INNER JOIN ROOM RM\n"
                + "          ON P.ROOM_ID = RM.ID\n"
                + " WHERE P.ACTIVE = 1 ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {

                if (advancedSearch.getRoomId() != 0) {
                    sql += "RM.ID = " + advancedSearch.getRoomId();
                }

                if (advancedSearch.getRegisterId() != 0) {
                    sql += "R.ID = " + advancedSearch.getRegisterId();
                }

                if(advancedSearch.getMinAmount() != null && !advancedSearch.getMinAmount().isEmpty()){
                    sql+=" AND P.AMOUNT >= "+Float.parseFloat(advancedSearch.getMinAmount());
                }

                if (advancedSearch.getMaxAmount() != null && !advancedSearch.getMaxAmount().isEmpty()){
                    sql+=" AND P.AMOUNT <= "+Float.parseFloat(advancedSearch.getMaxAmount());
                }

                if (advancedSearch.getBeginDate() != null && !advancedSearch.getBeginDate().isEmpty()){
                    sql+=" AND P.PAY_DATE >= TO_DATE('"+ new java.sql.Date(df.parse(advancedSearch.getBeginDate()).getTime())+"','YYYY-MM-DD')";
                }

                if (advancedSearch.getEndDate() != null && !advancedSearch.getEndDate().isEmpty()){
                    sql+=" AND P.PAY_DATE <= TO_DATE('"+ new java.sql.Date(df.parse(advancedSearch.getEndDate()).getTime())+"','YYYY-MM-DD')";

                }

                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("ID"));
                    Worker worker = new Worker();
                    Register register = new Register();
                    Room room = new Room();
                    payment.setR(rs.getLong("r"));
                    worker.setId(rs.getLong("WORKER_ID"));
                    worker.setName(rs.getString("WORKER_NAME"));
                    worker.setSurname(rs.getString("WORKER_SURNAME"));
                    payment.setWorker(worker);
                    register.setId(rs.getLong("REGISTER_ID"));
                    register.setName(rs.getString("REGISTER_NAME"));
                    register.setSurname(rs.getString("REGISTER_SURNAME"));
                    room.setId(rs.getLong("ROOM_ID"));
                    room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                    payment.setWorker(worker);
                    payment.setRoom(room);
                    payment.setRegister(register);
                    payment.setAmount(rs.getFloat("AMOUNT"));
                    payment.setPayDate(rs.getDate("PAY_DATE"));
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }
}
