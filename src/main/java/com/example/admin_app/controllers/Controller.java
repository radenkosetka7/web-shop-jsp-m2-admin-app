package com.example.admin_app.controllers;

import com.example.admin_app.beans.*;
import com.example.admin_app.dto.Attribute;
import com.example.admin_app.dto.Category;
import com.example.admin_app.dto.CustomUser;
import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;

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
    private static final String ERROR_PAGE = "/WEB-INF/pages/errorPage.jsp";
    private static final String USERS = "/WEB-INF/pages/users.jsp";
    private static final String CATEGORIES = "/WEB-INF/pages/categories.jsp";
    private static final String ADD_USER = "/WEB-INF/pages/addUser.jsp";
    private static final String UPDATE_USER = "/WEB-INF/pages/updateUser.jsp";
    private static final String UPDATE_CATEGORY = "/WEB-INF/pages/updateCategory.jsp";
    private static final String VIEW_ATTRIBUTES = "/WEB-INF/pages/attributes.jsp";



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
            resp.sendRedirect(req.getContextPath() + "/admin-controller");
            return;
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
                AttributeBean attributeBean = new AttributeBean();
                session.setAttribute("logBean", logBean);
                session.setAttribute("userBean", userBean);
                session.setAttribute("categoryBean", categoryBean);
                session.setAttribute("attributeBean",attributeBean);
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
                UserBean userBean=(UserBean) session.getAttribute("userBean");
                CategoryBean categoryBean=(CategoryBean) session.getAttribute("categoryBean");
                AttributeBean attributeBean=(AttributeBean) session.getAttribute("attributeBean");
                if(action.equals("users"))
                {
                    address=USERS;
                }
                else if(action.equals("addUser"))
                {
                    address=ADD_USER;
                    if(req.getParameter("submit") != null)
                    {
                        String role=req.getParameter("role");
                        Role roleType=Role.getKey(Integer.parseInt(role));
                        CustomUser customUser = new CustomUser(0,req.getParameter("name"),req.getParameter("surname"),
                                req.getParameter("city"),req.getParameter("username"),req.getParameter("avatar"),
                                req.getParameter("password"),req.getParameter("mail"),Status.ACTIVE, roleType);

                        if(userBean.insertUser(customUser))
                        {
                            address=USERS;
                        }
                    }

                }
                else if (action.equals("updateUser"))
                {
                    address=UPDATE_USER;
                    Integer id=Integer.parseInt(req.getParameter("id"));
                    CustomUser customUser=userBean.getUserById(id);
                    userBean.setCustomUser(customUser);

                    if(req.getParameter("submit")!=null)
                    {
                        String role=req.getParameter("role");
                        String status=req.getParameter("status");
                        CustomUser customUserUpdate = new CustomUser(id,req.getParameter("name"),req.getParameter("surname"),
                                req.getParameter("city"),req.getParameter("username"),req.getParameter("avatar"),
                                req.getParameter("password"),req.getParameter("mail"),Status.getKey(Integer.parseInt(status)), Role.getKey(Integer.parseInt(role)));

                        if(userBean.updateUser(customUserUpdate))
                        {
                            address=USERS;
                        }
                    }
                }
                else if(action.equals("deleteUser"))
                {
                    userBean.updateUserStatus(Integer.parseInt(req.getParameter("id")),Status.getValue(Status.BLOCKED));
                    address=USERS;
                }
                else if(action.equals("categories"))
                {
                    address=CATEGORIES;
                }
                else if(action.equals("addCategory"))
                {

                }
                else if(action.equals("updateCategory"))
                {
                    address=UPDATE_CATEGORY;
                    Integer id=Integer.parseInt(req.getParameter("id"));
                    Category category=categoryBean.getAllCategoryById(id);
                    categoryBean.setCategory(category);
                }
                else if(action.equals("deleteCategory"))
                {
                    Integer id=Integer.parseInt(req.getParameter("id"));
                    Category category=categoryBean.getAllCategoryById(id);
                    categoryBean.deleteCategory(category);
                    address=CATEGORIES;
                }
                else if(action.equals("logs"))
                {
                    address=LOGGER;
                }
                else if(action.equals("deleteAttribute"))
                {
                    Integer id=Integer.parseInt(req.getParameter("idAttr"));
                    attributeBean.deleteAttribute(id);
                    Integer catId=Integer.parseInt(req.getParameter("categoryId"));
                    Category category=categoryBean.getAllCategoryById(catId);
                    categoryBean.setCategory(category);
                    address=VIEW_ATTRIBUTES;
                }
                else if(action.equals("viewAttributes"))
                {
                    address=VIEW_ATTRIBUTES;
                    Integer id=Integer.parseInt(req.getParameter("id"));
                    if(id!=null) {
                        Category category = categoryBean.getAllCategoryById(id);
                        categoryBean.setCategory(category);
                    }
                }
                else if(action.equals("addAttribute"))
                {

                }
                else
                {
                    address=ERROR_PAGE;
                }
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
