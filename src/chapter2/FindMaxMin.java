package chapter2;

public class FindMaxMin {
	/*
	 * 1. 扫描一遍 每次会比较两次以便跟新最大最小值   O(2N)
	 */
	public static int[] search1(int[] arr){
		if(arr == null || arr.length <= 0)
			return null;
		int[] res = new int[2]; // Max , Min
		int max, min;
		max = min = arr[0];
		for(int i=1; i<arr.length; i++){
			if(arr[i]>max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}
		res[0] = max;
		res[1]=min;
		return res;
	}
	
	/*
	 * 2. 以Step = 2 扫描数组   比较三次 但遍历长度是N/2.O(1.5N)
	 */
	public static int[] search2(int[] arr){
		if(arr == null || arr.length <= 0)
			return null;
		int[] res = new int[2]; // Max , Min
		int max, min;
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		int i=0;
		for(; i<arr.length-1; i+=2){
			int a = arr[i]>arr[i+1] ? arr[i] : arr[i+1];
			int b = arr[i]>arr[i+1] ? arr[i+1] : arr[i];
			if(a>max)
				max = arr[i];
			if(b < min)
				min = arr[i];
		}
		// 长度为奇数的最后还有一个元素未考虑
		if((arr.length & 0x01) == 1){
			i = arr.length-1;
			if(arr[i]>max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}
		
		res[0] = max;
		res[1]=min;
		return res;
	}
	
	/*
	 * 3. 采用分而治之的思想  
	 */
	public static int[] search3(int[] arr){
		if(arr == null || arr.length <= 0)
			return null;
		return searchCore(arr, 0, arr.length-1);
	}
	
	private static int[] searchCore(int[] arr, int from, int to) {
		if(to-from <= 1){
			if(arr[from] < arr[to])
				return new int[]{arr[to], arr[from]};
			else return new int[]{arr[from], arr[to]};
		}
		int[] L = searchCore(arr, from, (from+to)/2);
		int[] R = searchCore(arr, (from+to)/2 + 1, to);
		int max, min;
		if(L[0] > R[0])
			max = L[0];
		else max = R[0];
		if(L[1] < R[1])
			min = L[1];
		else min = R[1];
		return new int[]{max,min};
	}

	/***********************************************
	 *   TEST
	 */
	public static void main(String[] args) {
		int[] a = {5,6,8,3,7,9,12,-12,99};
		int[] res = search3(a);
		System.out.println(res[0] + ","+ res[1]);
	}
}
