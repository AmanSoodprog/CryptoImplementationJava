import java.util.*;

public class Q2 {
	public static void main(String[] args) {
		System.out.println("Example-1\n");

		String key1 = "SCOPE";
		String plainText1 = "SECURITY";

		System.out.println("Key: " + key1);
		System.out.println("PlainText: " + plainText1);

		Playfair pfc1 = new Playfair(key1, plainText1);
		pfc1.generateCipherKey();

		String encText1 = pfc1.encryptMessage();
		System.out.println("Cipher Text is: " + encText1);
	}
}

class Playfair {
	private String key;
	private String plainText;
	private char[][] matrix = new char[5][5];

	public Playfair(String key, String plainText) {
		this.key = key.toLowerCase();
		this.plainText = plainText.toLowerCase();
	}

	public void generateCipherKey() {
		Set<Character> set = new HashSet<>();

		for (char ch : key.toCharArray()) {
			if (ch != 'j') {
				set.add(ch);
			}
		}

		StringBuilder tempKey = new StringBuilder(key);

		for (int i = 0; i < 26; i++) {
			char ch = (char) (i + 97);
			if (ch != 'j' && !set.contains(ch)) {
				tempKey.append(ch);
			}
		}

		for (int i = 0, idx = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matrix[i][j] = tempKey.charAt(idx++);
			}
		}

		System.out.println("Playfair Cipher Key Matrix:");

		for (char[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

	public String formatPlainText() {
		StringBuilder message = new StringBuilder();
		int len = plainText.length();

		for (int i = 0; i < len; i++) {
			if (plainText.charAt(i) == 'j') {
				message.append('i');
			} else {
				message.append(plainText.charAt(i));
			}
		}

		for (int i = 0; i < message.length(); i += 2) {
			if (message.charAt(i) == message.charAt(i + 1)) {
				message.insert(i + 1, 'x');
			}
		}

		if (len % 2 == 1) {
			message.append('x');
		}

		return message.toString();
	}

	public String[] formPairs(String message) {
		int len = message.length();
		String[] pairs = new String[len / 2];

		for (int i = 0, cnt = 0; i < len / 2; i++) {
			pairs[i] = message.substring(cnt, cnt += 2);
		}

		return pairs;
	}

	public int[] getCharPos(char ch) {
		int[] keyPos = new int[2];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrix[i][j] == ch) {
					keyPos[0] = i;
					keyPos[1] = j;
					break;
				}
			}
		}

		return keyPos;
	}

	public String encryptMessage() {
		String message = formatPlainText();
		String[] msgPairs = formPairs(message);
		StringBuilder encText = new StringBuilder();

		for (String pair : msgPairs) {
			char ch1 = pair.charAt(0);
			char ch2 = pair.charAt(1);
			int[] ch1Pos = getCharPos(ch1);
			int[] ch2Pos = getCharPos(ch2);

			if (ch1Pos[0] == ch2Pos[0]) {
				ch1Pos[1] = (ch1Pos[1] + 1) % 5;
				ch2Pos[1] = (ch2Pos[1] + 1) % 5;
			} else if (ch1Pos[1] == ch2Pos[1]) {
				ch1Pos[0] = (ch1Pos[0] + 1) % 5;
				ch2Pos[0] = (ch2Pos[0] + 1) % 5;
			} else {
				int temp = ch1Pos[1];
				ch1Pos[1] = ch2Pos[1];
				ch2Pos[1] = temp;
			}

			encText.append(matrix[ch1Pos[0]][ch1Pos[1]]);
			encText.append(matrix[ch2Pos[0]][ch2Pos[1]]);
		}

		return encText.toString();
	}
}
