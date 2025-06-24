# 🎮 Platformer RPG 2D - Cuộc Phiêu Lưu Của Hiệp Sĩ

---

## 📖 Mô Tả

**Platformer RPG 2D** là một trò chơi nhập vai 2D đơn giản nhưng hấp dẫn, nơi bạn điều khiển một hiệp sĩ trong hành trình vượt qua chướng ngại vật, chiến đấu với quái vật và thu thập vật phẩm.


- Ngôn ngữ: **Java**
- Framework: **Spring Boot**
- Áp dụng các nguyên lý **OOP**: đóng gói, kế thừa, đa hình, trừu tượng

---

## 🛠️ Tính Năng

⚔️ Hệ Thống Chiến Đấu Động: Hiệp sĩ chiến đấu với quái vật bằng các đòn tấn công.



🧗 Cơ Chế Platformer: Di chuyển, nhảy qua các nền tảng và vượt chướng ngại.



🧱 Thiết Kế Hướng Đối Tượng: Sử dụng các class để quản lý hiệp sĩ, quái vật và nền tảng.



🖥 Giao Diện Web Đơn Giản: Truy cập trò chơi qua trình duyệt với Spring Boot.
---

## 🚀 Công Nghệ Sử Dụng

- **Ngôn ngữ**: Java  
- **Thư viện**:
  - Spring Web: Xây dựng giao diện và API
  - Swing: UI, vẽ đồ họa 2D, quản lý sprite

---

## 🎮 Cách Chơi

| Hành động | Phím |
|----------|------|
| Di chuyển trái | `A` |
| Di chuyển phải | `D` |
| Nhảy | `W` |
| Tấn công | `Space` |

🎯 Mục tiêu: **Đánh bại quái vật để chiến thắng!**

---

## 👥 Thành Viên Nhóm

1. Nguyễn Nhật An  
2. Phạm Duy Quang  
3. Trần Đình Dũng

---

## 🏆 Cải Tiến Trong Tương Lai
-  Thêm cốt truyện và nhiệm vụ cho hiệp sĩ
-  Mở rộng cấp độ và các loại quái vật
-  Cải thiện đồ họa, hiệu ứng hoạt hình
-  Tích hợp âm thanh và nhạc nền
---

## 🛠️ Ứng Dụng Quản Lý Đấu Trường Game 2D

### 💻 Giao Diện: Spring Boot Web

### 📋 Quản Lý Nhân Vật (Player)

-  Cập nhật thông tin hiệp sĩ: sức mạnh, máu, vị trí
-  Lọc và hiển thị trạng thái hiệp sĩ

### 👾 Quản Lý Quái Vật (Enemies)

- Thêm, sửa, xóa quái vật  
- Lọc theo: loại, sức mạnh,...

### 🧱 Quản Lý Nền (Platforms)

- Thêm, sửa, xóa nền/platform  
- Lọc theo: vị trí, kích thước,...

### 🧩 Gán Nhân Vật Vào Phòng Đấu

- Phân bổ nhân vật vào platform để thi đấu (gán Player cho Platform/Battle)

### 💾 Lưu Trữ Dữ Liệu

- Dùng các class `Player`, `Enemies`, `Platforms` để ghi/đọc

### 🧠 Dữ Liệu Trong Bộ Nhớ

- Sử dụng các **Collection**: `ArrayList`, `LinkedList`, `Map`,...

### 🔑 Các Đối Tượng Chính

- `Player` - Nhân vật  
- `Enemies` - Quái vật  
- `Platforms` - Nền/phòng đấu

---

# Class Diagram

![Class Diagram](https://github.com/user-attachments/assets/174e51be-6847-4dfa-a06c-a4782fe426d1)

# Sequence Diagram for Attack
![Sequence Diagram for Attack](https://github.com/user-attachments/assets/32e7343f-0ab8-4f9e-9a31-e1f156f4e92d)

# Sequence Diagram for Movement
![Sequence Diagram for Movement](https://github.com/user-attachments/assets/d1ba4ab4-6efd-4c9f-8c67-866949a80a70)

# Sequence Diagram for Platform
![Sequence Diagram for Platform](https://github.com/user-attachments/assets/206a20ab-fd0e-431b-9b7f-56776b08311c)

# KeyPressed Flowchart
![KeyPressed Flowchart](https://github.com/user-attachments/assets/9c350193-6fa1-402d-be0b-e20e7ee26262)
