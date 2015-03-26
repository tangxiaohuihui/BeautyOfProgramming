package chapter2;

public class FactorialLowestBit1 {

	/*
	 * 2-7
	 * 为了找到最右边bit 1所在位置 想象中有一个右移的过程
	 * 问题转换为求N!中质因数2的个数
	 */
	public static int lowestOne(int n){
		int ret = 0;
		
		while(n != 0){
			n >>= 1;
			ret += n;
		}
		
		return ret + 1;
	}
	
	/*
	 * N!中质因数2的个数 = N - N的二进制表示中1的个数
	 */
	public static int lowestOne2(int n){
		int ret = 0;
		
		ret = n - NumberOf1InBinary.bit1Number4(n);
		
		return ret + 1;
	}
	
	/**********************************************************
	 *  Test !!
	 */
	public static void test0(){
		System.out.println(lowestOne(4));
		System.out.println(lowestOne2(4));
	}
	public static void main(String[] args) {
		test0();
	}
}
