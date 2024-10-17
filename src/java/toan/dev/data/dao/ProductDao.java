package toan.dev.data.dao;

import java.util.List;

import toan.dev.data.model.Product;

public interface ProductDao {

    public boolean insert(Product product);

    public boolean update(Product product);

    public boolean delete(int id);

    public Product find(int id);
    
    public Product findByProduct(int productId);

    public List<Product> findAll();

    public List<Product> findByName(String key);

    public List<Product> hot(int limit);

    public List<Product> news(int limit);

    public List<Product> getProducts(int from, int amount);

    public List<Product> filter(int categoryId, String propertyName, String order);

    public List<Product> findByCategory(int categoryId);
}
