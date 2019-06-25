public class KMP {

	void search(String word, String pattern) {
		int n = word.length();
		int m = pattern.length();

		int[] lps = new int[m];
		createLps(pattern, lps, m);

		// Search for pattern using LPS array
		int i = 0; // index for word
		int j = 0; // index for pattern

		while (i < n) {
			if (word.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			}
			if (j == m) {
				System.out.println("Pattern found at: " + (i - j));
				j = lps[j - 1];
			} else if (i < n && word.charAt(i) != pattern.charAt(j)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

	}

	private void createLps(String pattern, int[] lps, int m) {
		int length = 0; // Length of previous longest prefix suffix
		lps[0] = 0; // it is always 0
		int i = 1;

		// Loop from i = 1 to m-1
		while (i < m) {
			if (pattern.charAt(i) == pattern.charAt(length)) {
				length++;
				lps[i] = length;
				i++;
			} else {
				if (length != 0) {
					length = lps[length - 1];
				} else {
					lps[i] = length;
					i++;
				}
			}
		}
	}

  //Test Code
	public static void main(String[] args) {
		String word = "AABAACAADAABAABA";
		String pattern = "AABA";
		new KMP().search(word, pattern);
	}
}
