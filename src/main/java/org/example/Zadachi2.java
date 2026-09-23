package org.example;

import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.Set;

public class Zadachi2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //String string1 = scanner.nextLine();
        //String string2 = scanner.nextLine();
        //String string3 = scanner.nextLine();
        //String string4 = scanner.nextLine();

        //String cleaned = string1.toLowerCase().replace(" ", "");

        //StringBuilder rv = new StringBuilder(cleaned);
        //rv.reverse();
        //String reverse = rv.toString();

        //boolean isPalindrome = cleaned.equals(reverse);
        //System.out.println(isPalindrome);

        //String[] split = string2.split("\\s+");
        //System.out.println("кол-во слов: " + split.length);

        //LinkedHashSet<Integer> result = new LinkedHashSet<>();

        //String[] unique = string3.split(" ");
        //for (int i = 0; i < unique.length; i++) {
        //    int n = Integer.parseInt(unique[i]);
        //    result.add(n);
        //}
        //System.out.println(result);

        //for (int j : result){
        //    System.out.println(j + " ");
        //}

        // String[] split = string4.split("\\s+");

        //for (int i = split.length-1; i >= 0; i--) {
        //    int n = Integer.parseInt(split[i]);
        //    System.out.print(n + " ");
        //}

        for (int i = 1; i < 101; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            }
            else if (i % 3 == 0) {
                System.out.println("Fizz");
            }
            else if (i % 5 == 0) {
                System.out.println("Buzz");
            }
            else System.out.println(i);
        }
    }
}
