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
@Table(name = "efficiencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Efficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "efficiency_id")
    private int efficiencyId;

    @Column(name = "efficiency_name", nullable = false, length = 255)
    private String efficiencyName;

    // ID를 매개변수로 받는 생성자 추가
    public Efficiency(int efficiencyId) {
        this.efficiencyId = efficiencyId;
    }
}
