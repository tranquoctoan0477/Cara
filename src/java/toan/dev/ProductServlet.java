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
import toan.dev.util.Constants;
import toan.dev.data.dao.ProductImageDao;
import toan.dev.data.model.ProductImage;

/**
 *
 * @author tranq
 */
public class ProductServlet extends BaseServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Product product = productDao.find(productId);
        List<Product> newsProductsList = productDao.news(Constants.RELATED_NUMBER);
        // Lấy hình ảnh sản phẩm
        ProductImageDao productImageDao = DatabaseDao.getInstance().getProductImageDao();
        List<ProductImage> images = productImageDao.getProductImages(productId);
        
        request.setAttribute("newsProductsList", newsProductsList);
        request.setAttribute("product", product);
        request.setAttribute("productImages", images); // Thêm thuộc tính hình ảnh
        
        request.getRequestDispatcher("product.jsp").include(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
