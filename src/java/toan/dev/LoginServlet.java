package toan.dev;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import toan.dev.data.dao.DatabaseDao;
import toan.dev.data.dao.UserDao;
import toan.dev.data.model.User;

public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect("HomeServlet");
        } else {
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.checkLogin(email, password);

        if (user == null) {
            session.setAttribute("error", "Login Failed");
            response.sendRedirect("LoginServlet");
        } else {
            // Lưu thông tin người dùng vào session
            session.setAttribute("user", user);

            // Kiểm tra xem có redirectPage trong session không
            String redirectPage = (String) session.getAttribute("redirectPage");
            if (redirectPage != null) {
                session.removeAttribute("redirectPage"); // Xóa redirectPage khỏi session sau khi dùng
                response.sendRedirect(redirectPage);     // Chuyển về trang được lưu trước khi đăng nhập
            } else {
                if (user.getRole().equals("admin")) {
                    response.sendRedirect("DashboardServlet");
                } else {
                    response.sendRedirect("HomeServlet");
                }
            }
        }
    }
}
