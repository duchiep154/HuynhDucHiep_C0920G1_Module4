package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> products;
    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Iphone X", "Điện Thoại", "apple.jpg"));
        products.put(2, new Product(2, "Smart Tivi LG", "Ti vi", "lg.jpg"));
        products.put(3, new Product(3, "Samsung A71", "Điện Thoại", "samsung.jpg"));
        products.put(4, new Product(4, "Laptop Asus", "LapTop", "asus.jpg"));
        products.put(5, new Product(5, "Bàn Ủi", "Điện Gia Dụng", "panasonic.jpg"));
        products.put(6, new Product(6, "Nokia 8.1", "Điện Thoại", "nokia.jpg"));
        products.put(7, new Product(7, "Nồi Cơm Điện", "Điện Gia Dụng", "sunhouse.jpg"));
        products.put(8, new Product(8, "Iphone 12", "Điện Thoại", "apple.jpg"));
        products.put(9, new Product(9, "Iphone 11", "Điện Thoại", "apple.jpg"));
    }


    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        product.setId((products.size() > 0) ? (products.size() + 1) : 1);
        products.put(product.getId(), product);
    }

    @Override
    public void update(int id, Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
