package chapter2;


/*
 * Ҫ�ı�m�Ķ���λ�������ж�m��n�ж���λ�ǲ�ͬ��
 * ��������� Ȼ��ͳ��
 */
public class BitsReverseTotal {
	public static int bitsReverseTotal(int m, int n){
		int x = m ^ n;
		
		return NumberOf1InBinary.bit1Number4(x);
	}
	
	public static void main(String[] args) {
		System.out.println(bitsReverseTotal(10, 13));
		System.out.println(bitsReverseTotal(0, -1));
	}
}
