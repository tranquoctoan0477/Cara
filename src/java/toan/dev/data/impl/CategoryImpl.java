package toan.dev.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import toan.dev.data.dao.CategoryDao;
import toan.dev.data.driver.MySQLDriver;
import toan.dev.data.model.Category;

public class CategoryImpl implements CategoryDao {

    Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public boolean insert(Category category) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO CATEGORIES VALUES(NULL, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());

            stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        // TODO Auto-generated method stub
        String sql = "UPDATE CATEGORIES SET name = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getId());
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
        String sql = "DELETE FROM CATEGORIES WHERE ID = ?";
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
    public Category find(int id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM CATEGORIES WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String thumbnail = rs.getString("description");

                return new Category(id, name, thumbnail);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        List<Category> cateList = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIES";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                cateList.add(new Category(id, name, description));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cateList;
    }

    @Override
    public List<Category> hoCategorys() {
         List<Category> cateList = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIES LIMIT 4";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String thumbnail = rs.getString("thumbnail");

                cateList.add(new Category(id, name, thumbnail));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cateList;
    }

//    @Override
//    public List<Product> findByCategory(int categoryId) {
//        List<Product> productsList = new ArrayList<>();
//        String sql = "SELECT * FROM products WHERE category_id = ?"; // Câu truy vấn để lấy sản phẩm theo categoryId
//
//        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.setInt(1, categoryId); // Gán categoryId vào câu truy vấn
//            ResultSet rs = stmt.executeQuery(); // Thực thi truy vấn
//
//            while (rs.next()) {
//                // Giả sử lớp Product có các thuộc tính id, name, price, thumbnail
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setName(rs.getString("name"));
//                product.setPrice(rs.getDouble("price"));
//                product.setThumbnail(rs.getString("thumbnail"));
//                product.setCategoryId(rs.getInt("category_id"));
//                productsList.add(product); // Thêm sản phẩm vào danh sách
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // In ra thông báo lỗi nếu có
//        }
//
//        return productsList; // Trả về danh sách sản phẩm
//    }
}