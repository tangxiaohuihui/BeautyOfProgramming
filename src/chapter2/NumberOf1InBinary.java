package chapter2;

public class NumberOf1InBinary {
	
	/*
	 * 2-1
	 * 最高位为1 会出现问题
	 */
	public static int bit1Number0(int n){
		int count = 0;
		while(n != 0){
			if(n%2 == 1)
				count ++;
			n = n / 2;
		}
		
		return count;
	}
	
	/*
	 * 2-2 改为位操作 问题同上
	 */
	public static int bit1Number1(int n){
		int count = 0;
		while(n != 0){
			if((n & 1) != 0)
				count ++;
			n = n >> 1;
		}
		
		return count;
	}
	
	/*
	 * 上述问题发生的原因是 右移后高位填充的是1 那么左移呢？
	 * OK
	 */
	public static int bit1Number1X(int n){
		int count = 0;
		int i=1;
		while(i != 0){
			if((n & i) != 0)
				count ++;
			i = i << 1;
		}
		
		return count;
	}
	
	/*
	 *   利用逻辑右移  IN  JAVA
	 */
	public static int bit1Number2(int n){
		int count = 0;
		while(n != 0){
			if((n & 1) != 0)
				count ++;
			n = n >>> 1;
		}
		
		return count;
	}
	
	//  在上述基础上 控制整数的位数  但是前提是二进制数位知道的   在C里面是平台相关的 ？
	public static int bit1Number1My(int n){
		int count = 0;
		for(int i=0; i < 32;i++){
			if((n & 1) != 0)
				count ++;
			n = n >>> 1;
		}
		
		return count;
	}
	
	/*
	 *  2-3
	 *  更吊的方法, 经常使用:n & (n-1)会消除n最右边的bit 1 
	 *  O(M) , M是n中 bit 1的个数
	 */
	public static int bit1Number4(int n){
		int count = 0;
		while(n!=0){// have bit 1 in n
			count ++;
			n = (n-1) & n; 
		}
		
		return count;
	}
	
	
	/************************************************
	 *  Test Above
	 */
	public static void test0(){
		System.out.println(bit1Number1(0x0044));
		System.out.println(bit1Number1(0x80000040));  // dead loop
	}
	public static void test1(){
		System.out.println(bit1Number1X(0x0044));
		System.out.println(bit1Number1X(0x80000040));  // Yes OK
		System.out.println(bit1Number1X(-1));
	}
	public static void test2(){
		System.out.println(bit1Number2(0x0044));
		System.out.println(bit1Number2(0x80000040));  // OK!!!
	}
	
	public static void test4(){
		System.out.println(bit1Number4(0x0044));
		System.out.println(bit1Number4(0x80000040));  
	}
	
	public static void main(String[] args) {
		test4();
	}

}
