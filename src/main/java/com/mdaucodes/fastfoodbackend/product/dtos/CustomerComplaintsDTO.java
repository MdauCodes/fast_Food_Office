package com.mdaucodes.fastfoodbackend.product.dtos;

import java.util.UUID;

public class CustomerComplaintsDTO {
    private UUID customerUuid;
    private UUID productUuid;
    private String complaint;

    public CustomerComplaintsDTO() {
    }

    public CustomerComplaintsDTO(UUID customerUuid, UUID productUuid, String complaint) {
        this.customerUuid = customerUuid;
        this.productUuid = productUuid;
        this.complaint = complaint;
    }

    public UUID getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(UUID customerUuid) {
        this.customerUuid = customerUuid;
    }

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
