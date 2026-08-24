package org.example.e_com.service;

import org.example.e_com.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview();
    Review findById(int id);
    List<Review> findByUserId(int userId);
    List<Review> findByProductId(int productId);
    boolean addReview(Review review);
    boolean updateReview(Review review);
    boolean deleteReview(int id);

}
