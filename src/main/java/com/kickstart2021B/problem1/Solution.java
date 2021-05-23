package com.kickstart2021B.problem1;

import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

class Solution {

    public static void main(String[] args) {

        Instant start = Instant.now();

        for (int i = 1; i <= t; ++i) {
            int n = input.nextInt();
            String s = input.next();
            char last = 0;
            int sum = 0;
            StringBuilder sb = new StringBuilder(s.length()*2);
            for (int j = 0; j < n; j++) {
                char comp = s.charAt(j);
                if (last < comp) {
                    sum++;
                } else {
                    sum = 1;
                }
                last = comp;
                sb.append(sum).append(" ");
            }
            System.out.println("Case #" + i + ": " + sb);
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}
