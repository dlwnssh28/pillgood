package com.pillgood.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private int reviewId;
    private String memberUniqueId;
    private String orderNo;
    private LocalDateTime reviewDate;
    private String reviewContent;
    private int rating;
    private String reviewImage;
}
