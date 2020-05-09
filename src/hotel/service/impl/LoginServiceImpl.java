/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.service.impl;

import hotel.dao.LoginDao;
import hotel.model.LoginUser;
import hotel.service.LoginService;

/**
 *
 * @author ferha
 */
public class LoginServiceImpl implements LoginService {
    
    private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public LoginUser login(String username, String password) throws Exception {
        return loginDao.login(username, password);
    }

    @Override
    public LoginUser checkEmail(String email) throws Exception {
        return loginDao.checkEmail(email);
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        return loginDao.changePassword(password, token);
    }

    @Override
    public boolean updateToken(String token) throws Exception {
        return loginDao.updateToken(token);
    }

    @Override
    public boolean updateTokenById(String token, Long id) throws Exception {
        return updateTokenById(token, id);
    }

}
