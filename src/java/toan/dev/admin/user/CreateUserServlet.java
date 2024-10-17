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
public class CreateUserServlet extends BaseAdminServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin/user/create.jsp").include(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String role = request.getParameter("role");
        UserDao userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.findByEmail(email);
        if (email.isEmpty() || password.isEmpty() || repassword.isEmpty() || role.isEmpty()) {
            session.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
            request.getRequestDispatcher("admin/user/create.jsp").include(request, response);
            
        }else if(user !=null){
            session.setAttribute("error", "Email đã tồn tại");
            request.getRequestDispatcher("admin/user/create.jsp").include(request, response);
        }else if(!password.equals((repassword))){
            session.setAttribute("error", "Mật khẩu không trùng");
            request.getRequestDispatcher("admin/user/create.jsp").include(request, response);
        }else{
            user = new User(email, password, role);
            userDao.insert(user);
            response.sendRedirect("IndexUserServlet");
        }
    }

}