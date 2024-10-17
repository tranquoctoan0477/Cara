package toan.dev.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import toan.dev.data.dao.ProductDao;
import toan.dev.data.driver.MySQLDriver;
import toan.dev.data.model.Product;
import toan.dev.data.model.OrderItem;

public class ProductImpl implements ProductDao {

    Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public boolean insert(Product product) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO PRODUCTS(ID, NAME, DESCRIPTION, THUMBNAIL, PRICE, QUANTITY, CATEGORY_ID) VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getThumbnail());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.setInt(6, product.getCategoryId());
            
            stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        // TODO Auto-generated method stub
        String sql = "UPDATE PRODUCTS SET name = ?, description = ?, thumbnail = ?, price = ?, quantity = ?, category_id = ?, created_at = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getThumbnail());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.setInt(6, product.getCategoryId());
            stmt.setTimestamp(7, product.getCreatedAt());
            stmt.setInt(8, product.getId());
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM PRODUCTS WHERE ID =?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product find(int id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM PRODUCTS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                Timestamp created_at = rs.getTimestamp("created_at");
                return new Product(id, name, description, thumbnail, price, quantity, categoryId, created_at);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        List<Product> prodList = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                Timestamp created_at = rs.getTimestamp("created_at");
                prodList.add(new Product(id, name, description, thumbnail, price, quantity, categoryId, created_at));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prodList;
    }

    @Override
    public List<Product> findByName(String key) {
        String sql = "SELECT * FROM PRODUCTS WHERE name LIKE ?";
        List<Product> productList = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + key + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                Timestamp created_at = rs.getTimestamp("created_at");

                Product product = new Product(id, name, description, thumbnail, price, quantity, categoryId, created_at);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> hot(int limit) {
        List<Product> prodList = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS ORDER BY VIEW DESC LIMIT ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, limit);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int view = rs.getInt("view");
                int categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");

                // Thêm Product vào danh sách
                prodList.add(new Product(id, name, description, thumbnail, price, quantity, categoryId, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodList;
    }

    @Override
    public List<Product> news(int limit) {
        List<Product> prodList = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS ORDER BY VIEW DESC LIMIT ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, limit);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int view = rs.getInt("view");
                int categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");

                // Thêm Product vào danh sách
                prodList.add(new Product(id, name, description, thumbnail, price, quantity, categoryId, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodList;
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS WHERE category_id = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoryId); // Truyền categoryId vào câu lệnh SQL
            rs = stmt.executeQuery();

            // Lặp qua từng bản ghi kết quả và ánh xạ sang đối tượng Product
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng các tài nguyên (ResultSet và PreparedStatement)
            closeResources(rs, stmt);
        }

        return productList; // Trả về danh sách sản phẩm theo danh mục
    }

    // Phương thức hỗ trợ để đóng tài nguyên (ResultSet và PreparedStatement)
    private void closeResources(ResultSet rs, PreparedStatement stmt) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức hỗ trợ để chuyển ResultSet thành đối tượng Product
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String thumbnail = rs.getString("thumbnail");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity");
        int view = rs.getInt("view");  // Lấy giá trị view từ ResultSet
        int categoryId = rs.getInt("category_id");
        Timestamp createdAt = rs.getTimestamp("created_at");

        // Gọi constructor với đầy đủ 9 tham số, bao gồm view
        return new Product(id, name, description, thumbnail, price, quantity, view, categoryId, createdAt);
    }

    @Override
    public List<Product> filter(int categoryId, String propertyName, String order) {
        List<Product> prodList = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS WHERE category_id = ? ORDER BY " + propertyName + " " + order;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int view = rs.getInt("view");
                Timestamp created_at = rs.getTimestamp("created_at");
                prodList.add(new Product(id, name, description, thumbnail, price, quantity, categoryId, view, created_at));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodList;
    }

    @Override
    public List<Product> getProducts(int from, int amount) {
        // TODO Auto-generated method stub
        List<Product> prodList = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS LIMIT ?, ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, from);
            stmt.setInt(2, amount);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                Timestamp created_at = rs.getTimestamp("created_at");
                prodList.add(new Product(id, name, description, thumbnail, price, quantity, categoryId, created_at));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prodList;
    }
    
    @Override
public Product findByProduct(int productId) {
    // Câu truy vấn SQL để lấy thông tin từ bảng PRODUCTS
    String sql = "SELECT * FROM PRODUCTS WHERE ID = ?";
    try {
        // Chuẩn bị câu lệnh SQL
        PreparedStatement stmt = con.prepareStatement(sql);
        // Thiết lập giá trị cho tham số truy vấn
        stmt.setInt(1, productId);
        
        // Thực thi câu lệnh và lấy kết quả
        ResultSet rs = stmt.executeQuery();
        
        // Duyệt qua kết quả
        if (rs.next()) {
            // Lấy dữ liệu từ các cột của bảng PRODUCTS
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String thumbnail = rs.getString("thumbnail");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            int categoryId = rs.getInt("category_id");
            Timestamp created_at = rs.getTimestamp("created_at");
            
            // Trả về đối tượng Product được khởi tạo từ các giá trị lấy được
            return new Product(id, name, description, thumbnail, price, quantity, categoryId, created_at);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    // Trường hợp không tìm thấy product hoặc có lỗi, trả về null
    return null;
}


}
