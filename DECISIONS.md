# Decisions

<<<<<<< HEAD
**Name: Nikith Kumar Adigoppula**

**Date started: 17-Jun-2026**

**Date submitted:17-Jun-2026**

I started by reading the README, understanding the project structure, and running the test suite. My priority was to get the application compiling and passing tests before making any additional changes. Once the failing tests and compilation issues were identified, I focused on fixing the root causes and verifying the fixes through Maven tests.

---

## 1. Code & Design Decisions

**Auditable**

I did not modify the Auditable class because it was not directly related to the failing tests or functionality required by the assessment. The Auditable pattern is useful for sharing common fields such as createdAt and updatedAt across multiple entities and avoiding code duplication. The tradeoff is that it introduces inheritance into the domain model, which adds complexity if only a small number of entities need those fields. Since the current assessment did not require audit tracking functionality, I left it unchanged.

**TransactionResponse**

I did not make major structural changes to TransactionResponse. Response DTOs are important because they define exactly what information is exposed to API consumers. Returning entities directly can expose internal fields, create tight coupling between database models and API contracts, and sometimes cause serialization issues. DTOs provide a controlled and stable API layer.

**BudgetCalculator**

I implemented BudgetCalculator using Java Streams with groupingBy and reducing to aggregate transaction amounts by category. After aggregation, I sorted the entries by total spend in descending order and collected them into a LinkedHashMap to preserve the sorted order. I considered using manual loops and temporary maps, but the stream-based approach was concise, readable, and aligned with modern Java practices.

**Other Decisions**

The most significant decision was to follow the existing project structure and make targeted fixes rather than redesigning components. My goal was to fix defects while preserving the intended architecture and keeping changes minimal and easy to review.

---

## 2. Bug Fixes & Issues Found

**Missing Repository Method**

I found a compilation failure because TransactionServiceImpl referenced findByCategoryAndMonth, but the method was not present in TransactionRepository. This prevented the project from compiling. I fixed it by adding a repository query method with the required parameters.

**Monthly Spend Boundary Bug**

The monthly spend calculation excluded transactions occurring on the first day of the month. The cause was the use of isAfter(startOfMonth), which returns false for the first day itself. I fixed it by changing the condition to !isBefore(startOfMonth), ensuring the full month range is included.

**BudgetCalculator Implementation Missing**

BudgetCalculator.getTopSpendingCategories was left as a TODO and always returned an empty map. This caused test failures. I implemented the aggregation, sorting, and limiting logic required by the specification.

**Problems Not Fixed**

I noticed that getTransactionsByDateRange still contains a TODO implementation. I chose not to modify it because it was not required to pass the provided tests and changing unrelated functionality could introduce unintended behavior.
=======
**Name:** Nikith Kumar Adigoppula

**Date started:** 17-Jun-2026

**Date submitted:** 17-Jun-2026

I started by reading the README, understanding the project structure, and running the test suite. My priority was to get the application compiling and passing tests before making any additional changes. Once the failing tests and compilation issues were identified, I focused on fixing the root causes and verifying the fixes through Maven tests.

## 1. Code & Design Decisions

### Auditable

I did not modify the Auditable class because it was not directly related to the failing tests or functionality required by the assessment. The Auditable pattern is useful for sharing common fields such as createdAt and updatedAt across multiple entities and avoiding code duplication. The tradeoff is that it introduces inheritance into the domain model, which adds complexity if only a small number of entities need those fields. Since the current assessment did not require audit tracking functionality, I left it unchanged.

### TransactionResponse

I did not make major structural changes to TransactionResponse. Response DTOs are important because they define exactly what information is exposed to API consumers. Returning entities directly can expose internal fields, create tight coupling between database models and API contracts, and sometimes cause serialization issues. DTOs provide a controlled and stable API layer.

### BudgetCalculator

I implemented BudgetCalculator using Java Streams with groupingBy and reducing to aggregate transaction amounts by category. After aggregation, I sorted the entries by total spend in descending order and collected them into a LinkedHashMap to preserve the sorted order. I considered using manual loops and temporary maps, but the stream-based approach was concise, readable, and aligned with modern Java practices.

### Other Decisions

The most significant decision was to follow the existing project structure and make targeted fixes rather than redesigning components. My goal was to fix defects while preserving the intended architecture and keeping changes minimal and easy to review.

## 2. Bug Fixes & Issues Found

