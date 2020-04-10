package ForOnlineProgramming;

import java.util.Scanner;

public class ti1 {

	static int[] xingyunshu = new int[9];

	static int[] xingyunshuMeiTi = new int[9];

	public static void main(String[] args) {

		for (int j = 0; j < xingyunshu.length; j++) {
			xingyunshu[j] = 0;
		}

		Scanner input = new Scanner(System.in);

		int zongHangSHu = input.nextInt();

		for (int i = 0; i < zongHangSHu; i++) {

			for (int j = 0; j < xingyunshuMeiTi.length; j++) {
				xingyunshuMeiTi[j] = 0;
			}
			
			int a = input.nextInt();
			//int a = 15;
			sumShuZi(String.valueOf(a));
			
			int b = input.nextInt();
			//int b = 20;
			sumShuZi(String.valueOf(b));

			sumShuZi(String.valueOf(a * b));

			zhongjianShu(a, b);

			for (int j = 0; j < xingyunshuMeiTi.length - 1; j++) {
				System.out.print(xingyunshuMeiTi[j] + " ");
			}
			System.out.println(xingyunshuMeiTi[8]);
		}

		int zuiZhongXYS = 0;
		int tempS = -1;
		for (int i = 0; i < xingyunshu.length; i++) {
			if (xingyunshu[i] > tempS) {
				tempS = xingyunshu[i];
				zuiZhongXYS = i + 1;
			}
		}

		System.out.println(zuiZhongXYS);

		input.close();

	}

	public static void zhongjianShu(int a, int b) {

		char[] bShuZiChar = String.valueOf(b).toCharArray();

		for (char c : bShuZiChar) {
			int d = Integer.parseInt(String.valueOf(c));
			sumShuZi(String.valueOf(a * d));
		}

	}

	public static void sumShuZi(String shuzi) {

		char[] shuziChar = shuzi.toCharArray();
		for (char c : shuziChar) {
			if (c == '1') {
				xingyunshu[0] = xingyunshu[0] + 1;
				xingyunshuMeiTi[0] = xingyunshuMeiTi[0] + 1;
			}
			if (c == '2') {
				xingyunshu[1] = xingyunshu[1] + 1;
				xingyunshuMeiTi[1] = xingyunshuMeiTi[1] + 1;
			}
			if (c == '3') {
				xingyunshu[2] = xingyunshu[2] + 1;
				xingyunshuMeiTi[2] = xingyunshuMeiTi[2] + 1;
			}
			if (c == '4') {
				xingyunshu[3] = xingyunshu[3] + 1;
				xingyunshuMeiTi[3] = xingyunshuMeiTi[3] + 1;
			}
			if (c == '5') {
				xingyunshu[4] = xingyunshu[4] + 1;
				xingyunshuMeiTi[4] = xingyunshuMeiTi[4] + 1;
			}
			if (c == '6') {
				xingyunshu[5] = xingyunshu[5] + 1;
				xingyunshuMeiTi[5] = xingyunshuMeiTi[5] + 1;
			}
			if (c == '7') {
				xingyunshu[6] = xingyunshu[6] + 1;
				xingyunshuMeiTi[6] = xingyunshuMeiTi[6] + 1;
			}
			if (c == '8') {
				xingyunshu[7] = xingyunshu[7] + 1;
				xingyunshuMeiTi[7] = xingyunshuMeiTi[7] + 1;
			}
			if (c == '9') {
				xingyunshu[8] = xingyunshu[8] + 1;
				xingyunshuMeiTi[8] = xingyunshuMeiTi[8] + 1;
			}
		}
	}

}
