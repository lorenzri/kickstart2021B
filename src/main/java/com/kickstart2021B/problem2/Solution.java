package com.kickstart2021B.problem2;

import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

class Solution {

    public static void main(String[] args) {

        Instant start = Instant.now();

        Scanner input = new Scanner(new InputStreamReader(System.in));

        int t = input.nextInt();

        for (int i = 1; i <= t; ++i) {
            int n = input.nextInt();
            String s = input.next();
            char last = 0;
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (last < s.toCharArray()[j]) {
                    sum++;
                } else {
                    sum = 1;
                }
                last = s.toCharArray()[j];
                sb.append(sum).append(" ");
            }

            System.out.println("Case #" + i + ": " + sb);
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}
