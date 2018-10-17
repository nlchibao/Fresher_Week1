# Cap theorem: revisited 
## Định nghĩa:
Định lý Cap nói rằng, trong một hệ thống phân tán, chúng ta chỉ có thể thõa mãn 2 trong 3 đảm bảo : Tính nhất quán (Consistency), Tính sẵn sàng (Availability), Dung sai phân vùng (Partition Tolerance)

<p align="center">
  <img src="http://i.imgur.com/bgLMI2u.png">
  <br/>
  <i><a href=http://robertgreiner.com/2014/08/cap-theorem-revisited>Source: CAP theorem revisited</a></i>
</p>

* Tính nhất quán (Consistency) : Mỗi lần đọc dữ liệu, sẽ nhận được nội dung mới nhất hoặc lỗi.
* Tính sẵn sàng (Availability) : Mỗi yêu cầu nhận được phản hồi (Không phải lỗi) - không đảm bảo rằng nó chứa các ghi chép mới nhất.
* Dung sai phân vùng (Parition Tolerance): Hệ thống tiếp tục hoạt động bất chấp một số lượng tùy ý các tin nhắn (messages) đang bị rớt (hoặc trì hoãn) bởi mạng giữa các nút

## CP - Consistency/ Partition Tolerance

Hệ thống sẽ trả lại lỗi hoặc thời gian chờ nếu thông tin cụ thể không đảm bảo được cập nhật do phân vùng mạng

<p align="center">
  <img src="http://robertgreiner.com/uploads/images/2014/CAP-CP-full.png" width="300">
</p>

## AP - Availability/ Partition Tolerance

Hệ thống sẽ luôn xử lý truy vấn và cố gắng trả lại phiên bản hiện có của thông tin, ngay cả khi nó không thể đảm bảo được cập nhật do phân vùng mạng.

<p align="center">
  <img src="http://robertgreiner.com/uploads/images/2014/CAP-AP-full.png" width="300">
</p>


