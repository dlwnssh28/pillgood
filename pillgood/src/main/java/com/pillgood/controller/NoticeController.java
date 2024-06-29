package com.pillgood.controller;

import com.pillgood.dto.NoticeDto;
import com.pillgood.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<NoticeDto>> getAllNotices() {
        List<NoticeDto> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto> getNoticeById(@PathVariable Integer noticeNo) {
        return noticeService.getNoticeById(noticeNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NoticeDto> createNotice(@RequestBody NoticeDto noticeDto) {
        NoticeDto createdNotice = noticeService.createNotice(noticeDto);
        return ResponseEntity.ok(createdNotice);
    }

    @PutMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto> updateNotice(@PathVariable Integer noticeNo, @RequestBody NoticeDto noticeDto) {
        return noticeService.updateNotice(noticeNo, noticeDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Integer noticeNo) {
        if (noticeService.deleteNotice(noticeNo)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
