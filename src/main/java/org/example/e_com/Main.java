package org.example.e_com;

import org.example.e_com.business.Impl.ReviewBusinessImpl;
import org.example.e_com.business.ReviewBusiness;
import org.example.e_com.model.Review;

public class Main {

    public static void main(String[] args) {

        ReviewBusiness reviewBusiness =
                new ReviewBusinessImpl();

        System.out.println("================================");
        System.out.println("      REVIEW BUSINESS TEST");
        System.out.println("================================");

        // ==============================
        // 1. GET ALL REVIEW
        // ==============================
        System.out.println("\n===== GET ALL REVIEW =====");

        for (Review review : reviewBusiness.getAllReview()) {
            System.out.println(review);
        }

        // ==============================
        // 2. FIND BY ID
        // ==============================
        System.out.println("\n===== FIND REVIEW BY ID =====");

        Review review =
                reviewBusiness.findById(1);

        System.out.println(review);

        // ==============================
        // 3. FIND BY USER ID
        // ==============================
        System.out.println("\n===== FIND REVIEW BY USER ID =====");

        for (Review r : reviewBusiness.findByUserId(2)) {
            System.out.println(r);
        }

        // ==============================
        // 4. FIND BY PRODUCT ID
        // ==============================
        System.out.println("\n===== FIND REVIEW BY PRODUCT ID =====");

        for (Review r : reviewBusiness.findByProductId(1)) {
            System.out.println(r);
        }

        // ==============================
        // 5. ADD REVIEW
        // ==============================
        System.out.println("\n===== ADD REVIEW =====");

        Review newReview = new Review();

        // User 3 chưa review Product 2
        newReview.setUserId(3);
        newReview.setProductId(2);
        newReview.setRating(5);
        newReview.setComment("Sản phẩm dùng rất tốt");

        boolean add =
                reviewBusiness.addReview(newReview);

        System.out.println("Thêm review: " + add);

        // ==============================
        // 6. UPDATE REVIEW
        // ==============================
        System.out.println("\n===== UPDATE REVIEW =====");

        Review updateReview =
                reviewBusiness.findById(1);

        if (updateReview != null) {

            updateReview.setRating(4);
            updateReview.setComment(
                    "Sau khi sử dụng thấy khá tốt"
            );

            boolean update =
                    reviewBusiness.updateReview(updateReview);

            System.out.println(
                    "Update review: " + update
            );
        }

        // ==============================
        // 7. DELETE REVIEW
        // ==============================
        System.out.println("\n===== DELETE REVIEW =====");

        // Dùng ID không tồn tại để test
        boolean delete =
                reviewBusiness.deleteReview(999);

        System.out.println(
                "Delete review ID 999: " + delete
        );

        // ==============================
        // END
        // ==============================
        System.out.println("\n================================");
        System.out.println("          TEST FINISHED");
        System.out.println("================================");
    }
}