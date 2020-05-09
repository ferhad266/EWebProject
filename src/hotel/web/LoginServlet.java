package hotel.web;

import hotel.dao.LoginDao;
import hotel.dao.impl.LoginDaoImpl;
import hotel.model.LoginUser;
import hotel.service.LoginService;
import hotel.service.impl.LoginServiceImpl;
import hotel.util.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginServlet", urlPatterns = "/ls")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);
        String address = null;
        String action = null;
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }

            if (action.equalsIgnoreCase("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    LoginUser loginUser = loginService.login(username, password);
                    if (loginUser != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("login", loginUser);
                        address = "index.jsp";
                    } else {
                        request.setAttribute("invalid", "Username or password is invalid!");
                        address = "login.jsp";
                    }
                } else {
                    request.setAttribute("invalid", "Username or password is empty!");
                    address = "login.jsp";
                }
            } else if (action.equalsIgnoreCase("forgotPassword")) {
                address = "forgotPassword.jsp";
            } else if (action.equalsIgnoreCase("forgot")) {
                String email = request.getParameter("email");
                String token = UUID.randomUUID().toString();
                if (email != null && !email.isEmpty()) {
                    LoginUser loginUser = loginService.checkEmail(email);
                    if (loginUser != null) {
                        boolean isUpdated = loginService.updateTokenById(token, loginUser.getId());
                        if (isUpdated) {
                            String text = "Change your password with this link: http://localhost:8088/EWebProject_war_exploded/ls?action=changePassword&token=" + token;
                            boolean isSend = Utility.sendMail(email, "Forgot Password", text);
                            if (isSend) {
                                request.setAttribute("success", "The Mail has been successfully sended!");
                            } else {
                                request.setAttribute("invalid", "Problem! The Mail has not been successfully sended!");
                            }
                        } else {
                            request.setAttribute("invalid", "Problem! Update token is failed!");
                        }
                    } else {
                        request.setAttribute("invalid", "Problem! Email not found!");
                    }
                } else {
                    request.setAttribute("invalid", "Email is empty!");
                }
                address = "forgotPassword.jsp";
            } else if (action.equalsIgnoreCase("changePassword")) {
                String token = request.getParameter("token");
                request.setAttribute("token", token);
                address = "changePassword.jsp";
            } else if (action.equalsIgnoreCase("change")) {
                String token = request.getParameter("token");
                String password = request.getParameter("password");
                String repeatPassword = request.getParameter("repeatPassword");
                if (password.equals(repeatPassword)) {
                    boolean isChanged = loginService.changePassword(password, token);
                    if (isChanged) {
                        boolean isChangeToken = loginService.updateToken(token);
                        if (isChangeToken){
                            request.setAttribute("success","Your password has been successfully changed!");
                            address = "login.jsp";
                        }else {
                            request.setAttribute("invalid","Problem! Update token is failed!");
                            address = "changePassword.jsp";
                        }
                    } else {
                        request.setAttribute("invalid", "Problem! Password has not been change successfully!");
                        address = "changePassword.jsp";
                    }
                } else {
                    request.setAttribute("invalid", "Passwords are not equals!");
                    address = "changePassword.jsp";
                }
            }
            if (address != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
