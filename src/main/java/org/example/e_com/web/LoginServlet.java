package org.example.e_com.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.e_com.model.User;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //lay du lieu tu form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //kiem tra dang nhap
        User user = userService.login(username, password);
        if(user != null){
            //dang nhap thanh cong
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            //chuyen ve trang home
            resp.sendRedirect((req.getContextPath() + "/home"));
        }else{
            req.setAttribute("error", "Ten đăng nhập hoặc mật khẩu không đúng");
            req.setAttribute("username", username);
            //forward lai login.jsp de hien thi loi
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
