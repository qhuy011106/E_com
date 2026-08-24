package org.example.e_com.dao;

import org.example.e_com.model.Review;

import java.util.List;

public interface ReviewDao {

    // Lấy tất cả review
    List<Review> getAllReview();

    // Tìm review theo ID
    Review findById(int id);

    // Lấy tất cả review của một sản phẩm
    List<Review> findByProductId(int productId);

    // Lấy tất cả review của một user
    List<Review> findByUserId(int userId);

    // Thêm review
    int insert(Review review);

    // Cập nhật review
    int update(Review review);

    // Xóa review
    int delete(int id);
}