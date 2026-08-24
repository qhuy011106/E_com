package org.example.e_com.service.impl;

import org.example.e_com.dao.ReviewDao;
import org.example.e_com.dao.impl.ReviewDaoImpl;
import org.example.e_com.model.Review;
import org.example.e_com.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao reviewDao;
    public ReviewServiceImpl(){
        this.reviewDao = new ReviewDaoImpl();
    }
    @Override
    public List<Review> getAllReview() {
        return reviewDao.getAllReview();
    }

    @Override
    public Review findById(int id) {

        if (id <= 0) {
            return null;
        }

        return reviewDao.findById(id);
    }

    @Override
    public List<Review> findByUserId(int userId) {

        if (userId <= 0) {
            return List.of();
        }

        return reviewDao.findByUserId(userId);
    }

    @Override
    public List<Review> findByProductId(int productId) {

        if (productId <= 0) {
            return List.of();
        }

        return reviewDao.findByProductId(productId);
    }

    @Override
    public boolean addReview(Review review) {

        if (review == null
                || review.getUserId() <= 0
                || review.getProductId() <= 0
                || review.getRating() < 1
                || review.getRating() > 5) {

            return false;
        }

        return reviewDao.insert(review) > 0;
    }

    @Override
    public boolean updateReview(Review review) {

        if (review == null
                || review.getId() <= 0
                || review.getUserId() <= 0
                || review.getProductId() <= 0
                || review.getRating() < 1
                || review.getRating() > 5) {

            return false;
        }

        return reviewDao.update(review) > 0;
    }

    @Override
    public boolean deleteReview(int id) {

        if (id <= 0) {
            return false;
        }

        Review review = reviewDao.findById(id);

        if (review == null) {
            return false;
        }

        return reviewDao.delete(id) > 0;
    }
}