# Scale Database

## MySQL replication

### Master-slave replication
- Cho phép sao chép tự động từ một master tạo ra một hay nhiều cơ sở dữ liệu slave. Master cho phép việc đọc/ ghi, trong khi các slave chỉ cho phép việc đọc.

<p align="center">
  <img src="https://camo.githubusercontent.com/6a097809b9690236258747d969b1d3e0d93bb8ca/687474703a2f2f692e696d6775722e636f6d2f4339696f47746e2e706e67" width="400">
</p>

- Master- slave replication mặc định là không đồng bộ, các slave không cần phải kết nối vĩnh viễn để nhận các cập nhật
- Tất cả các thay đổi trên cơ sở dữ liệu master sẽ được ghi lại dưới dạng file log binary, slave đọc file log đó, thực hiện những thao tác trong file log, việc ghi, đọc và thực thi trong file log này dưới dạng binary được thực hiện rất nhanh.
- Nhược điểm:
    - Có khả năng bị mất dữ liệu khi master bị fail trước khi các slave cập nhật lại dữ liệu
    - Thao tác ghi sẽ dẫn đến việc cập nhật lại các bản sao (slave). Việc có nhiều thao tác ghi sẽ dẫn đến việc cập nhật các slave liên tục => Không thể thực hiện việc đọc dữ liệu.
    - Càng có nhiều slave được đọc, thì càng phải replicate => dẫn đến hệ thống xử lý chậm (hệ thống bị lag)
    - Ở một số hệ thống, việc ghi vào master có thể thực thi ở nhiều luồng song song (multiple threads) , các slave chỉ có thể hỗ trợ việc cập nhật với 1 luồng duy nhất.
    - Cần bổ sung thêm phần cứng và tăng thêm độ phức tạp.
    - Cần bổ sung thêm logic để xác định được đâu là slave, đâu là master

### Master-master replication
- Cho phép đọc và ghi dữ liệu đồng thời trên cả hai master. Khi đó cả hai Master vừa đóng vai trò là master, vừa là slave

<p align="center">
  <img src="https://camo.githubusercontent.com/5862604b102ee97d85f86f89edda44bde85a5b7f/687474703a2f2f692e696d6775722e636f6d2f6b7241484c47672e706e67" width="400">
</p>

- Nhược điểm:
    - Phải cần thực hiện việc cân  bằng tải (load balancer) hoặc cần thực hiện việc thay đổi logic cho ứng dụng để có thể xác định vị trí master nào để ghi.
    - Hầu hết các hệ thống master-master hoặc là tính nhất quán "lỏng lẻo" (vi phạm ACID) hoặc là tăng độ trễ của quá trình ghi do đồng bộ hóa.
    - Quá trình giải quyết xung đột sẽ xảy ra nhiều hơn khi nhiều quá trình ghi được thêm vào và độ trễ tăng lên.

## Federation 

- Federation (hoặc gọi là phân vùng chức năng) là chia tách cơ sở dữ liệu theo hàm.
- Cho ví dụ:

<p align="center">
  <img src="https://camo.githubusercontent.com/6eb6570a8b6b4e1d52e3d7cc07e7959ea5dac75f/687474703a2f2f692e696d6775722e636f6d2f553371563333652e706e67" width="300">
</p>

Xét ở ví dụ trên, thay vì chỉ dùng một cơ sỏ dữ liệu duy nhất, ta có thể chia cơ sở dữ liệu đó thành ba cơ sở dữ liệu con như: forums, users, products => Việc đọc và ghi ít hơn cho mỗi cơ sở dữ liệu. Cơ sở dữ liệu nhỏ dẫn đến nhiều dữ liệu có thể phù hợp với bộ nhớ => Nhiều lần truy cập bộ nhớ cache hơn do cải thiện vị trí bộ nhớ cache. Ngoài ra, có thể xử lý song song các dữ liệu.

- Nhược điểm:
    - Federation không hiệu quả nếu lược đồ yêu cầu các hàm hoặc các bảng dữ liệu lớn.
    - Cần cập nhật logic cho ứng dụng để có thể xác định cơ sở dữ liệu nào cần đọc và ghi.
    - Việc kết hợp hai cơ sở dữ liệu phức tạp hơn việc liên kết đến một cơ sở dữ liệu
    - Bổ sung thêm phần cứng và tăng thêm độ phức tạp 

## Sharding
- Sharing phân phối dữ liệu trên các databases khác nhau, sao cho mỗi database chỉ quản lý một tập con của dữ liệu.
- Ví dụ: Lấy dữ liệu phân tích người dùng, khi sử dụng phương pháp sharding ta được như sau:
- Khi số lượng người dùng tăng lên, nhiều phân đoạn được thêm vào cụm.
<p align="center">
  <img src="https://camo.githubusercontent.com/1df78be67b749171569a0e11a51aa76b3b678d4f/687474703a2f2f692e696d6775722e636f6d2f775538783549642e706e67" width="400">
</p>

- Ưu điểm: Tương tự như lợi thế của federation
    - Giảm thiểu lưu lượng dữ liệu đọc và ghi
    - Ít nhân rộng hơn và nhiều lần truy cập bộ nhớ cache hơn.
    - Kích thước chỉ mục giảm, thường cải thiện hiệu suất với các truy vấn nhanh hơn.
    - Nếu một shard bị hỏng, các shard vẫn hoạt động
    - Cho phép cập nhật dữ liệu song song giữa các shard với nhau

- Nhược điểm:
    - Cần phải cập nhận logic cho ứng dụng để làm việc với các shard => truy vấn SQL phức tạp
    - Phân phối dữ liệu có thể bị lệch trong một shard
    - Việc kết hợp dữ liệu từ nhiều shard phức tạp hơn





