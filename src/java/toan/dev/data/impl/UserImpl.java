package toan.dev.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

import toan.dev.data.dao.UserDao;
import toan.dev.data.driver.MySQLDriver;
import toan.dev.data.model.User;

public class UserImpl implements UserDao {

    Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public boolean insert(User user) {
        String sql = "INSERT INTO USERS(ID, EMAIL, PASSWORD, ROLE) VALUES(null, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            int rowsAffected = stmt.executeUpdate(); // Sử dụng executeUpdate để trả về số dòng bị ảnh hưởng
            return rowsAffected > 0; // Nếu có dòng bị ảnh hưởng, trả về true
        } catch (SQLException e) {
            e.printStackTrace(); // In ra ngoại lệ nếu có
        }
        return false; // Nếu có lỗi xảy ra, trả về false
    }

    @Override
    public boolean update(User user) {
        // TODO Auto-generated method stub
        String sql = "UPDATE USERS SET email = ? ,password = ?, role = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getId());
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int userId) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM USERS WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User find(int userId) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(userId, email, password, role);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByEmail(String userEmail) {
        // Câu truy vấn SQL để tìm người dùng theo email
        String sql = "SELECT * FROM USERS WHERE email = ?";
        try {
            // Chuẩn bị câu lệnh SQL với tham số email
            PreparedStatement stmt = con.prepareStatement(sql);
            // Gán tham số email (chuỗi) vào vị trí thứ nhất trong câu truy vấn SQL
            stmt.setString(1, userEmail);
            // Thực thi câu lệnh truy vấn
            ResultSet rs = stmt.executeQuery();
            // Nếu có kết quả trả về từ cơ sở dữ liệu
            if (rs.next()) {
                // Lấy các giá trị từ kết quả truy vấn
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                int id = rs.getInt("id");  // Giả định bảng có cột userId
                // Trả về một đối tượng User mới với các giá trị đã lấy
                return new User(id, email, password, role);
            }
        } catch (SQLException e) {
            // In ra lỗi nếu có ngoại lệ xảy ra
            e.printStackTrace();
        }
        // Trả về null nếu không tìm thấy người dùng
        return null;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE ID > ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, 0);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                users.add(new User(id, email, password, role));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User checkLogin(String email, String password) {
        String sql = "SELECT * FROM USERS WHERE email = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Lấy mật khẩu đã mã hóa từ cơ sở dữ liệu
                String hashedPassword = rs.getString("password");
                // Kiểm tra mật khẩu nhập vào có khớp với mật khẩu đã mã hóa không
                if (BCrypt.checkpw(password, hashedPassword)) {
                    // Nếu khớp, trả về đối tượng User
                    return new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Trả về null nếu không khớp thông tin đăng nhập
        return null;
    }

}
