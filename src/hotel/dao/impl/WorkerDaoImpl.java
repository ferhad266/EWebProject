package hotel.dao.impl;

import hotel.dao.DbHelper;
import hotel.dao.WorkerDao;
import hotel.model.Worker;
import hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkerDaoImpl implements WorkerDao {
    @Override
    public List<Worker> getWorkerList() throws Exception {
        List<Worker> workerList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,NAME,SURNAME,DOB,FATHER_NAME,PHONE,EMAIL FROM WORKER\r\n" + " WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Worker worker = new Worker();
                    worker.setR(rs.getLong("r"));
                    worker.setId(rs.getLong("ID"));
                    worker.setName(rs.getString("NAME"));
                    worker.setSurname(rs.getString("SURNAME"));
                    worker.setDob(rs.getDate("DOB"));
                    worker.setFatherName(rs.getString("FATHER_NAME"));
                    worker.setPhone(rs.getString("PHONE"));
                    worker.setEmail(rs.getString("EMAIL"));
                    workerList.add(worker);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return workerList;
    }

    @Override
    public boolean addWorker(Worker worker) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO WORKER(ID,NAME,SURNAME,DOB,FATHER_NAME,PHONE,EMAIL)\r\n"
                + " VALUES(WORKER_SEQ.NEXTVAL,?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, worker.getName());
                ps.setString(2, worker.getSurname());
                ps.setDate(3, new java.sql.Date(worker.getDob().getTime()));
                ps.setString(4, worker.getFatherName());
                ps.setString(5, worker.getPhone());
                ps.setString(6, worker.getEmail());
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
    public Worker getWorkerById(Long workerId) throws Exception {
        Worker worker = new Worker();
        List<Worker> workerList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r, ID,NAME,SURNAME,DOB,FATHER_NAME,PHONE,EMAIL FROM WORKER\r\n"
                + " WHERE ACTIVE = 1 AND ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, workerId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    worker.setR(rs.getLong("r"));
                    worker.setId(rs.getLong("ID"));
                    worker.setName(rs.getString("NAME"));
                    worker.setSurname(rs.getString("SURNAME"));
                    worker.setDob(rs.getDate("DOB"));
                    worker.setFatherName(rs.getString("FATHER_NAME"));
                    worker.setPhone(rs.getString("PHONE"));
                    worker.setEmail(rs.getString("EMAIL"));
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return worker;
    }

    @Override
    public List<Worker> searchWorkerData(String keyword) throws Exception {
        List<Worker> workerList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,NAME,SURNAME,DOB,FATHER_NAME,PHONE,EMAIL FROM WORKER W WHERE W.ACTIVE = 1\r\n"
                + " AND LOWER(W.NAME) LIKE LOWER(?) OR LOWER(W.SURNAME) LIKE LOWER(?) OR LOWER(W.FATHER_NAME) LIKE LOWER(?) OR LOWER(W.PHONE) LIKE LOWER(?)";
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
                    Worker worker = new Worker();
                    worker.setR(rs.getLong("r"));
                    worker.setId(rs.getLong("ID"));
                    worker.setName(rs.getString("NAME"));
                    worker.setSurname(rs.getString("SURNAME"));
                    worker.setDob(rs.getDate("DOB"));
                    worker.setFatherName(rs.getString("FATHER_NAME"));
                    worker.setPhone(rs.getString("PHONE"));
                    worker.setEmail(rs.getString("EMAIL"));
                    workerList.add(worker);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return workerList;
    }

    @Override
    public boolean updateWorker(Worker worker) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE WORKER SET NAME=?,SURNAME=?,DOB=?,FATHER_NAME=?,PHONE=?,EMAIL=?" + " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, worker.getName());
                ps.setString(2, worker.getSurname());
                ps.setDate(3, new java.sql.Date(worker.getDob().getTime()));
                ps.setString(4, worker.getFatherName());
                ps.setString(5, worker.getPhone());
                ps.setString(6, worker.getEmail());
                ps.setLong(7, worker.getId());
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
    public boolean deleteWorker(Long workerId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE WORKER SET ACTIVE = 0" + " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, workerId);
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
}
