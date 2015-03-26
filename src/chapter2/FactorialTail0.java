package chapter2;

public class FactorialTail0 {
	
	/*
	 * 2-6
	 * 就是看n质因数分解后有多少个5，决定了0的个数
	 */
	public static int tail0(int n){
		int ret = 0;
		for(int i=1; i<=n; i++){
			int j = i;
			while(j % 5 == 0){
				ret ++;
				j /= 5;
			}
		}
		
		return ret;
	}
	
	/*
	 * 2-6-2
	 * 发现规律 质因数中5的个数 Z = [N/5] + [N/5^2] + [N/5^3] +.....
	 */
	public static int tail1(int n){
		int ret = 0;
		while(n != 0){
			ret += n / 5;
			n /= 5;
		}
		
		return ret;
	}
	
	
	
	
	
	
	
	
	/**********************************************************
	 *  Test !!
	 */
	public static void test0(){
		System.out.println(tail1(10));
	}
	public static void main(String[] args) {
		test0();
	}
}
