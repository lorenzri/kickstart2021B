package com.kickstart2021B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Solution {

	public static void main(String[] args) {

		Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		int t = input.nextInt();

		for (int i = 1; i <= t; ++i) {
			int n = input.nextInt();
			String s = input.next().trim();
			int[] o = new int[n];
			char last = 0;
			int sum = 0;
			for (int j = 0; j < n; j++) {
				if(j == 0 || last < s.toCharArray()[j]){
					sum++;
					last = s.toCharArray()[j];
				} else {
					sum = 1;
				}
				o[j] = sum;
			}

			StringBuilder sb = new StringBuilder();
			for (int oi : o) {
				sb.append(oi).append(" ");
			}

			System.out.println("Case #" + i + ": " + sb);
		}
	}
}
