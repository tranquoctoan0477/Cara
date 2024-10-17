/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toan.dev.data.impl;

import toan.dev.data.model.ProductImage;
import toan.dev.data.dao.ProductImageDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import toan.dev.data.driver.MySQLDriver;

/**
 *
 * @author tranq
 */
public class ProductImageImpl implements ProductImageDao{
    Connection con = MySQLDriver.getInstance().getConnection();
    
    @Override
    public List<ProductImage> getProductImages(int productId) {
        List<ProductImage> images = new ArrayList<>();
        String sql = "SELECT * FROM product_images WHERE product_id = ? LIMIT 4"; // Không cần LIMIT

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, productId); // Thay thế dấu hỏi với productId
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String imageUrl = rs.getString("image_url");
                images.add(new ProductImage(id, productId, imageUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
}
