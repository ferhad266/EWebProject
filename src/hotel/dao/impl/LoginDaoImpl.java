/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.dao.impl;

import hotel.dao.DbHelper;
import hotel.dao.LoginDao;
import hotel.model.LoginUser;
import hotel.model.Role;
import hotel.util.JdbcUtility;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ferha
 */
public class LoginDaoImpl implements LoginDao {

    @Override
    public LoginUser login(String username, String password) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT LU.ID,LU.USERNAME,LU.NAME,LU.SURNAME,R.ID ROLE_ID,R.ROLE_NAME FROM LOGIN_USER LU\n"
                + "INNER JOIN ROLE R ON LU.ROLE_ID = R.ID\n"
                + "WHERE LU.ACTIVE = 1 AND LU.USERNAME = ? and LU.PASSWORD = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                    loginUser.setUsername(rs.getString("USERNAME"));
                    loginUser.setName(rs.getString("NAME"));
                    loginUser.setSurname(rs.getString("SURNAME"));
                    Role role= new Role();
                    role.setId(rs.getLong("ROLE_ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    loginUser.setRole(role);
                }else{
                    loginUser = null;
                }
            } else {
                System.out.println("Connetion is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return loginUser;
    }

    @Override
    public LoginUser checkEmail(String email) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM LOGIN_USER LU\n"
                + "WHERE LU.ACTIVE = 1 AND LU.USERNAME = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if(rs.next()){
                    loginUser.setId(rs.getLong("ID"));
                    loginUser.setToken(rs.getString("TOKEN"));
                }else{
                    loginUser = null;
                }
            } else {
                System.out.println("Connetion is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return loginUser;
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LOGIN_USER SET PASSWORD = ?" + " WHERE TOKEN = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, token);
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
    public boolean updateToken(String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LOGIN_USER SET TOKEN = ?" + " WHERE TOKEN = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, null);
                ps.setString(2, token);
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
    public boolean updateTokenById(String token, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LOGIN_USER SET TOKEN = ?" + " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);
                ps.setLong(2, id);
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
