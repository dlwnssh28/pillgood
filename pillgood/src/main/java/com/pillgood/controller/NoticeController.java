package com.pillgood.controller;

import com.pillgood.dto.NoticeDto;
import com.pillgood.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto>> getAllNotices() {
        List<NoticeDto> notices = noticeService.getAllNotices();
        System.out.println("공지사항 목록 조회: " + notices); // 로그 추가
        return ResponseEntity.ok(notices);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto> getNoticeById(@PathVariable Integer noticeNo) {
        System.out.println("공지사항 조회 요청: 공지사항 번호 - " + noticeNo); // 로그 추가
        return noticeService.getNoticeById(noticeNo)
                .map(notice -> {
                    System.out.println("공지사항 조회 성공: " + notice); // 로그 추가
                    return ResponseEntity.ok(notice);
                })
                .orElseGet(() -> {
                    System.out.println("공지사항 조회 실패: 공지사항 번호 - " + noticeNo); // 로그 추가
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping("/create")
    public ResponseEntity<NoticeDto> createNotice(@RequestBody NoticeDto noticeDto) {
        System.out.println("공지사항 생성 요청: " + noticeDto); // 로그 추가
        NoticeDto createdNotice = noticeService.createNotice(noticeDto);
        System.out.println("공지사항 생성 성공: " + createdNotice); // 로그 추가
        return ResponseEntity.status(201).body(createdNotice); // 201 Created 상태 코드 반환
    }

    @PutMapping("/update/{noticeNo}")
    public ResponseEntity<NoticeDto> updateNotice(@PathVariable Integer noticeNo, @RequestBody NoticeDto noticeDto) {
        System.out.println("공지사항 수정 요청: 공지사항 번호 - " + noticeNo + ", 수정 내용 - " + noticeDto); // 로그 추가
        return noticeService.updateNotice(noticeNo, noticeDto)
                .map(updatedNotice -> {
                    System.out.println("공지사항 수정 성공: " + updatedNotice); // 로그 추가
                    return ResponseEntity.ok(updatedNotice);
                })
                .orElseGet(() -> {
                    System.out.println("공지사항 수정 실패: 공지사항 번호 - " + noticeNo); // 로그 추가
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/delete/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Integer noticeNo) {
        System.out.println("공지사항 삭제 요청: 공지사항 번호 - " + noticeNo); // 로그 추가
        if (noticeService.deleteNotice(noticeNo)) {
            System.out.println("공지사항 삭제 성공: 공지사항 번호 - " + noticeNo); // 로그 추가
            return ResponseEntity.noContent().build();
        }
        System.out.println("공지사항 삭제 실패: 공지사항 번호 - " + noticeNo); // 로그 추가
        return ResponseEntity.notFound().build();
    }
}
