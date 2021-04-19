package com.kickstart2021B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

class Solution {

	public static void main(String[] args) {

		Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		long t = input.nextLong();

		for (long i = 1; i <= t; i++) {
			long n = input.nextLong();
			String s = input.next();
			long[] o = new long[n];
			char last = 250;
			long sum = 0;
			for (int j = 0; j < n; j++) {
				if(j == 0 || last < s.toCharArray()[j]){
					sum++;
					last = s.toCharArray()[j];
				} else {
					sum = 1;
				}
				o[j] = sum;
			}

			StringBuffer os = new StringBuffer();
			for (int oi : o) {
				os.append(oi).append(" ");
			}

			System.out.println("Case #" + i + ": " + os.substring(0, os.length()-1));
		}
	}
}
