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
import java.util.ArrayList;
import java.util.List;
import toan.dev.data.model.OrderItem;
import toan.dev.util.Helper;

/**
 *
 * @author tranq
 */
public class CartServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<OrderItem>();
        }
        
        double total = Helper.total(cart);
        request.setAttribute("cart", cart);
        request.setAttribute("total", Helper.total(cart));
        session.setAttribute("total", total); // Cập nhật tổng tiền vào session
        
        request.getRequestDispatcher("cart.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createOrder(request);
                break;
            case "update":
                updateOrder(request);
                break;
            case "delete":
                deleteOrder(request);
                break;
            default:
                throw new AssertionError();
        }

        // Chuyển hướng về giỏ hàng
        response.sendRedirect("CartServlet");
    }

    private void createOrder(HttpServletRequest request) {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        double price = Double.parseDouble(request.getParameter("price"));

        OrderItem orderItem = new OrderItem(quantity, price, 0, productId);

        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean isExistInCart = false;

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        for (OrderItem ord : cart) {
            if (ord.getProductId() == productId) {
                // Nếu đã tồn tại, cập nhật số lượng
                ord.setQuantity(ord.getQuantity() + quantity);
                isExistInCart = true;
                break;
            }
        }

        // Nếu sản phẩm chưa tồn tại, thêm vào giỏ hàng
        if (!isExistInCart) {
            cart.add(orderItem);
        }

        // Cập nhật giỏ hàng vào session và tính tổng tiền
        session.setAttribute("cart", cart);
        updateCartTotal(session); // Cập nhật tổng tiền
    }

    private void updateOrder(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart != null && !cart.isEmpty()) {
            for (OrderItem ord : cart) {
                if (ord.getProductId() == productId) {
                    ord.setQuantity(quantity); // Cập nhật số lượng sản phẩm
                    break;
                }
            }
        }
        session.setAttribute("cart", cart);
        updateCartTotal(session); // Cập nhật tổng tiền sau khi cập nhật sản phẩm
    }

    private void deleteOrder(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart != null) {
            for (int i = 0; i < cart.size(); i++) {
                OrderItem ord = cart.get(i);
                if (ord.getProductId() == productId) {
                    cart.remove(ord); // Xóa sản phẩm khỏi giỏ hàng
                    break;
                }
            }
        }
        session.setAttribute("cart", cart);
        updateCartTotal(session); // Cập nhật tổng tiền sau khi xóa sản phẩm
    }

    // Hàm tính tổng tiền giỏ hàng và cập nhật vào session
    private void updateCartTotal(HttpSession session) {
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart != null) {
            double total = Helper.total(cart); // Tính lại tổng tiền
            session.setAttribute("total", total); // Cập nhật tổng tiền vào session
        }
    }

}
