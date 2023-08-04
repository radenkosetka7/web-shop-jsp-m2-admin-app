package com.example.admin_app.controllers;

import com.example.admin_app.beans.AdminBean;
import com.example.admin_app.beans.CategoryBean;
import com.example.admin_app.beans.LoggerBean;
import com.example.admin_app.beans.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "controller",value = "/admin-controller")
public class Controller extends HttpServlet {


    private static final String LOGIN = "/WEB-INF/pages/login.jsp";
    private static final String LOGGER = "/WEB-INF/pages/home.jsp";


    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        String address = LOGIN;
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        session.setAttribute("notification","");
        if(action == null || action.equals(""))
        {
            address=LOGIN;
        }
        else if(action.equals("logout"))
        {
            session.invalidate();
        }
        else if(action.equals("login"))
        {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            AdminBean adminBean = new AdminBean();
            if(adminBean.getUser(username,password) != null)
            {
                session.setAttribute("adminBean", adminBean);
                LoggerBean logBean = new LoggerBean();
                UserBean userBean = new UserBean();
                CategoryBean categoryBean = new CategoryBean();
                session.setAttribute("logBean", logBean);
                session.setAttribute("userBean", userBean);
                session.setAttribute("categoryBean", categoryBean);
                address = LOGGER;
            }
            else
            {
                session.setAttribute("notification","Invalid credentials");
            }


        }
        else
        {
            AdminBean adminBean=(AdminBean) session.getAttribute("adminBean");
            if(adminBean == null || !adminBean.getLoggedIn())
            {
                address=LOGIN;
            }
            else
            {

            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }
}
