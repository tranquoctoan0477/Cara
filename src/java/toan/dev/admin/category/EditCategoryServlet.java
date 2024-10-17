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
import toan.dev.admin.BaseAdminServlet;
import toan.dev.data.dao.CategoryDao;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.model.Category;

/**
 *
 * @author tranq
 */
public class EditCategoryServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        Category category = categoryDao.find(categoryId);

        request.setAttribute("category", category);
        request.getRequestDispatcher("admin/category/edit.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        Category category = categoryDao.find(categoryId);

        String name = request.getParameter("name");
        String description = request.getParameter("thumbnail");

        // Kiểm tra điều kiện
        String errorMessage = null;

        if (name == null || name.trim().isEmpty()) {
            errorMessage = "Name cannot be empty.";
        } else if (description == null || description.trim().isEmpty()) {
            errorMessage = "Description cannot be empty.";
        }

        if (errorMessage != null) {
            // Lưu thông báo lỗi vào session và chuyển hướng lại trang edit
            HttpSession session = request.getSession();
            session.setAttribute("error", errorMessage);
            request.setAttribute("category", category); // Để giữ lại thông tin của danh mục
            request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
        } else {
            // Cập nhật danh mục
            category.setName(name);
            category.setDescription(description);
            categoryDao.update(category);

            // Chuyển hướng tới danh sách danh mục
            response.sendRedirect("IndexCategoryServlet");
        }
    }

}
