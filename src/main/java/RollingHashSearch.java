public class RollingHashSearch {

    // Base for polynomial rolling hash
    static final int P = 31;

    // Large modulus to reduce collisions
    static final int MOD = 1_000_000_009;

    /**
     * Searches for pattern in text using Polynomial Rolling Hash (Rabin-Karp).
     * Returns true if pattern exists, false otherwise.
     */
    public static boolean contains(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        // Pattern longer than text → impossible to match
        if (m > n) return false;

        // Precompute powers of P: p^0, p^1, ..., p^(max(n,m))
        long[] pPow = new long[Math.max(n, m)];
        pPow[0] = 1;

        for (int i = 1; i < pPow.length; i++) {
            pPow[i] = (pPow[i - 1] * P) % MOD;
        }

        // Prefix hash array for the text
        // h[i] = hash of substring text[0..i-1]
        long[] h = new long[n + 1];

        for (int i = 0; i < n; i++) {
            // Convert character to 1–26 using (char - 'a' + 1)
            long charValue = text.charAt(i) - 'a' + 1;

            h[i + 1] = (h[i] + charValue * pPow[i]) % MOD;
        }

        // Compute hash of pattern
        long patternHash = 0;
        for (int i = 0; i < m; i++) {
            long charValue = pattern.charAt(i) - 'a' + 1;
            patternHash = (patternHash + charValue * pPow[i]) % MOD;
        }

        // Slide window across the text and compare hashes
        for (int i = 0; i + m <= n; i++) {

            // Hash of substring text[i .. i+m-1]
            long currentHash = (h[i + m] - h[i] + MOD) % MOD;

            // Align pattern hash to same power range using pPow[i]
            if (currentHash == (patternHash * pPow[i]) % MOD) {

                return true;
            }
        }

        return false;
    }
}
