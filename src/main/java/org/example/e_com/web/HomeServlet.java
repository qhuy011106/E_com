package org.example.e_com.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //chuan bi du lieu
        String appName ="E_com System";
        String slogan = "Mua sắm online - Giao hàng tận nơi";
        Date currentTime = new Date();
        //dua lu lieu vao rq(de jsp dung)
        req.setAttribute("appname", appName);
        req.setAttribute("slogan", slogan);
        req.setAttribute("currentTime", currentTime);
        //forward den jsp de hien thi
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,resp);
    }
}
