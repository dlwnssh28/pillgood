package com.pillgood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointDetailDto {

    @JsonProperty("point_detail_id")
    private int pointDetailId;

    @JsonProperty("member_unique_id")
    private String memberUniqueId;

    @JsonProperty("point_status_code")
    private String pointStatusCode;

    @JsonProperty("points")
    private int points;

    @JsonProperty("detail_history_id")
    private int detailHistoryId;

    @JsonProperty("point_id")
    private int pointId;
}
