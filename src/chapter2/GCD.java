package chapter2;

import java.math.BigInteger;

public class GCD {
	/*
	 * 1. 直接辗转相除法 f(x,y) = f(y, x%y)
	 */
	public static int gcd1(int x, int y){
		return (y==0) ? x : gcd1(y, x%y);
	}
	
	/*
	 * 2. 为了改善上面取模运算 采用 f(x,y) = f(x-y,y)
	 * 如果太大 容易发生StackOverflowError 所以不能暴力递归，并且用减法的话递减速率很慢
	 */
	public static BigInteger gcd2(BigInteger x, BigInteger y){
		
		if(x.compareTo(y)  < 0)
			return gcd2(y, x);
		if(y.equals(0))
			return x;
		
		return gcd2(x.subtract(y), y);
	}
	
	/*
	 * 上述是尾递归 容易转换为循环
	 */
	public static BigInteger gcd2Iter(BigInteger x, BigInteger y){
		
		while(!y.equals(BigInteger.ZERO)){
			if(x.compareTo(y) < 0){
				BigInteger tmp = x;
				x = y;
				y = tmp;
				
			}else {
				x = x.subtract(y);
			}
		}
		return x;
	}
	
	/*
	 * 3. 加快递减速度 同时利用位运算的优势
	 */
	public static int gcd3(int x, int y){
		if(x < y)
			return gcd3(y, x);
		if(y==0)
			return x;
		else{
			if(isEven(x)){
				if(isEven(y))
					return (gcd3(x>>1, y>>1)) << 1;//NB.
				else return gcd3(x>>1, y);
			}else{
				if(isEven(y))
					return gcd3(x, y>>1);
				else return gcd3(y, x-y);
			}
		}
	}
	private static boolean isEven(int x) {
		return (x & 0x01) == 0;
	}
	
	

	/***********************************************
	 * Test
	 */
	public static void test(){
		System.out.println(gcd1(20, 42));
		System.out.println(gcd3(20, 42));
	}
	public static void test2(){
		BigInteger x = new BigInteger("12488868467488");
		BigInteger y = new BigInteger("868467488");  // StackOverflowError
		System.out.println(gcd2(x,y));
	}
	
	
	//虽然耗时  但是不会栈溢出
	public static void test3(){
		BigInteger x1 = new BigInteger("42");
		BigInteger y1 = new BigInteger("30");  
		System.out.println(gcd2Iter(x1,y1));
		
		BigInteger x = new BigInteger("8868467488");
		BigInteger y = new BigInteger("868467488");  // 很快运行完
		System.out.println(gcd2Iter(x,y));
	}
	public static void main(String[] args) {
		test3();
		//BigInteger x = new BigInteger("0");
		//System.out.println(x.equals(0));
	}
}
