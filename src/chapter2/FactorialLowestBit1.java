package chapter2;

public class FactorialLowestBit1 {

	/*
	 * 2-7
	 * Ϊ���ҵ����ұ�bit 1����λ�� ��������һ�����ƵĹ���
	 * ����ת��Ϊ��N!��������2�ĸ���
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
	 * N!��������2�ĸ��� = N - N�Ķ����Ʊ�ʾ��1�ĸ���
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
