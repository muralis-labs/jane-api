package br.com.muralis.core.mock;

import java.util.Random;

public class CPFGenerator {

	public static String generate() {
		Random random = new Random();

		// Generate the first 9 digits of the CPF
		int[] cpfDigits = new int[11];
		for (int i = 0; i < 9; i++) {
			cpfDigits[i] = random.nextInt(10);
		}

		// Calculate the first verification digit
		int firstVerificationDigit = calculateVerificationDigit(cpfDigits, 10);
		cpfDigits[9] = firstVerificationDigit;

		// Calculate the second verification digit
		int secondVerificationDigit = calculateVerificationDigit(cpfDigits, 11);
		cpfDigits[10] = secondVerificationDigit;

		// Convert the array of digits to a CPF string
		StringBuilder cpfBuilder = new StringBuilder();
		for (int digit : cpfDigits) {
			cpfBuilder.append(digit);
		}

		return cpfBuilder.toString();
	}

	public static int calculateVerificationDigit(int[] cpfDigits, int weight) {
		int sum = 0;
		for (int cpfDigit : cpfDigits) {
			sum += cpfDigit * weight;
			weight--;
		}
		int remainder = sum % 11;
		if (remainder < 2) {
			return 0;
		}
		else {
			return 11 - remainder;
		}
	}

}