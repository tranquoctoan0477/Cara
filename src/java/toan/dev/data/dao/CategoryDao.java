package toan.dev.data.dao;

import java.util.List;

import toan.dev.data.model.Category;
import toan.dev.data.model.Product;

public interface CategoryDao {
	public boolean insert(Category category);
	public boolean update(Category category);
	public boolean delete(int id);
	public Category find(int id);
	public List<Category> findAll();
//        List<Product> findByCategory(int categoryId);
        public List<Category> hoCategorys();
}
