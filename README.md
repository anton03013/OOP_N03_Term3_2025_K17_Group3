# 🎮 Platformer RPG 2D - Cuộc Phiêu Lưu Của Hiệp Sĩ

## 📖 Mô Tả
Chào mừng bạn đến với **Platformer RPG 2D**, một trò chơi nhập vai 2D đơn giản nhưng đầy cuốn hút, nơi bạn sẽ điều khiển một hiệp sĩ dũng cảm trong hành trình phiêu lưu kỳ thú. Vượt qua các chướng ngại vật, chiến đấu với quái vật, thu thập kho báu (vàng, bình máu) và hoàn thành các nhiệm vụ. Trò chơi được xây dựng bằng **Java** và sử dụng **LibGDX** để phát triển giao diện đồ họa. Các nguyên tắc **OOP** như đóng gói, kế thừa, đa hình và trừu tượng được áp dụng để quản lý nhân vật, vật phẩm và hệ thống trò chơi.

## 🛠️ Tính Năng
- **Hệ Thống Chiến Đấu Động**: Tham gia các trận chiến với sát thương chí mạng và quản lý máu.
- **Cơ Chế Platformer**: Nhảy, di chuyển và tương tác với môi trường.
- **Thiết Kế Hướng Đối Tượng**: Cấu trúc mã sạch và dễ bảo trì.
- **Giao Diện Đồ Họa**: Xây dựng bằng **Swing** với giao diện đơn giản nhưng hiệu quả.

## 🚀 Công Nghệ Sử Dụng
- **Ngôn Ngữ**: Java
- **Thư Viện**: 
  - **LibGDX**: Phát triển game 2D (đồ họa, vật lý, quản lý sprite).
  - **Swing**: Các thành phần giao diện người dùng.

## 🎮 Cách Chơi
1. **Di Chuyển**:
   - `A`: Di chuyển sang trái
   - `D`: Di chuyển sang phải
   - `W`: Nhảy
2. **Chiến Đấu**:
   - `Space`: Tấn công đối thủ khi ở trong phạm vi.
3. **Mục Tiêu**:
   - Đánh bại đối thủ để giành chiến thắng!

## 👥 Thành Viên Nhóm
  - 1 Nguyễn Nhật An
  - 2 Phạm Duy Quang
  - 3 Trần Đình Dũng
       
## 🏆 Cải Tiến Trong Tương Lai
  - Thêm các lớp nhân vật mới (ví dụ: Pháp Sư, Cung Thủ)..
  - Thêm các lớp nhân vật mới (ví dụ: Pháp Sư, Cung Thủ).
  - Giới thiệu các cấp độ và kẻ thù mới.
  - Nâng cấp đồ họa với các hiệu ứng hoạt hình.

Xây dựng ứng dụng Quản lý Đấu Trường Game 2D

## Giao diện: Java Swing (có thể mở rộng lên Spring Boot nếu cần).
## Chức năng quản lý nhân vật (Player):
   - Thêm, sửa, xóa nhân vật.
   - Liệt kê thông tin các nhân vật, có thể lọc theo tên, sức mạnh, máu,...
## Chức năng quản lý quái vật (Enemies):
   - Thêm, sửa, xóa quái vật.
   - Liệt kê thông tin các quái vật, lọc theo loại, sức mạnh,...
## Chức năng quản lý nền/platform (Platforms):
   - Thêm, sửa, xóa platform.
   - Liệt kê thông tin các platform, lọc theo vị trí, kích thước,...
## Chức năng gán nhân vật vào phòng đấu (gán Player cho Platform hoặc Battle):
   - Cho phép phân bổ nhân vật vào các phòng/platform để thi đấu.

## Dữ liệu được lưu trữ xuống file nhị phân:

   - Tạo các lớp liên quan đến Player, Enemies, Platforms để đọc/ghi dữ liệu xuống file.
## Khi làm việc với dữ liệu trong bộ nhớ:
   - Dữ liệu được lưu trữ dưới dạng các Collection như ArrayList, LinkedList, Map,...
## Các đối tượng chính:

   - Đối tượng 01: Player (Nhân vật)
   - Đối tượng 02: Enemies (Quái vật)
   - Đối tượng 03: Platforms (Nền/phòng đấu)

# Class Diagram

![Class Diagram](https://github.com/user-attachments/assets/174e51be-6847-4dfa-a06c-a4782fe426d1)

# Sequence Diagram for Attack
![Sequence Diagram for Attack](https://github.com/user-attachments/assets/32e7343f-0ab8-4f9e-9a31-e1f156f4e92d)

# Sequence Diagram for Movement
![Sequence Diagram for Movement](https://github.com/user-attachments/assets/d1ba4ab4-6efd-4c9f-8c67-866949a80a70)

# Sequence Diagram for Platform
![Sequence Diagram for Platform](https://github.com/user-attachments/assets/206a20ab-fd0e-431b-9b7f-56776b08311c)


#Phương thức của Quang:
Chức năng : khi người chơi ấn nút A hoặc D thì nhân vật sẽ di chuyển theo hướng trái-phải,
nếu giữ cả 2 nút cùng lúc nhân vật sẽ đứng yên, nhân vật di chuyển được tính là lệch 
đi 10 đơn vị so với vị trí đứng tùy theo hướng ấn. Nhân vật chỉ di chuyển khi nhấn phím và dừng khi nhả phím.
Code:#sửa hướng xoay
if (key == KeyEvent.VK_A) {
            model.movingLeft = true;
            if (!model.movingRight) model.facingRight = false;
        } else if (key == KeyEvent.VK_D) {
            model.movingRight = true;
            if (!model.movingLeft) model.facingRight = true;
#thay đổi tọa độ
if (model.movingLeft) {
            model.p1X = Math.max(0, model.p1X - 10);
        }
        if (model.movingRight) {
            model.p1X = Math.min(view.getWidth() - 40, model.p1X + 10);
        }
#function nhả phím
public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> model.movingLeft = false;
            case KeyEvent.VK_D -> model.movingRight = false;
        }
  }


