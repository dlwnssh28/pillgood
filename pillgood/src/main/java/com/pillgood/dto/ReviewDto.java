package com.pillgood.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Integer reviewId;
    private String memberUniqueId;
    private String orderNo;
    private LocalDateTime reviewDate;
    private String reviewContent;
    private Integer rating;
    private String reviewImage;
}
