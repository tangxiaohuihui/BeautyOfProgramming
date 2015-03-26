package chapter2;

public class FactorialTail0 {
	
	/*
	 * 2-6
	 * ���ǿ�n�������ֽ���ж��ٸ�5��������0�ĸ���
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
	 * ���ֹ��� ��������5�ĸ��� Z = [N/5] + [N/5^2] + [N/5^3] +.....
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
