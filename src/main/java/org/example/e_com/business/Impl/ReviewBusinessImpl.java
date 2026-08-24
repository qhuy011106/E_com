package org.example.e_com.business.Impl;

import org.example.e_com.business.ReviewBusiness;
import org.example.e_com.model.Product;
import org.example.e_com.model.Review;
import org.example.e_com.model.User;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.ReviewService;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.ProductServiceImpl;
import org.example.e_com.service.impl.ReviewServiceImpl;
import org.example.e_com.service.impl.UserServiceImpl;

import java.util.List;

public class ReviewBusinessImpl implements ReviewBusiness {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;
    public ReviewBusinessImpl(){
        this.reviewService = new ReviewServiceImpl();
        this.userService = new UserServiceImpl();
        this.productService = new ProductServiceImpl();
    }
    @Override
    public List<Review> getAllReview() {
        return reviewService.getAllReview();
    }

    @Override
    public Review findById(int id) {
        if(id <= 0) return null;
        return reviewService.findById(id);
    }

    @Override
    public List<Review> findByUserId(int userId) {
        if(userId <= 0) return List.of();
        return reviewService.findByUserId(userId);
    }

    @Override
    public List<Review> findByProductId(int productId) {
        if(productId <= 0) return List.of();
        return reviewService.findByProductId(productId);
    }

    @Override
    public boolean addReview(Review review) {
        if(review == null) return false;
        //kiem tra user
        if(review.getUserId() <= 0) return false;
        User user = userService.findById(review.getUserId());
        if(user ==  null) return false;
         //kiem tra product
        if(review.getProductId() <= 0) return false;
        Product product = productService.findById(review.getProductId());
        if(product == null) return false;
        //rating chi nam tu 1 -> 5
        if(review.getRating() < 1 || review.getRating() > 5) return false;
        return reviewService.addReview(review);
    }

    @Override
    public boolean updateReview(Review review) {
        if(review == null || review.getId() <= 0 || review.getUserId() <= 0 || review.getProductId() <= 0) return false;
        // kiem tra review có tồn tại không
        Review existing = reviewService.findById(review.getId());
        if(existing == null) return false;
        //kiem tra user
        User user = userService.findById(review.getUserId());
        if(user == null) return false;
        //kiem tra product
        Product product = productService.findById(review.getProductId());
        if(product == null) return false;
        //rating
        if(review.getRating() < 1 || review.getRating() > 5) return false;
        return reviewService.updateReview(review);
    }

    @Override
    public boolean deleteReview(int id) {
        if (id <= 0) {
            return false;
        }

        Review review =
                reviewService.findById(id);

        if (review == null) {
            return false;
        }

        return reviewService.deleteReview(id);
    }
}
