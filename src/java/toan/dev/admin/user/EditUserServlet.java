/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package toan.dev.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import toan.dev.admin.BaseAdminServlet;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.dao.UserDao;
import toan.dev.data.model.User;

/**
 *
 * @author tranq
 */
public class EditUserServlet extends BaseAdminServlet {


    
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int userId = Integer.parseInt(request.getParameter("userId"));
    UserDao userDao = DatabaseDao.getInstance().getUserDao();
    User user = userDao.find(userId);
    
    request.setAttribute("user", user);
    request.getRequestDispatcher("admin/user/edit.jsp").include(request, response);
}


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        UserDao userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.find(userId);
        
        String email = request.getParameter("email");
        String pasword = request.getParameter("pasword");
        user.setEmail(email);
        user.setPassword(pasword);
        
        userDao.update(user);
        response.sendRedirect("IndexUserServlet");
    }

}