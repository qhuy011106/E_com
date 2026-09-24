package org.example.e_com;

import org.example.e_com.business.*;
import org.example.e_com.business.Impl.*;
import org.example.e_com.dto.*;
import org.example.e_com.model.*;
import org.example.e_com.service.*;
import org.example.e_com.service.impl.*;
import org.example.e_com.util.AuthUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            showMainMenu();
            choice = readInt("Chọn: ");

            switch (choice) {
                // ===== AUTH =====
                case 1 -> login();
                case 2 -> register();
                case 3 -> AuthUtil.logout();

                // ===== CUSTOMER =====
                case 4 -> viewProducts();
                case 5 -> searchProducts();
                case 6 -> viewCart();
                case 7 -> addToCart();
                case 8 -> checkout();
                case 9 -> viewOrderHistory();

                // ===== ADMIN =====
                case 10 -> adminManageUsers();
                case 11 -> adminManageOrders();
                case 12 -> adminManageProducts();
                case 13 -> adminStatistics();

                case 0 -> System.out.println("👋 Tạm biệt!");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);

        scanner.close();
    }

    // ===== HIỂN THỊ MENU =====
    private static void showMainMenu() {
        System.out.println("\n========================================");
        System.out.println("       E-COM SYSTEM");
        System.out.println("========================================");

        if (AuthUtil.isLoggedIn()) {
            User user = AuthUtil.getCurrentUser();
            String roleLabel = AuthUtil.isAdmin() ? "👑 ADMIN" : "👤 CUSTOMER";
            System.out.println("Xin chào: " + user.getFullname() + " (" + roleLabel + ")");
        } else {
            System.out.println("🔓 Chưa đăng nhập");
        }

        System.out.println("========================================");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("3. Đăng xuất");
        System.out.println("----------------------------------------");
        System.out.println("4. Xem sản phẩm");
        System.out.println("5. Tìm kiếm sản phẩm");
        System.out.println("6. Xem giỏ hàng");
        System.out.println("7. Thêm vào giỏ hàng");
        System.out.println("8. Thanh toán");
        System.out.println("9. Lịch sử đơn hàng");

        if (AuthUtil.isAdmin()) {
            System.out.println("----------------------------------------");
            System.out.println("👑 QUẢN TRỊ VIÊN");
            System.out.println("10. Quản lý người dùng");
            System.out.println("11. Quản lý đơn hàng");
            System.out.println("12. Quản lý sản phẩm");
            System.out.println("13. Thống kê");
        }

        System.out.println("----------------------------------------");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }

    // ===== HELPER =====
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("❌ Vui lòng nhập số!");
                scanner.nextLine();
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("❌ Vui lòng nhập số!");
                scanner.nextLine();
            }
        }
    }

    // ===== 1. ĐĂNG NHẬP =====
    private static void login() {
        System.out.println("\n===== ĐĂNG NHẬP =====");
        String username = readString("Username: ");
        String password = readString("Password: ");

        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);

        if (user != null) {
            AuthUtil.setCurrentUser(user);
            System.out.println("✅ Đăng nhập thành công!");
            System.out.println("   Chào mừng: " + user.getFullname());
        } else {
            System.out.println("❌ Đăng nhập thất bại!");
        }
    }

    // ===== 2. ĐĂNG KÝ =====
    private static void register() {
        System.out.println("\n===== ĐĂNG KÝ =====");
        String username = readString("Username: ");
        String password = readString("Password: ");
        String fullname = readString("Họ tên: ");
        String email = readString("Email: ");
        String phone = readString("Số điện thoại: ");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole("CUSTOMER");

        UserService userService = new UserServiceImpl();
        if (userService.addUser(user)) {
            System.out.println("✅ Đăng ký thành công! Hãy đăng nhập.");
        } else {
            System.out.println("❌ Đăng ký thất bại! Vui lòng kiểm tra lại thông tin.");
        }
    }

    // ===== 4. XEM SẢN PHẨM =====
    private static void viewProducts() {
        System.out.println("\n===== DANH SÁCH SẢN PHẨM =====");

        ProductBusiness productBusiness = new ProductBusinessImpl();
        List<Product> products = productBusiness.getAllProduct();

        if (products == null || products.isEmpty()) {
            System.out.println("❌ Không có sản phẩm nào!");
            return;
        }

        System.out.printf("%-5s | %-30s | %-12s | %-10s\n", "ID", "Tên", "Giá", "Tồn kho");
        System.out.println("-".repeat(70));
        for (Product p : products) {
            System.out.printf("%-5d | %-30s | %-12.0f | %-10d\n",
                    p.getId(), p.getName(), p.getPrice(), p.getQuantity());
        }
    }

    // ===== 5. TÌM KIẾM SẢN PHẨM =====
    private static void searchProducts() {
        System.out.println("\n===== TÌM KIẾM SẢN PHẨM =====");
        String keyword = readString("Nhập từ khóa: ");

        ProductBusiness productBusiness = new ProductBusinessImpl();
        List<ProductDTO> products = productBusiness.searchProducts(keyword);

        if (products == null || products.isEmpty()) {
            System.out.println("❌ Không tìm thấy sản phẩm nào!");
            return;
        }

        System.out.println("\n🔍 Kết quả tìm kiếm (" + products.size() + " sản phẩm):");
        for (ProductDTO p : products) {
            System.out.printf("  - %s | %.0f VND | %s\n",
                    p.getName(), p.getPrice(), p.getStockStatus());
        }
    }

    // ===== 6. XEM GIỎ HÀNG =====
    private static void viewCart() {
        if (!AuthUtil.requireLogin()) {
            System.out.println("❌ Vui lòng đăng nhập!");
            return;
        }

        System.out.println("\n===== GIỎ HÀNG =====");
        int userId = AuthUtil.getCurrentUser().getId();

        CartBusiness cartBusiness = new CartBusinessImpl();
        CartDTO cart = cartBusiness.getCartDTOByUserId(userId);

        if (cart == null) {
            System.out.println("❌ Không thể xem giỏ hàng!");
            return;
        }

        System.out.println(cart);
    }

    // ===== 7. THÊM VÀO GIỎ HÀNG =====
    private static void addToCart() {
        if (!AuthUtil.requireLogin()) {
            System.out.println("❌ Vui lòng đăng nhập!");
            return;
        }

        System.out.println("\n===== THÊM VÀO GIỎ HÀNG =====");
        int productId = readInt("Nhập Product ID: ");
        int quantity = readInt("Nhập số lượng: ");

        int userId = AuthUtil.getCurrentUser().getId();
        CartBusiness cartBusiness = new CartBusinessImpl();

        if (cartBusiness.addToCart(userId, productId, quantity)) {
            System.out.println("✅ Đã thêm vào giỏ hàng!");
        } else {
            System.out.println("❌ Thêm vào giỏ hàng thất bại!");
        }
    }

    // ===== 8. THANH TOÁN =====
    private static void checkout() {
        if (!AuthUtil.requireLogin()) {
            System.out.println("❌ Vui lòng đăng nhập!");
            return;
        }

        System.out.println("\n===== THANH TOÁN =====");
        System.out.println("Phương thức thanh toán:");
        System.out.println("  1. COD (Thanh toán khi nhận hàng)");
        System.out.println("  2. BANKING (Chuyển khoản)");
        System.out.println("  3. VNPAY");
        int choice = readInt("Chọn: ");

        String paymentMethod = switch (choice) {
            case 1 -> "COD";
            case 2 -> "BANKING";
            case 3 -> "VNPAY";
            default -> "COD";
        };

        int userId = AuthUtil.getCurrentUser().getId();
        CheckoutBusiness checkoutBusiness = new CheckoutBusinessImpl();
        Order order = checkoutBusiness.checkout(userId, paymentMethod);

        if (order != null) {
            System.out.println("\n🎉 ĐẶT HÀNG THÀNH CÔNG!");
            System.out.println("   Mã đơn hàng: #" + order.getId());
            System.out.println("   Tổng tiền: " + order.getTotal_amount() + " VND");
            System.out.println("   Trạng thái: " + order.getStatus());
        } else {
            System.out.println("❌ Thanh toán thất bại!");
        }
    }

    // ===== 9. LỊCH SỬ ĐƠN HÀNG =====
    private static void viewOrderHistory() {
        if (!AuthUtil.requireLogin()) {
            System.out.println("❌ Vui lòng đăng nhập!");
            return;
        }

        System.out.println("\n===== LỊCH SỬ ĐƠN HÀNG =====");
        int userId = AuthUtil.getCurrentUser().getId();

        OrderHistoryBusiness historyBusiness = new OrderHistoryBusinessImpl();
        List<OrderHistoryDTO> orders = historyBusiness.getOrderHistory(userId);

        if (orders == null || orders.isEmpty()) {
            System.out.println("❌ Bạn chưa có đơn hàng nào!");
            return;
        }

        System.out.println("\n📦 Lịch sử mua hàng:");
        for (OrderHistoryDTO order : orders) {
            System.out.println(order);
        }
    }

    // ===== 10. ADMIN: QUẢN LÝ USER =====
    private static void adminManageUsers() {
        if (!AuthUtil.requireAdmin()) {
            System.out.println("❌ Bạn không có quyền ADMIN!");
            return;
        }

        System.out.println("\n===== QUẢN LÝ NGƯỜI DÙNG =====");
        System.out.println("1. Xem tất cả user");
        System.out.println("2. Cập nhật role");
        System.out.println("3. Xóa user");
        int choice = readInt("Chọn: ");

        AdminBusiness admin = new AdminBusinessImpl();

        switch (choice) {
            case 1 -> {
                List<User> users = admin.getAllUser();
                System.out.printf("%-5s | %-20s | %-25s | %-10s\n",
                        "ID", "Username", "Họ tên", "Role");
                System.out.println("-".repeat(70));
                for (User u : users) {
                    System.out.printf("%-5d | %-20s | %-25s | %-10s\n",
                            u.getId(), u.getUsername(), u.getFullname(), u.getRole());
                }
            }
            case 2 -> {
                int userId = readInt("Nhập User ID: ");
                String role = readString("Nhập role mới (ADMIN/CUSTOMER): ");
                if (admin.updateUserRole(userId, role)) {
                    System.out.println("✅ Cập nhật role thành công!");
                } else {
                    System.out.println("❌ Cập nhật thất bại!");
                }
            }
            case 3 -> {
                int userId = readInt("Nhập User ID cần xóa: ");
                if (admin.deleteUser(userId)) {
                    System.out.println("✅ Xóa user thành công!");
                } else {
                    System.out.println("❌ Xóa thất bại!");
                }
            }
        }
    }

    // ===== 11. ADMIN: QUẢN LÝ ĐƠN HÀNG =====
    private static void adminManageOrders() {
        if (!AuthUtil.requireAdmin()) {
            System.out.println("❌ Bạn không có quyền ADMIN!");
            return;
        }

        System.out.println("\n===== QUẢN LÝ ĐƠN HÀNG =====");
        AdminBusiness admin = new AdminBusinessImpl();
        List<Order> orders = admin.getAllOrder();

        if (orders.isEmpty()) {
            System.out.println("❌ Không có đơn hàng nào!");
            return;
        }

        System.out.printf("%-5s | %-8s | %-15s | %-10s\n",
                "ID", "User ID", "Tổng tiền", "Trạng thái");
        System.out.println("-".repeat(50));
        for (Order o : orders) {
            System.out.printf("%-5d | %-8d | %-15.0f | %-10s\n",
                    o.getId(), o.getUser_id(), o.getTotal_amount(), o.getStatus());
        }

        System.out.println("\n1. Cập nhật trạng thái");
        System.out.println("0. Quay lại");
        int choice = readInt("Chọn: ");

        if (choice == 1) {
            int orderId = readInt("Nhập Order ID: ");
            System.out.println("Trạng thái:");
            System.out.println("  1. PENDING (Chờ xử lý)");
            System.out.println("  2. CONFIRMED (Đã xác nhận)");
            System.out.println("  3. SHIPPING (Đang giao)");
            System.out.println("  4. COMPLETED (Hoàn thành)");
            System.out.println("  5. CANCELLED (Đã hủy)");
            int statusChoice = readInt("Chọn: ");

            String status = switch (statusChoice) {
                case 1 -> "PENDING";
                case 2 -> "CONFIRMED";
                case 3 -> "SHIPPING";
                case 4 -> "COMPLETED";
                case 5 -> "CANCELLED";
                default -> null;
            };

            if (status != null && admin.updateOrderStatus(orderId, status)) {
                System.out.println("✅ Cập nhật trạng thái thành công!");
            } else {
                System.out.println("❌ Cập nhật thất bại!");
            }
        }
    }

    // ===== 12. ADMIN: QUẢN LÝ SẢN PHẨM =====
    private static void adminManageProducts() {
        if (!AuthUtil.requireAdmin()) {
            System.out.println("❌ Bạn không có quyền ADMIN!");
            return;
        }

        System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
        System.out.println("1. Xem tất cả sản phẩm");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        int choice = readInt("Chọn: ");

        AdminBusiness admin = new AdminBusinessImpl();

        switch (choice) {
            case 1 -> {
                List<Product> products = admin.getAllProduct();
                System.out.printf("%-5s | %-30s | %-12s | %-10s\n",
                        "ID", "Tên", "Giá", "Tồn kho");
                System.out.println("-".repeat(70));
                for (Product p : products) {
                    System.out.printf("%-5d | %-30s | %-12.0f | %-10d\n",
                            p.getId(), p.getName(), p.getPrice(), p.getQuantity());
                }
            }
            case 2 -> {
                String name = readString("Tên sản phẩm: ");
                double price = readDouble("Giá: ");
                int quantity = readInt("Số lượng: ");
                int categoryId = readInt("Category ID: ");

                Product product = new Product();
                product.setName(name);
                product.setPrice(BigDecimal.valueOf(price));
                product.setQuantity(quantity);
                product.setCategory_id(categoryId);

                if (admin.addProduct(product)) {
                    System.out.println("✅ Thêm sản phẩm thành công!");
                } else {
                    System.out.println("❌ Thêm sản phẩm thất bại!");
                }
            }
            case 3 -> {
                int id = readInt("Product ID: ");
                String name = readString("Tên mới: ");
                double price = readDouble("Giá mới: ");
                int quantity = readInt("Số lượng mới: ");
                int categoryId = readInt("Category ID: ");

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(BigDecimal.valueOf(price));
                product.setQuantity(quantity);
                product.setCategory_id(categoryId);

                if (admin.updateProduct(product)) {
                    System.out.println("✅ Cập nhật thành công!");
                } else {
                    System.out.println("❌ Cập nhật thất bại!");
                }
            }
            case 4 -> {
                int id = readInt("Product ID cần xóa: ");
                if (admin.deletProduct(id)) {
                    System.out.println("✅ Xóa thành công!");
                } else {
                    System.out.println("❌ Xóa thất bại!");
                }
            }
        }
    }

    // ===== 13. ADMIN: THỐNG KÊ =====
    private static void adminStatistics() {
        if (!AuthUtil.requireAdmin()) {
            System.out.println("❌ Bạn không có quyền ADMIN!");
            return;
        }

        int choice;
        do {
            System.out.println("\n========================================");
            System.out.println("         📊 THỐNG KÊ HỆ THỐNG");
            System.out.println("========================================");
            System.out.println("1. 📈 Dashboard tổng quan");
            System.out.println("2. 🏆 Top sản phẩm bán chạy");
            System.out.println("3. 💰 Doanh thu theo tháng");
            System.out.println("4. 👑 Top khách hàng");
            System.out.println("5. ⚠️  Sản phẩm sắp hết hàng");
            System.out.println("0. Quay lại");
            choice = readInt("Chọn: ");

            switch (choice) {
                case 1 -> showDashboard();
                case 2 -> showTopProducts();
                case 3 -> showRevenueByMonth();
                case 4 -> showTopCustomers();
                case 5 -> showLowStockProducts();
                case 0 -> System.out.println("↩️  Quay lại menu chính");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // ===== 13.1 DASHBOARD TỔNG QUAN =====
    private static void showDashboard() {
        System.out.println("\n===== 📈 DASHBOARD TỔNG QUAN =====");

        StatsBusiness statsBusiness = new StatsBusinessImpl();
        DashboardStatsDTO dashboard = statsBusiness.getDashboard();

        if (dashboard == null) {
            System.out.println("❌ Không thể lấy dữ liệu!");
            return;
        }

        System.out.println(dashboard);

        if (!dashboard.getTopProducts().isEmpty()) {
            System.out.println("\n🏆 TOP 5 SẢN PHẨM BÁN CHẠY:");
            System.out.println("-".repeat(80));
            int rank = 1;
            for (ProductStatsDTO p : dashboard.getTopProducts()) {
                System.out.printf("%d. %s%n", rank++, p);
            }
        } else {
            System.out.println("\n⚠️  Chưa có dữ liệu bán hàng (cần đơn COMPLETED)");
        }

        if (!dashboard.getTopCustomers().isEmpty()) {
            System.out.println("\n👑 TOP 5 KHÁCH HÀNG:");
            System.out.println("-".repeat(80));
            int rank = 1;
            for (CustomerStatsDTO c : dashboard.getTopCustomers()) {
                System.out.printf("%d. %s%n", rank++, c);
            }
        } else {
            System.out.println("\n⚠️  Chưa có dữ liệu khách hàng");
        }
    }

    // ===== 13.2 TOP SẢN PHẨM BÁN CHẠY =====
    private static void showTopProducts() {
        System.out.println("\n===== 🏆 TOP SẢN PHẨM BÁN CHẠY =====");

        int limit = readInt("Nhập số lượng muốn xem (mặc định 10): ");
        if (limit <= 0) limit = 10;

        StatsBusiness statsBusiness = new StatsBusinessImpl();
        List<ProductStatsDTO> products = statsBusiness.getTopSellingProducts(limit);

        if (products.isEmpty()) {
            System.out.println("⚠️  Chưa có sản phẩm nào bán được!");
            System.out.println("💡 Gợi ý: Cập nhật đơn hàng thành 'COMPLETED' để có dữ liệu.");
            return;
        }

        System.out.printf("%n%-5s | %-30s | %-15s | %-10s | %-15s%n",
                "STT", "Tên sản phẩm", "Danh mục", "Đã bán", "Doanh thu");
        System.out.println("-".repeat(90));

        int rank = 1;
        for (ProductStatsDTO p : products) {
            System.out.printf("%-5d | %-30s | %-15s | %-10d | %,.0f VND%n",
                    rank++,
                    p.getProductName(),
                    p.getCategoryName(),
                    p.getTotalSold(),
                    p.getTotalRevenue());
        }
    }

    // ===== 13.3 DOANH THU THEO THÁNG =====
    private static void showRevenueByMonth() {
        System.out.println("\n===== 💰 DOANH THU THEO THÁNG =====");

        int year = readInt("Nhập năm (mặc định " + LocalDate.now().getYear() + "): ");
        if (year <= 0) year = LocalDate.now().getYear();

        StatsBusiness statsBusiness = new StatsBusinessImpl();
        List<RevenueStatsDTO> revenues = statsBusiness.getRevenueByMonth(year);

        if (revenues.isEmpty()) {
            System.out.println("⚠️  Không có dữ liệu doanh thu cho năm " + year);
            return;
        }

        System.out.println("\n📅 Doanh thu năm " + year + ":");
        System.out.printf("%-15s | %-10s | %-15s | %-15s | %-10s%n",
                "Tháng", "Đơn hàng", "Doanh thu", "TB/đơn", "SP bán");
        System.out.println("-".repeat(80));

        double totalYear = 0;
        for (RevenueStatsDTO r : revenues) {
            System.out.printf("%-15s | %-10d | %,.0f VND    | %,.0f VND   | %-10d%n",
                    r.getMonthLabel(),
                    r.getTotalOrders(),
                    r.getTotalRevenue(),
                    r.getAvgOrderValue(),
                    r.getTotalProducts());
            totalYear += r.getTotalRevenue().doubleValue();
        }

        System.out.println("-".repeat(80));
        System.out.printf("💰 TỔNG DOANH THU NĂM %d: %,.0f VND%n", year, totalYear);
    }

    // ===== 13.4 TOP KHÁCH HÀNG =====
    private static void showTopCustomers() {
        System.out.println("\n===== 👑 TOP KHÁCH HÀNG =====");

        int limit = readInt("Nhập số lượng muốn xem (mặc định 10): ");
        if (limit <= 0) limit = 10;

        StatsBusiness statsBusiness = new StatsBusinessImpl();
        List<CustomerStatsDTO> customers = statsBusiness.getTopCustomers(limit);

        if (customers.isEmpty()) {
            System.out.println("⚠️  Chưa có khách hàng nào mua hàng!");
            System.out.println("💡 Gợi ý: Cập nhật đơn hàng thành 'COMPLETED' để có dữ liệu.");
            return;
        }

        System.out.printf("%n%-5s | %-25s | %-25s | %-10s | %-15s%n",
                "STT", "Họ tên", "Email", "Đơn", "Chi tiêu");
        System.out.println("-".repeat(90));

        int rank = 1;
        for (CustomerStatsDTO c : customers) {
            System.out.printf("%-5d | %-25s | %-25s | %-10d | %,.0f VND%n",
                    rank++,
                    c.getFullName(),
                    c.getEmail(),
                    c.getTotalOrders(),
                    c.getTotalSpent());
        }
    }

    // ===== 13.5 SẢN PHẨM SẮP HẾT HÀNG =====
    private static void showLowStockProducts() {
        System.out.println("\n===== ⚠️  SẢN PHẨM SẮP HẾT HÀNG =====");

        int threshold = readInt("Nhập ngưỡng tồn kho (mặc định 5): ");
        if (threshold < 0) threshold = 5;

        StatsBusiness statsBusiness = new StatsBusinessImpl();
        List<ProductStatsDTO> products = statsBusiness.getLowStockProducts(threshold);

        if (products.isEmpty()) {
            System.out.println("✅ Không có sản phẩm nào dưới ngưỡng " + threshold);
            return;
        }

        System.out.println("\n⚠️  Có " + products.size() + " sản phẩm cần nhập thêm hàng:");
        System.out.printf("%n%-5s | %-30s | %-15s | %-10s | %-15s%n",
                "STT", "Tên sản phẩm", "Danh mục", "Tồn kho", "Đã bán");
        System.out.println("-".repeat(90));

        int rank = 1;
        for (ProductStatsDTO p : products) {
            System.out.printf("%-5d | %-30s | %-15s | %-10d | %-15d%n",
                    rank++,
                    p.getProductName(),
                    p.getCategoryName(),
                    p.getStockQuantity(),
                    p.getTotalSold());
        }
    }
}