package com.mdaucodes.fastfoodbackend.product.dtos;

import java.math.BigDecimal;

public class DiscountAmount {
    private BigDecimal amount;

    public DiscountAmount() {
    }

    public DiscountAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
