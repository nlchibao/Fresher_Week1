## 1. Target

- This is the first week of freshers
- This module provides them a big picture of designing & developing information systems
- Freshers are taught to use various tools for taking notes: xmind, markdown, boostnote...
- Be familiar with IntelliJ Idea, vim, vscode...
- Philosophy: `Ask the right questions`

## 2. Materials

- [System Design Primer](https://github.com/donnemartin/system-design-primer)
- [Effective Engineer](https://gist.github.com/rondy/af1dee1d28c02e9a225ae55da2674a6f)
- [10 Common Software Architectural Patterns in a nutshell](https://towardsdatascience.com/10-common-software-architectural-patterns-in-a-nutshell-a0b47a1e9013)
- [A Beginners Guide to writing a Kickass README](https://medium.com/@meakaakka/a-beginners-guide-to-writing-a-kickass-readme-7ac01da88ab3)
- Google
- [Web Architecture 101](https://engineering.videoblocks.com/web-architecture-101-a3224e126947)
- [Redis cookbook](https://github.com/rediscookbook/rediscookbook)
## 3. Reports

+ Các em tạo repo riêng, ghi note markdown và post comment link vào đây (nhớ để repo ở chế độ public)

+ File báo cáo phải viết dưới bằng [Markdown](https://en.wikipedia.org/wiki/Markdown) (phần mở rộng là `*.md`

+ Trong báo cáo có thể reference tài liệu => nhưng bắt buộc phải ghi lại gạch đầu dòng những ý chính trong báo cáo

## 4. Nội dung báo cáo

Nên đọc hết `System Design Primer` để có cái nhìn về hệ thống. Phần nào chưa hiểu (google mãi chưa hiểu) thì nên hỏi người hướng dẫn 

- Định lý CAP, khái niệm `eventual consistency`

- Khái niệm throughput, latency 

- Các phương pháp để scale database (MySQL): sharding, replication, ...

- Task Queue khác gì Message Queue?

## 5. Bài tập 

### 5.1 Load balancer 

- Khái niệm về load balancer 

- Kiến trúc bên trong [nginx](https://dzone.com/articles/inside-nginx-how-we-designed). Làm rõ việc tại sao nginx dùng single thread 

- Thí nghiệm: 

```
1. Làm một web tĩnh đơn giản (hello world), viết bằng python 
2. Cho web chạy trên 2 port khác nhau 
3. Cài đặt nginx để load balancing giữa 2 port trên (verify lại bằng web browser)
```

### 5.2 Caching

- Vai trò của cache, các thuật toán apply cho cache (LRU, LFU)

- Cài đặt nginx để phục vụ trang web tĩnh (trang web có hiển thị một bức ảnh). Tìm cách để bật module cache cho nginx (bức ảnh khi hoạt động sẽ nằm trong mem, ko phải fetch từ disk nữa)

### 5.3 Redis 

- Khái niêm cơ bản về Redis: [kiến trúc](http://qnimate.com/overview-of-redis-architecture/) bên trong

- Nắm được các cấu trúc dữ liệu của Redis  (data structures). Trả lời câu hỏi: khi nào dùng cấu trúc `hyperloglog`

- Nắm được cách đặt tên key cho phù hợp (foo:*....)

- Config cluster redis 3 node, chạy cùng machine, khác port

- Viết chương trình chat đơn giản sử dụng Redis pub/sub (Dùng Java, Maven, và thư viện Redisson). Lưu dữ liệu chat cho mỗi người, set expire trong 1 ngày.

- Tham khảo: [5 Key Takeaways for Developing with Redis](https://redislabs.com/blog/5-key-takeaways-for-developing-with-redis/) và [Redis: Zero to Master in 30 minutes - Part 1](http://openmymind.net/2011/11/8/Redis-Zero-To-Master-In-30-Minutes-Part-1/)


### 5. Thời gian hoàn thành 

- 2 tuần 

- Hoàn thành càng sớm thì điểm quá trình càng cao 