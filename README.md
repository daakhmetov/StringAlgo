**Introduction**

String-matching algorithms are essential in many software applications, including text editors, search engines, compilers, and bioinformatics. The purpose of this assignment was to choose one string-processing algorithm, implement it from scratch in Java, analyze its performance, and test it on input strings of different lengths.

For this task, I selected the Rabin–Karp algorithm using Polynomial Rolling Hash. This algorithm is widely used for fast substring search and is known for its simplicity, efficiency, and suitability for implementation using modular arithmetic.

**Algorithm Description**

The Rabin–Karp algorithm uses a hashing approach to efficiently compare the pattern with substrings of the text. Instead of checking each character for every position (which results in O(n·m) time), Rabin–Karp converts strings into numerical hash values using a polynomial hash function:

H(s) = (s₀ · p⁰ + s₁ · p¹ + ... + sₖ₋₁ · pᵏ⁻¹) mod M

Where:

p is a constant base (31 in this implementation)

M is a large prime modulus (1,000,000,009)

Characters are mapped to integers (a → 1, b → 2, …)

To search the pattern inside the text, the algorithm:

Precomputes powers of p.

Builds a prefix hash array for the text.

Computes the hash of the pattern.

Slides a window across the text, comparing hash values.

If the hashes match, the substring is considered a match.

Because comparing hash values is O(1), the search becomes very fast.

**Implementation Summary**

Key components of the implementation:

Precomputation of polynomial powers for efficient rolling hash.

Prefix hash array allowing O(1) substring hash extraction.

Pattern hashing using the same polynomial method.

Sliding window search comparing aligned hash values.

Handling negative modulo values to prevent overflow issues.

Utility method contains(text, pattern) that returns true/false.

Unit tests were written using JUnit 5 and integrated through Maven.

**Testing Methodology**

The algorithm was tested on three required string sizes:

1. Short Test
Text: "hello"
Pattern: "lo"


Result: Match found at index 3

2. Medium Test
Text: "ababcababcab"
Pattern: "abc"


Result: Multiple matches found

3. Long Test

A long string of ~15,000 characters was constructed:

"abcabcabc...abcxyz"


Result: Pattern "xyz" found at the end

Additional Tests

Pattern not found

Pattern equals text

Pattern longer than text

All tests were successful, confirming correctness across different input sizes.

**Time Complexity Analysis**
• Best & Average Case Complexity: O(n + m)

This comes from:

O(m) to compute the pattern hash

O(n) to compute prefix hashes

O(n) to slide across the text

Since hash comparisons are O(1), total performance is linear.

• Worst Case Complexity: O(n·m)

This occurs only when:

Many hash collisions occur

Every collision requires verifying the substring character-by-character

However, due to the large modulus (1e9+9) and good base (31), collisions are extremely unlikely, so the worst case almost never happens in practice.

**Space Complexity Analysis**
Total Space: O(n + m)

Used for:

pPow[] – polynomial powers

h[] – prefix hashes of the text

Temporary variables

This is optimal for hashing-based string matching.

**Conclusion**

The Rabin–Karp algorithm with polynomial rolling hash proved to be a simple, efficient, and reliable method for substring search. The implementation meets all assignment requirements, including correctness, clarity, comments, and complete testing across multiple input sizes.

Compared to more complex algorithms such as KMP, Aho-Corasick, or Suffix Arrays, Rabin–Karp offers an excellent balance between ease of implementation and strong performance. The tests and analysis confirm that the algorithm performs well and scales effectively even for large input sizes.
