/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package toan.dev.admin.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import toan.dev.admin.BaseAdminServlet;
import toan.dev.data.dao.CategoryDao;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.dao.UserDao;
import toan.dev.data.model.Category;
import toan.dev.data.model.User;

/**
 *
 * @author tranq
 */
public class CreateCategoryServlet extends BaseAdminServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin/category/create.jsp").include(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String thumbnail = request.getParameter("thumbnail");
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        
        if (name.isEmpty() || thumbnail.isEmpty()) {
            session.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
            request.getRequestDispatcher("admin/category/create.jsp").include(request, response);
            
        }else{
            Category category = new Category(name, thumbnail);
            categoryDao.insert(category);
            response.sendRedirect("IndexCategoryServlet");
        }
    }

}