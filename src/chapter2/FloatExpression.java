package chapter2;

public class FloatExpression {
	
	// 输入（循环）小数 假设输入是规则的  并且没有考虑大数情况 TODO
	public static String fractionFormat(String input){
		int flag = input.indexOf('.');
		if(flag < 0)
			return input;
		flag = input.indexOf('(');
		if(flag < 0)
			return finiteDecimal(input);
		
		return infiniteDecimal(input);
	}

	private static String finiteDecimal(String input) {
		int mid = input.indexOf('.');
		String left = input.substring(0,mid);
		String right = input.substring(mid+1);//
		return minimumfiniteForm(left, right);
	}

	private static String minimumfiniteForm(String left, String right) {
		int x = Integer.parseInt(right);
		int y = (int)Math.pow(10, right.length());
		int gcd = GCD.gcd3(x, y);
		x /= gcd;
		y /= gcd;
		return left + "右"+ x + "/" + y;
	}

	private static String infiniteDecimal(String input) {
		int point = input.indexOf('.');
		String left = input.substring(0,point);
		String right = input.substring(point+1);//
		int bracket = right.indexOf('(');
		String certain = right.substring(0, bracket);
		String uncertain = right.substring(bracket+1, right.length()-1);
		
		return minimumInfiniteForm(left, certain, uncertain);
	}
	
	private static String minimumInfiniteForm(String left, String certain, String uncertain) {
		int N = certain.length();
		int M = uncertain.length();
		if(N==0)
			certain = "0";
		int x = Integer.parseInt(certain);
		int y = Integer.parseInt(uncertain);
		x = x * ((int)Math.pow(10, M)-1) + y;
		y = ((int)Math.pow(10, M)-1) * (int)Math.pow(10, N);
		
		int gcd = GCD.gcd3(x, y);
		x /= gcd;
		y /= gcd;
		return left + "右"+ x + "/" + y;
	}

	/*********************************
	 * Test
	 */
	public static void test(){
		System.out.println(fractionFormat("124.(33)"));
	}
	
	public static void main(String[] args) {
		//String s = "lasjf.lasjdflsa";
		//System.out.println(s.indexOf('.'));
		test();
	}
	

}
