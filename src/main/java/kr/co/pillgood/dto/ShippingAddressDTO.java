package kr.co.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressDTO {

    private int shippingAddrId;
    private String memberUniqueId;
    private String postalCode;
    private String address;
    private String detailedAddress;
}
