package chapter2;

import java.util.Arrays;

public class TopK {

	/*
	 * 1. 先排序然后找到最大的K个数 复杂度是排序的复杂度 O(NlogN)
	 */
	public static int[] topK1(int[] A, int N, int k) {
		// 边界处理
		int[] res = new int[k];
		Arrays.sort(A);
		int j = 0;
		for (int i = A.length - k; i <= A.length - 1; i++)
			res[j++] = A[i];

		return res;
	}

	/*
	 * 2.但是有必要对全部的数据排序吗> 仅仅维持K个元素有序的开销是 O(N*K) 所以要看具体情况 K < logN的情况下选择部分排序
	 * 这里是顺序读入 适合流处理 不必全部载入内存
	 */
	public static int[] topK2(int[] A, int N, int k) {
		// 边界处理
		int[] res = new int[k];
		for (int i = 0; i < k; i++)
			res[i] = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			insertSortK(res, k, A[i]);
		}

		return res;
	}

	// 或者干脆找到数组res中的最小元素 替换之 即可
	private static void insertSortK(int[] res, int k, int x) {
		if (x < res[k - 1])
			return;
		int i = res.length - 1;
		while (i >= 0 && res[i] <= x)
			i--;
		i++;
		// System.out.println("insert: " + i);
		for (int j = res.length - 1; j > i; j--)
			res[j] = res[j - 1];
		res[i] = x;
	}

	/*
	 * 3. 利用快速排序的思想 转换成小问题 O(NlogK)
	 */
	/*
	 * 4. 采用最小堆数据结构 每次读入一个元素若比堆顶元素大则插入其中 进行堆调整 最后输出这个堆中的元素即可 O(NlogK)
	 */

	public static int[] topK4(int[] A, int N, int k) {
		// heap size is k
		int[] res = Arrays.copyOfRange(A, 0, k);
		// 构造一个小顶堆
		for (int j = k / 2 - 1; j >= 0; j--)
			sink(res, j, k);
		//System.out.println("HHHH: " + Arrays.toString(res));
		// 然后把后续的元素插入到这个堆中
		for(int j=k; j<N; j++){
			if(A[j] > res[0]){
				res[0] = A[j];
				sink(res, 0, k);
			}
		}
		return res;
	}

	private static void sink(int[] pq, int k, int N) {
		while (2 * k < N) {
			int j = 2 * k;
			if (j < N && !less(pq, j, j + 1))
				j++;
			if (less(pq, k, j))
				break;
			exch(pq, k, j);
			k = j; // 往下递归
		}
	}

	private static boolean less(int[] pq, int i, int j) {
		return pq[i] < pq[j];
	}

	private static void exch(int[] pq, int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}
	
	
	/*
	 * 5. 如果输入的数据在一个较小的范围之内 则可考虑用计数排序  O(N)
	 * 分组思想
	 */
	static final int R = 100;  // 比如都在1-100范围内
	public static int[] topK5(int[] A, int N, int k) {
		int[] res = new int[k];
		int[] count = new int[R+1];
		for(int i=0; i<N; i++){
			count[A[i]] ++;
		}
		System.out.println(Arrays.toString(count));
		int point = 0, topCount =0;
		for(point=R; point>=0; point--){
			topCount += count[point];
			if(topCount >= k)
				break;
		}
		// 然后把最大的k个数找到
		int j=0;
		for(int i=point; i<=R && j<k; i++)
			if(count[i] > 0)
				res[j++] = i;
	
		return res;
	}
	

	/***********************************************
	 * Test
	 */
	public static void test() {
		int A[] = { 2, 34, 2, 6667, 34, 93, 8, 34 };
		int res[] = topK4(A, A.length, 4);
		System.out.println(Arrays.toString(res));
	}
	
	public static void test2() {
		int A[] = { 2, 34, 2, 99,78 ,100, 34, 93, 8, 34, 43, 45,32, 4,89,44,67 };
		int res[] = topK5(A, A.length, 4);
		System.out.println(Arrays.toString(res));
	}

	public static void main(String[] args) {
		test2();
	}
}
