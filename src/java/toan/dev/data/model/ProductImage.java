package toan.dev.data.model;

public class ProductImage {
    private int id; // ID của hình ảnh
    private int productId; // ID của sản phẩm liên quan
    private String imageUrl; // Đường dẫn đến hình ảnh

    public ProductImage(int id, int productId, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
