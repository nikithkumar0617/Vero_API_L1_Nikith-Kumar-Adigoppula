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