/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.dao;

import hotel.model.LoginUser;

/**
 * @author ferha
 */
public interface LoginDao {

    LoginUser login(String username, String password) throws Exception;

    LoginUser checkEmail(String email) throws Exception;

    boolean changePassword(String password, String token) throws Exception;

    boolean updateToken(String token) throws Exception;

    boolean updateTokenById(String token, Long id) throws Exception;


}
