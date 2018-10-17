# Task Queue vs Message Queue

# Message Queue
Message Queue nhận, giữ và gửi các messages. Nếu thao tác thực hiện chậm, bạn có thể sử dụng message queue với quy trình như sau:

* Một ứng dụng đưa công việc vào hàng đợi. Sau đó, thông báo cho người dùng về trạng thái công việc.
* Sau đó, chọn một công việc từ hàng đợi và xử lý nó. Tiếp đó thông báo công việc đã hoàn thành.

User không bị block và công việc được xử lý dưới nền. Trong thời gian này, các client có thể tùy chọn thực hiện một số xử lý nhỏ để làm như công việc đã hoàn thành. Ví dụ, khi bạn đăng môt bài viết trên facebook, facebook sẽ đăng ngay vào dòng thời gian của bạn, nhưng phải mất một thời gian facebook có thể gửi đến tất cả những người theo dõi bạn.

## Task Queue
Task queues nhận task và dữ liệu, xử lý chúng và trả về kết quả. Nó có thể hỗ trợ lập kế hoạch và có thể chạy các công việc tính toán chuyên sâu trong nền.

Celery hỗ trợ lập kế hoạch và chủ yếu hỗ trợ python 

## Điểm khác nhau giữa message queue vs task queue
Message queue | Task Queue
---|---
Các message thường được xử lý( do đó bị xóa khỏi hàng đợi) càng nhanh càng tốt | Các task thường được xếp lịch xử lý ở một thời gian cụ thể , do đó, nó sẽ nằm ở trong queue một thời gian'