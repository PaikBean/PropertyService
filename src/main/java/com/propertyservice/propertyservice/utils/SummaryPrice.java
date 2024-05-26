package com.propertyservice.propertyservice.utils;

import com.propertyservice.propertyservice.domain.property.Property;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class SummaryPrice {

    public String summaryPrice(String transactionType, BigDecimal deposit, BigDecimal monthlyFee) {
        return transactionType + " " + formatPrice(deposit) + "/" + formatPrice(monthlyFee);
    }

    public String summaryPrice(String transactionType, BigDecimal amount) {
        return transactionType + " " + formatPrice(amount);
    }


    private String formatPrice(BigDecimal amount) {
        if (amount == null) {
            return "0원";
        }

        long numberValue = amount.longValue();
        long trillion = numberValue / 1_0000_0000_0000L;
        long billion = (numberValue % 1_0000_0000_0000L) / 1_0000_0000L;
        long million = (numberValue % 1_0000_0000L) / 1_0000L;
        long rest = numberValue % 1_0000L;

        StringBuilder result = new StringBuilder();
        if (trillion > 0) {
            result.append(trillion).append("조");
        }
        if (billion > 0) {
            result.append(billion).append("억");
        }
        if (million > 0) {
            result.append(million).append("만");
        }
        if (rest > 0 || result.isEmpty()) {
            result.append(rest);
        }

        return result.toString().trim();
    }
}
