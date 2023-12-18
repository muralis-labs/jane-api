package br.com.muralis.core.mock;

import java.util.Random;

public class GenerateCNPJ {
    public static String generateRandomCnpj() {
        Random random = new Random();

        int[] digits = new int[12];
        for (int i = 0; i < 12; i++) {
            digits[i] = random.nextInt(10);
        }

        int[] verificationDigits = calculateVerificationDigits(digits);

        StringBuilder cnpjBuilder = new StringBuilder();
        for (int digit : digits) {
            cnpjBuilder.append(digit);
        }
        cnpjBuilder.append(verificationDigits[0]);
        cnpjBuilder.append(verificationDigits[1]);

        return cnpjBuilder.toString();
    }

    private static int[] calculateVerificationDigits(int[] digits) {
        int[] weights = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] verificationDigits = new int[2];

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += digits[i] * weights[i];
        }

        int remainder = sum % 11;
        verificationDigits[0] = (remainder < 2) ? 0 : (11 - remainder);

        sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += digits[i] * weights[i];
        }

        remainder = sum % 11;
        verificationDigits[1] = (remainder < 2) ? 0 : (11 - remainder);

        return verificationDigits;
    }
}
