package com.pillgood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shipping_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_addr_id")
    private int shippingAddrId;

    @Column(name = "member_unique_id", nullable = false)
    private String memberUniqueId;

    @Column(name = "postal_code", length = 10, nullable = false)
    private String postalCode;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "detailed_address", length = 255, nullable = false)
    private String detailedAddress;

    @ManyToOne
    @JoinColumn(name = "member_unique_id", referencedColumnName = "member_unique_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_member_unique_id"))
    private Member member;

    // 필요한 생성자 추가
    public ShippingAddress(String memberUniqueId, String postalCode, String address, String detailedAddress) {
        this.memberUniqueId = memberUniqueId;
        this.postalCode = postalCode;
        this.address = address;
        this.detailedAddress = detailedAddress;
    }
}