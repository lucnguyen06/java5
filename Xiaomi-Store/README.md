# Unified Store Application

Dự án tích hợp hoàn chỉnh từ 4 dự án:
- **asm1**: Cửa hàng điện thoại với chức năng sản phẩm, giỏ hàng, đơn hàng, quản trị
- **asm02-1**: Trang hỗ trợ (About, Contact, News)
- **delivery_payment**: Quản lý giao hàng và thanh toán
- **PCS_Store**: Tính năng cửa hàng nâng cao

## Cấu trúc dự án

```
unified-store/
├── src/main/java/com/example/store/
│   ├── store/              # Module cửa hàng chính (từ asm1)
│   │   ├── controller/    # Controllers: Home, Product, Cart, Order, Auth, Admin
│   │   ├── dao/           # Data Access Objects (JDBC)
│   │   ├── model/         # Models: Product, Order, Account, CartItem
│   │   ├── service/       # Services: Auth, Cart, Product, Order
│   │   └── config/        # Configuration: WebConfig, AuthInterceptor
│   ├── support/           # Module hỗ trợ (từ asm02-1)
│   │   └── controller/    # SupportController: About, Contact, News
│   └── delivery/          # Module giao hàng/thanh toán (từ delivery_payment)
│       ├── controller/    # REST Controllers và WebController
│       ├── dto/           # Data Transfer Objects
│       ├── model/         # JPA Entities: Order, Delivery, Payment
│       ├── repository/    # JPA Repositories
│       └── service/       # Business Logic Services
└── src/main/resources/
    ├── templates/         # Thymeleaf templates từ tất cả dự án
    └── static/           # Static resources (CSS, JS, images)

```

## Cấu hình Database

### MySQL (Cửa hàng chính)
- Database: `unified_store`
- Tables: `products`, `orders`, `order_details`, `accounts`

### H2 (Giao hàng/Thanh toán - In-memory)
- Database: `delivery_payment` (in-memory)
- Tables: `delivery_orders`, `deliveries`, `payments`
- Console: http://localhost:8080/h2-console

## Các endpoint chính

### Cửa hàng (Store)
- `/` - Trang chủ (danh sách sản phẩm)
- `/product/list` - Danh sách sản phẩm
- `/product/detail/{id}` - Chi tiết sản phẩm
- `/cart/view` - Xem giỏ hàng
- `/cart/add/{id}` - Thêm vào giỏ hàng
- `/order/checkout` - Thanh toán
- `/login` - Đăng nhập
- `/logout` - Đăng xuất
- `/admin/product/list` - Quản lý sản phẩm
- `/admin/order/list` - Quản lý đơn hàng

### Hỗ trợ (Support)
- `/about` - Giới thiệu
- `/contact` - Liên hệ
- `/news` - Tin tức

### Giao hàng/Thanh toán (Delivery/Payment)
- `/delivery` - Dashboard
- `/delivery/orders` - Quản lý đơn hàng
- `/delivery/deliveries` - Quản lý giao hàng
- `/delivery/payments` - Quản lý thanh toán
- `/api/delivery/orders` - REST API đơn hàng
- `/api/deliveries` - REST API giao hàng
- `/api/payments` - REST API thanh toán

## Cài đặt và chạy

1. Cấu hình database trong `application.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

2. Chạy ứng dụng:
   ```bash
   mvn spring-boot:run
   ```

3. Truy cập: http://localhost:8080

## Ghi chú

- Module cửa hàng sử dụng JDBC với MySQL
- Module delivery/payment sử dụng JPA với H2 (in-memory)
- Các entity Order được tách biệt: `store.model.Order` và `delivery.model.Order`
- Delivery Order sử dụng table `delivery_orders` để tránh xung đột
