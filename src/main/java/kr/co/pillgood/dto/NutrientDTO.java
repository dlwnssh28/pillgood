package kr.co.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutrientDTO {

    private int nutrientId;
    private String nutrientName;
    private String description;
}
