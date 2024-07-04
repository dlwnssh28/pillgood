package com.pillgood.service;

import com.pillgood.dto.NoticeDto;
import com.pillgood.entity.Notice;
import com.pillgood.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDto> getAllNotices() {
        List<NoticeDto> notices = noticeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        System.out.println("공지사항 목록 조회: " + notices); // 로그 추가
        return notices;
    }

    @Override
    public NoticeDto createNotice(NoticeDto noticeDto) {
        Notice noticeEntity = convertToEntity(noticeDto);
        Notice savedNotice = noticeRepository.save(noticeEntity);
        NoticeDto createdNoticeDto = convertToDto(savedNotice);
        System.out.println("공지사항 생성: " + createdNoticeDto); // 로그 추가
        return createdNoticeDto;
    }

    @Override
    public Optional<NoticeDto> updateNotice(int noticeNo, NoticeDto updatedNoticeDto) {
        return noticeRepository.findById(noticeNo)
                .map(notice -> {
                    notice.setNoticeTitle(updatedNoticeDto.getNoticeTitle());
                    notice.setNoticeContent(updatedNoticeDto.getNoticeContent());
                    Notice updatedNotice = noticeRepository.save(notice);
                    NoticeDto updatedNoticeDtoResult = convertToDto(updatedNotice);
                    System.out.println("공지사항 수정: " + updatedNoticeDtoResult); // 로그 추가
                    return updatedNoticeDtoResult;
                });
    }

    @Override
    public boolean deleteNotice(int noticeNo) {
        if (noticeRepository.existsById(noticeNo)) {
            noticeRepository.deleteById(noticeNo);
            System.out.println("공지사항 삭제: 공지사항 번호 - " + noticeNo); // 로그 추가
            return true;
        }
        System.out.println("공지사항 삭제 실패: 공지사항 번호 - " + noticeNo); // 로그 추가
        return false;
    }

    @Override
    public Optional<NoticeDto> getNoticeById(int noticeNo) {
        Optional<NoticeDto> noticeDto = noticeRepository.findById(noticeNo).map(this::convertToDto);
        if (noticeDto.isPresent()) {
            System.out.println("공지사항 조회: " + noticeDto.get()); // 로그 추가
        } else {
            System.out.println("공지사항 조회 실패: 공지사항 번호 - " + noticeNo); // 로그 추가
        }
        return noticeDto;
    }

    @Override
    public NoticeDto convertToDto(Notice noticeEntity) {
        return new NoticeDto(
                noticeEntity.getNoticeNo(),
                noticeEntity.getNoticeTitle(),
                noticeEntity.getNoticeContent()
        );
    }

    @Override
    public Notice convertToEntity(NoticeDto noticeDto) {
        return new Notice(
                noticeDto.getNoticeNo(),
                noticeDto.getNoticeTitle(),
                noticeDto.getNoticeContent()
        );
    }
}