### Missing Repository Method

I found a compilation failure because TransactionServiceImpl referenced findByCategoryAndMonth, but the method was not present in TransactionRepository. This prevented the project from compiling. I fixed it by adding a repository query method with the required parameters.

### Monthly Spend Boundary Bug

The monthly spend calculation excluded transactions occurring on the first day of the month. The cause was the use of isAfter(startOfMonth), which returns false for the first day itself. I fixed it by changing the condition to !isBefore(startOfMonth), ensuring the full month range is included.

### BudgetCalculator Implementation Missing
>>>>>>> e461cd2 (Complete assessment documentation)

BudgetCalculator.getTopSpendingCategories was left as a TODO and always returned an empty map. This caused test failures. I implemented the aggregation, sorting, and limiting logic required by the specification.

### Problems Not Fixed

I noticed that getTransactionsByDateRange still contains a TODO implementation. I chose not to modify it because it was not required to pass the provided tests and changing unrelated functionality could introduce unintended behavior.

## 3. Testing Decisions

<<<<<<< HEAD
**TransactionCandidateTest**

My focus was on validating the behavior around spending calculations and category aggregation. The tests ensure that monthly spending calculations include valid boundary dates and that top spending categories are returned in the correct order.

**What I Did Not Test**

I did not write extensive integration tests, controller tests, or database tests because the assessment focused on service-layer logic and defect resolution. With additional time, I would add controller-level tests using MockMvc and integration tests against H2.

**Difference Between Test Suites**

TransactionServiceTest verifies the behavior of service methods using mocked dependencies. TransactionCandidateTest focuses on candidate-added validation of the specific bugs and business rules I considered most critical. They complement each other rather than testing exactly the same behavior.


---

## 4. AI Tool Usage

**Tools Used**

ChatGPT, Claude, and GitHub Copilot.

**Examples**
=======
### TransactionCandidateTest

My focus was on validating the behavior around spending calculations and category aggregation. The tests ensure that monthly spending calculations include valid boundary dates and that top spending categories are returned in the correct order.

### What I Did Not Test

I did not write extensive integration tests, controller tests, or database tests because the assessment focused on service-layer logic and defect resolution. With additional time, I would add controller-level tests using MockMvc and integration tests against H2.

### Difference Between Test Suites

TransactionServiceTest verifies the behavior of service methods using mocked dependencies. TransactionCandidateTest focuses on candidate-added validation of the specific bugs and business rules I considered most critical. They complement each other rather than testing exactly the same behavior.

## 4. AI Tool Usage

### Tools Used

ChatGPT, Claude, and GitHub Copilot.

### Examples
>>>>>>> e461cd2 (Complete assessment documentation)

I used AI to help understand Maven build errors and identify the root cause of failing tests. AI suggested possible fixes, but I verified each suggestion by running the test suite.

I also used AI to compare implementation approaches for BudgetCalculator. After reviewing the suggestions, I implemented and validated the version that matched the expected behavior of the tests.

<<<<<<< HEAD
**Incorrect AI Output**

At one stage, AI generated a larger set of replacement files rather than focusing on the specific defects causing the failures. I rejected the full replacement approach because it would have unnecessarily changed working code. Instead, I used the test failures and existing project structure to guide smaller targeted fixes.

**Philosophy**

AI is useful for debugging, code review, documentation, and exploring implementation options. However, I do not trust AI output without verification. All AI-generated suggestions should be validated through compilation, testing, and manual review.

---

=======
### Incorrect AI Output

At one stage, AI generated a larger set of replacement files rather than focusing on the specific defects causing the failures. I rejected the full replacement approach because it would have unnecessarily changed working code. Instead, I used the test failures and existing project structure to guide smaller targeted fixes.

### Philosophy

AI is useful for debugging, code review, documentation, and exploring implementation options. However, I do not trust AI output without verification. All AI-generated suggestions should be validated through compilation, testing, and manual review.

>>>>>>> e461cd2 (Complete assessment documentation)
## 5. What I'd Do Next

1. Implement getTransactionsByDateRange to complete unfinished functionality.
2. Add controller-level tests using MockMvc to validate request and response behavior.
3. Add integration tests covering repository queries and H2 database interactions.
4. Improve validation around API inputs such as invalid dates and months.

The biggest remaining weakness is the limited test coverage outside the service layer. Additional integration and controller tests would provide stronger confidence in end-to-end behavior.
