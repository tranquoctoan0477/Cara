/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package toan.dev;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.dao.ProductDao;
import toan.dev.data.model.Product;

/**
 *
 * @author tranq
 */
public class SearchServlet extends BaseServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("search.jsp").include(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tham số keyword từ request
        String keyword = request.getParameter("keyword");
        
        // Sử dụng ProductDao để tìm sản phẩm theo từ khóa
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        List<Product> productList = productDao.findByName(keyword);
        
        // Gán keyword và danh sách sản phẩm vào request attribute
        request.setAttribute("keyword", keyword);
        request.setAttribute("productList", productList);
        
        // Chuyển đến trang search.jsp để hiển thị kết quả tìm kiếm
        request.getRequestDispatcher("search.jsp").include(request, response);
    
    }

}
