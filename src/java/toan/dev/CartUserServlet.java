package toan.dev;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.model.Order;
import toan.dev.data.dao.OrderDao;
import toan.dev.data.model.User;

public class CartUserServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Lấy thông tin người dùng từ session
        User currentUser = (User) session.getAttribute("user");

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (currentUser == null) {
            // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("LoginServlet");
            return;
        }

        // Nếu đã đăng nhập, lấy danh sách đơn hàng của người dùng
        OrderDao orderDAO = DatabaseDao.getInstance().getOrderDao();
        List<Order> orderList = orderDAO.findByUser(currentUser.getId()); // Sử dụng currentUser.getId()

        // Đặt danh sách đã lấy làm thuộc tính để hiển thị trên trang JSP
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("cartuser.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu có xử lý POST nào đó, hãy thêm vào đây
    }
}
