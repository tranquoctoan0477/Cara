/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package toan.dev;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.dao.OrderDao;
import toan.dev.data.dao.OrderItemDao;
import toan.dev.data.model.Order;
import toan.dev.data.model.OrderItem;
import toan.dev.data.model.User;
import toan.dev.util.StringHelper;

/**
 *
 * @author tranq
 */
public class CheckoutServlet extends BaseServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("LoginServlet");
        }else{
            proccessCheckout(request, user);
            response.sendRedirect("CartServlet");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void proccessCheckout(HttpServletRequest request, User user) {
    OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
    String code =  StringHelper.randomString(10);
    Order order = new Order(code, "pending", user.getId());
    orderDao.insert(order);
    
    // Tìm đơn hàng vừa được lưu bằng mã code
    order = orderDao.findByCode(code);
    if (order == null) {
        // Xử lý khi không tìm thấy đơn hàng với mã code
        System.out.println("Không tìm thấy đơn hàng với mã: " + code);
        return;
    }
    
    HttpSession session = request.getSession();
    List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
    
    OrderItemDao orderItemDao = DatabaseDao.getInstance().getOrderItemDao();
    if (cart != null) {
        for (OrderItem orderItem : cart) {
            orderItem.setOrderId(order.getId());
            orderItemDao.insert(orderItem);
        }
    }
    
    session.setAttribute("message", "Checkout Success");
    session.removeAttribute("cart");
}


}
