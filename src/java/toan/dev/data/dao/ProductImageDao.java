/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toan.dev.data.dao;

import toan.dev.data.model.ProductImage;
import java.util.List;
/**
 *
 * @author tranq
 */
public interface ProductImageDao {
    List<ProductImage> getProductImages(int productId); // Lấy danh sách hình ảnh theo productId
//    void addProductImage(ProductImage productImage); // Thêm hình ảnh sản phẩm
//    void updateProductImage(ProductImage productImage); // Cập nhật hình ảnh sản phẩm
//    void deleteProductImage(int id); // Xóa hình ảnh sản phẩm
}
