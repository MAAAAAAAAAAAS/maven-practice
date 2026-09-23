package org.example;

import java.util.Scanner;

public class Zadachi1WeatherStatsTasks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stats = scanner.nextLine();
        int positive = 0, negative = 0, zero = 0, sum = 0, chet = 0, nechet = 0;

        String[] numbers = stats.split(" ");
        //int max = Integer.parseInt(numbers[0]);
        //int min = Integer.parseInt(numbers[0]);

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);
            sum = sum + n;
            if (n > max) { secondMax = max; max = n; }
            else if (n > secondMax && n != max) { secondMax = n; }
            if (min > n) { min = n; }
            if (n > 0) { positive++; }
            else if (n < 0) { negative++; }
            else { zero++; }
            if (n % 2 == 0) { chet++; }
            else { nechet++; }
        }
        double average = (double) sum / numbers.length;

        System.out.println("выше нуля: " + positive + ", ниже нуля: " + negative + ", равна нулю: " + zero);
        System.out.println("сумма = " + sum);
        System.out.println("максимальное число = " + max + ", второе максимальное число = " + secondMax);
        System.out.println("минимальное число = " + min);
        System.out.println("среднее арифметическое = " + average);
        System.out.println("четных = " + chet + ", нечетных = " + nechet);
    }
}
