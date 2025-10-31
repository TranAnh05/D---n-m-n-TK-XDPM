# Dự án Xây dựng Website giới thiệu, bán thiết bị máy tính trực tuyến (hiện thực code cho admin)

> **Dự án tiểu luận môn Thiết kế & Xây dựng phần mềm**

## 1. Giới thiệu

Đây là dự án backend cho một hệ thống thương mại điện tử (e-commerce). Điểm đặc biệt của dự án không nằm ở quy mô, mà ở phương pháp tiếp cận:

Dự án được xây dựng với mục tiêu chính là áp dụng và thực thi nghiêm ngặt các nguyên tắc của **Kiến trúc Sạch (Clean Architecture)** và **Phát triển Hướng Kiểm thử (Test-Driven Development - TDD)**.

Hệ thống được thiết kế dưới dạng **Headless Backend** (backend không có giao diện), cung cấp các **API (JSON)** cho một ứng dụng frontend (Web/Mobile) riêng biệt.

## 2. 🎯 Triết lý Thiết kế: Clean Architecture

Dự án được cấu trúc thành 4 tầng riêng biệt, tuân thủ nghiêm ngặt **Quy tắc Phụ thuộc (Dependency Rule)**: *Mọi phụ thuộc đều phải hướng vào trong*.



* **Tầng 4: Domain / Entities**
    * Chứa các đối tượng nghiệp vụ cốt lõi (POJO) và các quy tắc nghiệp vụ (validation).
    * Ví dụ: `Product`, `Category`, `User`, `Order`.
    * Đây là lớp trong cùng, không phụ thuộc vào bất cứ thứ gì.

* **Tầng 3: Application / Use Cases**
    * Chứa toàn bộ logic nghiệp vụ của ứng dụng (ví dụ: `AddNewCategoryUsecase`).
    * Định nghĩa các **Ports** (Interfaces) để giao tiếp với bên ngoài (ví dụ: `CategoryRepository`, `AddNewCategoryOutputBoundary`).
    * Tầng này không biết gì về Database hay Web API.

* **Tầng 2: Adapters / Interface Adapters**
    * Là các "bộ chuyển đổi" (ví dụ: `AddNewCategoryPresenter`, `AddNewCategoryViewModel`).
    * Chịu trách nhiệm triển khai các Port (Interface) của Tầng 3.
    * Chuyển đổi dữ liệu từ Use Case (Tầng 3) sang định dạng mà Tầng 1 (Web/Database) có thể hiểu.

* **Tầng 1: Infrastructure / Frameworks & Drivers**
    * Là lớp ngoài cùng, chứa các chi tiết kỹ thuật cụ thể.
    * **API Endpoints:** `CategoryController` (Servlet) nhận request và trả về JSON.
    * **Database:** `CategoryRepositoryImpl` (viết bằng JDBC/SQL) để triển khai `ICategoryRepository` và kết nối tới MySQL.

## 3. 🧪 Quy trình Phát triển: TDD (Test-Driven Development)

Thay vì "lắp" UI và Database vào ngay từ đầu, dự án tập trung vào việc xây dựng và kiểm thử lõi nghiệp vụ trước.

Toàn bộ logic nghiệp vụ (Tầng 3 - Use Cases) và logic trình bày (Tầng 2 - Presenters) được **kiểm thử 100% bằng JUnit 5**.

Các bài test này sử dụng các **Fake Repositories** (giả lập CSDL bằng `HashMap`) để đảm bảo các Use Case được test một cách độc lập, nhanh chóng và không phụ thuộc vào kết nối MySQL.

## 4. 🚀 Các Tính năng Chính (Dành cho Admin)

Dự án tập trung vào việc hiện thực hóa các yêu cầu chức năng cho vai trò `Admin` dưới dạng các API endpoint.

* **Quản lý thông tin sản phẩm/loại sản phẩm:**
    * Thêm mới một loại sản phẩm.
    * Xem danh sách tất cả loại sản phẩm.
    * Cập nhật, xóa loại sản phẩm.
    * Thêm mới, xem, sửa, xóa sản phẩm.

* **Quản lý thông tin tài khoản người dùng:**
    * Xem danh sách tài khoản.
    * Cập nhật vai trò / khóa tài khoản.

* **Quản lý thông tin đơn hàng trực tuyến:**
    * Xem danh sách đơn hàng.
    * Cập nhật trạng thái đơn hàng.

* **Tìm kiếm thông tin:**
    * Tìm kiếm sản phẩm, tài khoản người dùng, và đơn đặt sản phẩm.

## 5. 🛠️ Công nghệ sử dụng

| Lĩnh vực | Công nghệ |
| :--- | :--- |
| **Ngôn ngữ Backend** | Java |
| **API Framework** | Java Servlets |
| **Cơ sở dữ liệu** | MySQL |
| **Testing Framework** | JUnit 5 |
| **Build Tool** | [Maven] |
| **Kiến trúc** | **Clean Architecture** & **TDD** |