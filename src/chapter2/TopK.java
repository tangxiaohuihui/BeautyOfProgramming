package chapter2;

import java.util.Arrays;

public class TopK {

	/*
	 * 1. ������Ȼ���ҵ�����K���� ���Ӷ�������ĸ��Ӷ� O(NlogN)
	 */
	public static int[] topK1(int[] A, int N, int k) {
		// �߽紦��
		int[] res = new int[k];
		Arrays.sort(A);
		int j = 0;
		for (int i = A.length - k; i <= A.length - 1; i++)
			res[j++] = A[i];

		return res;
	}

	/*
	 * 2.�����б�Ҫ��ȫ��������������> ����ά��K��Ԫ������Ŀ����� O(N*K) ����Ҫ��������� K < logN�������ѡ�񲿷�����
	 * ������˳����� �ʺ������� ����ȫ�������ڴ�
	 */
	public static int[] topK2(int[] A, int N, int k) {
		// �߽紦��
		int[] res = new int[k];
		for (int i = 0; i < k; i++)
			res[i] = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			insertSortK(res, k, A[i]);
		}

		return res;
	}

	// ���߸ɴ��ҵ�����res�е���СԪ�� �滻֮ ����
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
	 * 3. ���ÿ��������˼�� ת����С���� O(NlogK)
	 */
	/*
	 * 4. ������С�����ݽṹ ÿ�ζ���һ��Ԫ�����ȶѶ�Ԫ�ش���������� ���жѵ��� ������������е�Ԫ�ؼ��� O(NlogK)
	 */

	public static int[] topK4(int[] A, int N, int k) {
		// heap size is k
		int[] res = Arrays.copyOfRange(A, 0, k);
		// ����һ��С����
		for (int j = k / 2 - 1; j >= 0; j--)
			sink(res, j, k);
		//System.out.println("HHHH: " + Arrays.toString(res));
		// Ȼ��Ѻ�����Ԫ�ز��뵽�������
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
			k = j; // ���µݹ�
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
	 * 5. ��������������һ����С�ķ�Χ֮�� ��ɿ����ü�������  O(N)
	 * ����˼��
	 */
	static final int R = 100;  // ���綼��1-100��Χ��
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
		// Ȼ�������k�����ҵ�
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
