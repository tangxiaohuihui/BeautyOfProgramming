package chapter2;

import java.util.LinkedList;
import java.util.Queue;

public class FindMinimumMMakeProductAll0and1 {

	/*
	 * 1.N*M����ʽ����1,10,11,100,101,110....ֱ����������ռ����� ���ҿ��Է���ǰ���ת������ ������˼·1.���н���ǰ��ת��
	 * 2.ֱ�ӽ�����i�Ķ����Ʊ�ʾת��Ϊ01���б�ʾ�Ĵ����� �����о���һ�����ṹ ���⣺����̫���ʱ��ͻ�������� 10000000000
	 */
	public static int findMinM1(int N) {
		if (N <= 0)
			return -1;
		if (N == 1)
			return 1;
		int i = 2;
		while (true) {
			String sequnce = Integer.toBinaryString(i);
			int decimal = Integer.parseInt(sequnce);
			// System.out.println(decimal);
			if (decimal % N == 0)
				return decimal;
			i++;
		}
	}

	// ����long�Ӵ�ռ伯
	public static long findMinM1X(int N) {
		if (N <= 0)
			return -1;
		if (N == 1)
			return 1;
		long i = 2;
		while (true) {
			String sequnce = Long.toBinaryString(i);
			long decimal = Long.parseLong(sequnce);
			//System.out.println(decimal + "����:" + decimal % N);
			if (decimal % N == 0)
				return decimal;
			i++;
		}
	}

	/*
	 * ������ֱ�����ö�����˼�� �� Ҳ������ǰ������� ָ�����ɺ����Ԫ�� �����Ƕ����Ĳ�α���
	 */
	public static long findMinM1Y(int N) {
		if (N <= 0)
			return -1;

		Queue<Long> queue = new LinkedList<Long>();
		queue.offer(1L);
		while (!queue.isEmpty()) {
			long e = queue.poll();
			if (e % N == 0)
				return e;
			queue.offer(e * 10);
			queue.offer(e * 10 + 1);
		}

		return -1;
	}

	/*
	 * 2.���ݶ�N���������з���
	 */
	public static int findMinM2(int N) {
		if (N <= 0) {
			return -1;
		}
		if (N == 1) {
			return 1;
		}
		// group[i] ��ʾģN����=i������(ֻ����01)
		int[] group = new int[N];
		group[1] = 1;   // 1 % N =1 
		int k = 2;
		while (true) {
			
			// ÿһ����ѯKλ�Ľ⣬Ҫ�������������ռ�
			for (int i = 0; i < N; i++) {
				int root = group[i];
				if (root == 0) {
					continue;
				}
				System.out.println("di : " + root);
				// ��õ�ǰ���λ�� ����Ϊi ������root��λ�� ��Kλ��������K-1λ���ɵ�
				int len = 0;
				int temp = root;
				while (temp != 0) { // �����Ǽ�λ����������K+1λʱֻ�迼��Kλ
					temp /= 10;
					len++;
				}
				System.out.println("len : " + len);
				if ((len + 1) == k) {
					int s = root * 10;
					int t = root * 10 + 1;
					System.out.println("���ڿ��� " + s + ", " + t);
					if (s % N == 0) {
						return s;
					} else if (group[s % N] == 0 ) {
						System.out.println(s + "����A ������" + (s%N));
						group[s % N] = s;
					}
					if (t % N == 0) {
						return t;
					} else if (group[t % N] == 0 ) {
						System.out.println(t + "���� B������" + (t%N));
						group[t % N] = t;
					}
				}
			}// end for
			k++;
		}
	}
	/*
	 * 3.�����������ܴ����������  ���Ը�ΪString����ʾ
	 */
	public static String findMinM3(int N) {
		if (N <= 0) {
			return "";
		}
		if (N == 1) {
			return "1";
		}
		// group[i] ��ʾģN����=i������(ֻ����01)
		String[] group = new String[N];
		group[1] = "1";   // 1 % N =1 
		int k = 2;
		while (true) {
			
			// ÿһ����ѯKλ�Ľ⣬Ҫ�������������ռ�
			for (int i = 0; i < N; i++) {
				String root = group[i];
				if (root == null) {
					continue;
				}
				// ��õ�ǰ���λ�� ����Ϊi ������root��λ�� ��Kλ��������K-1λ���ɵ�
				int len = root.length();
				System.out.println("len : " + len);
				if ((len + 1) == k) {
					String s = root + "0";
					String t = root + "1";
					// XXX������������Ƕ�Ӧ������ ���������һ��XXX
					int sRemain = (10 * i) % N;
					int tRemain = (10 * i + 1) % N;
					if (sRemain == 0) {
						return s;
					} else if (group[sRemain] == null || less(group[sRemain], s) ) {
						group[sRemain] = s;
					}
					if (tRemain== 0) {
						return t;
					} else if (group[tRemain] == null || less(group[tRemain], t)  ) {
						group[tRemain] = t;
					}
				}
			}// end for
			k++;
		}
	}

	// �Ƚ��ַ�����ʾ��������a�Ƿ�С��b
	//TODO  ������ֵ����Ǳ�ǰ��Ĵ� ����û��ʵ��
	private static boolean less(String a, String b) {
		return false;
	}

	/*****************************************************
	 * Test
	 */
	public static void main(String[] args) {
		//test1();
		test3();
	}

	public static void test1() {
		int N = 12;// N=35 ������
		// System.out.println(findMinM1(N));
		N = 36;
		System.out.println(findMinM1X(N)); // Can Work !!
		System.out.println("==========================");
		N = 40;
		System.out.println(findMinM1Y(N)); // Can Work !!
	}
	
	public static void test2() {
		int N = 35;
		System.out.println(findMinM1X(N)); 
		System.out.println("==================");
		N=3;
		System.out.println(findMinM2(N));
		
	}
	public static void test3() {
		int N = 3;
		System.out.println(findMinM3(N));
		
	}

}
