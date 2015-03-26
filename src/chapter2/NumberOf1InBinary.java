package chapter2;

public class NumberOf1InBinary {
	
	/*
	 * 2-1
	 * ���λΪ1 ���������
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
	 * 2-2 ��Ϊλ���� ����ͬ��
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
	 * �������ⷢ����ԭ���� ���ƺ��λ������1 ��ô�����أ�
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
	 *   �����߼�����  IN  JAVA
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
	
	//  ������������ ����������λ��  ����ǰ���Ƕ�������λ֪����   ��C������ƽ̨��ص� ��
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
	 *  �����ķ���, ����ʹ��:n & (n-1)������n���ұߵ�bit 1 
	 *  O(M) , M��n�� bit 1�ĸ���
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
