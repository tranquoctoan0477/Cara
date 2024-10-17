/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package toan.dev;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.dao.ProductDao;
import toan.dev.data.model.Product;
import toan.dev.util.Constants;

/**
 *
 * @author tranq
 */
public class ShopServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        List<Product> productList; // Khai báo danh sách sản phẩm
        int total = productDao.findAll().size(); // Lấy tổng số sản phẩm từ database
        int numberPage = (int) Math.ceil((double) total / Constants.PER_PAGE); // Tính số trang

        int page = 1; // Mặc định là trang 1
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán giá trị from (offset) dựa trên page
        int from = (page - 1) * Constants.PER_PAGE;

        // Kiểm tra để đảm bảo 'from' không vượt quá tổng số sản phẩm
        if (from >= total) {
            productList = new ArrayList<>(); // Nếu không có sản phẩm, tạo danh sách trống
        } else {
            productList = productDao.getProducts(from, Constants.PER_PAGE);
        }

        // Set thuộc tính cho request
        request.setAttribute("total", total);
        request.setAttribute("page", page);
        request.setAttribute("numberPage", numberPage);

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("shop.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
