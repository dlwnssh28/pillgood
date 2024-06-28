package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private Integer noticeNo;
    private String noticeTitle;
    private String noticeContent;
}