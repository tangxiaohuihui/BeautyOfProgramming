package chapter2;

import java.util.LinkedList;
import java.util.Queue;

public class FindMinimumMMakeProductAll0and1 {

	/*
	 * 1.N*M的形式就是1,10,11,100,101,110....直接在这个积空间搜索 并且可以发现前后的转换规律 有两种思路1.队列进行前后转化
	 * 2.直接将整数i的二进制表示转化为01序列表示的大整数 想象中就是一颗树结构 问题：整数太大的时候就会出现问题 10000000000
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

	// 利用long加大空间集
	public static long findMinM1X(int N) {
		if (N <= 0)
			return -1;
		if (N == 1)
			return 1;
		long i = 2;
		while (true) {
			String sequnce = Long.toBinaryString(i);
			long decimal = Long.parseLong(sequnce);
			//System.out.println(decimal + "余数:" + decimal % N);
			if (decimal % N == 0)
				return decimal;
			i++;
		}
	}

	/*
	 * 上面是直接利用二进制思想 ， 也可以有前面的整数 指数生成后面的元素 看成是对树的层次遍历
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
	 * 2.根据对N的余数进行分类
	 */
	public static int findMinM2(int N) {
		if (N <= 0) {
			return -1;
		}
		if (N == 1) {
			return 1;
		}
		// group[i] 表示模N余数=i的整数(只含有01)
		int[] group = new int[N];
		group[1] = 1;   // 1 % N =1 
		int k = 2;
		while (true) {
			
			// 每一次轮询K位的解，要考察整个余数空间
			for (int i = 0; i < N; i++) {
				int root = group[i];
				if (root == 0) {
					continue;
				}
				System.out.println("di : " + root);
				// 获得当前这个位置 余数为i 的整数root的位数 在K位的数是有K-1位生成的
				int len = 0;
				int temp = root;
				while (temp != 0) { // 计算是几位整数。计算K+1位时只需考虑K位
					temp /= 10;
					len++;
				}
				System.out.println("len : " + len);
				if ((len + 1) == k) {
					int s = root * 10;
					int t = root * 10 + 1;
					System.out.println("现在考虑 " + s + ", " + t);
					if (s % N == 0) {
						return s;
					} else if (group[s % N] == 0 ) {
						System.out.println(s + "属于A 余数：" + (s%N));
						group[s % N] = s;
					}
					if (t % N == 0) {
						return t;
					} else if (group[t % N] == 0 ) {
						System.out.println(t + "属于 B余数：" + (t%N));
						group[t % N] = t;
					}
				}
			}// end for
			k++;
		}
	}
	/*
	 * 3.上述方法不能处理大数问题  所以改为String来表示
	 */
	public static String findMinM3(int N) {
		if (N <= 0) {
			return "";
		}
		if (N == 1) {
			return "1";
		}
		// group[i] 表示模N余数=i的整数(只含有01)
		String[] group = new String[N];
		group[1] = "1";   // 1 % N =1 
		int k = 2;
		while (true) {
			
			// 每一次轮询K位的解，要考察整个余数空间
			for (int i = 0; i < N; i++) {
				String root = group[i];
				if (root == null) {
					continue;
				}
				// 获得当前这个位置 余数为i 的整数root的位数 在K位的数是有K-1位生成的
				int len = root.length();
				System.out.println("len : " + len);
				if ((len + 1) == k) {
					String s = root + "0";
					String t = root + "1";
					// XXX数组的索引就是对应的余数 充分利用这一点XXX
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

	// 比较字符串表示的整数的a是否小于b
	//TODO  后面出现的总是比前面的大 这里没有实现
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
		int N = 12;// N=35 出问题
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
