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
        return noticeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoticeDto createNotice(NoticeDto noticeDto) {
        Notice noticeEntity = convertToEntity(noticeDto);
        Notice savedNotice = noticeRepository.save(noticeEntity);
        return convertToDto(savedNotice);
    }

    @Override
    public Optional<NoticeDto> updateNotice(int noticeNo, NoticeDto updatedNoticeDto) {
        return noticeRepository.findById(noticeNo)
                .map(notice -> {
                    notice.setNoticeTitle(updatedNoticeDto.getNoticeTitle());
                    notice.setNoticeContent(updatedNoticeDto.getNoticeContent());
                    Notice updatedNotice = noticeRepository.save(notice);
                    return convertToDto(updatedNotice);
                });
    }

    @Override
    public boolean deleteNotice(int noticeNo) {
        if (noticeRepository.existsById(noticeNo)) {
            noticeRepository.deleteById(noticeNo);
            return true;
        }
        return false;
    }

    @Override
    public Optional<NoticeDto> getNoticeById(int noticeNo) {
        return noticeRepository.findById(noticeNo).map(this::convertToDto);
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
