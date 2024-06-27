package kr.co.pillgood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nutrients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    private int nutrientId;

    @Column(name = "nutrient_name", nullable = false, length = 255)
    private String nutrientName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // ID를 매개변수로 받는 생성자 추가
    public Nutrient(int nutrientId) {
        this.nutrientId = nutrientId;
    }
}
