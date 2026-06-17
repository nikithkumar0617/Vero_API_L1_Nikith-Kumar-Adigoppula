package com.vero.api.util;

import com.vero.api.model.Category;
import com.vero.api.model.Transaction;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BudgetCalculator {

    private BudgetCalculator() {
    }

    /**
     * Groups the provided transactions by category, sums the total amount spent
     * per category, and returns the top N categories by total spend in descending order.
     */
    public static Map<Category, BigDecimal> getTopSpendingCategories(List<Transaction> transactions, int topN) {
        return transactions.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        Transaction::getCategory,
                        java.util.stream.Collectors.reducing(
                                BigDecimal.ZERO,
                                Transaction::getAmount,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Category, BigDecimal>comparingByValue().reversed())
                .limit(topN)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );
    }
}