package com.pillgood.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
    private Integer noticeNo;
    private String noticeTitle;
    private String noticeContent;
}
