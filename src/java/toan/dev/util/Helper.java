package toan.dev.util;

import java.util.List;
import toan.dev.data.model.OrderItem;

/**
 * Helper chứa các phương thức hỗ trợ tính toán tổng tiền
 */
public class Helper {

    // Hàm tính tổng tiền của giỏ hàng
    public static double total(List<OrderItem> orderItemList) {
        double total = 0;
        for (int i = 0; i < orderItemList.size(); i++) {
            // Truy xuất đúng phần tử tại vị trí 'i'
            OrderItem ord = orderItemList.get(i);
            total += ord.getPrice() * ord.getQuantity(); // Tổng tiền = giá sản phẩm * số lượng
        }
        return total;
    }
}
