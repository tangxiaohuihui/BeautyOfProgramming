package chapter2;

public class IDMoreThanHalf {
	
	/*
	 * 1. ������ Ȼ��ͳ��ÿ��ID���ֵĴ���   O(NlogN + N)
	 */
	/*
	 * 2. �����򣬳��ִ�������һ������ֿ϶����м�    O(NlogN)  
	 */
	/*
	 * 3. �������и����ֳ��ֵĴ������������鳤�ȵ�һ�� ��˵���и����ֳ��ֵĴ����������������ֳ��ִ����ĺͻ�Ҫ�ࡣ
	 * ��˿��ԶԳ���Ƶ�ν����ۼƣ�����������һ�����ֵ�ʱ�������һ�����ֺ�����֮ǰ�����������ͬ���������1��
	 * �����һ�����ֺ�����֮ǰ��������ֲ�ͬ���������1���������Ϊ�㣬������Ҫ������һ�����֣����Ѵ���������Ϊ1��
	 * ��󱣴�����ֿ϶��ǳ��ִ�������һ���  ��Ϊ��������ô��εĵ��� ����Ȼ����
 We will sweep down the sequence starting at the pointer position shown above.

As we sweep we maintain a pair consisting of a current candidate and a counter. 
Initially, the current candidate is unknown and the counter is 0.

When we move the pointer forward over an element e:
If the counter is 0, we set the current candidate to e and we set the counter to 1.
If the counter is not 0, we increment or decrement the counter according to whether e is the current candidate.
When we are done, the current candidate is the majority element, if there is a majority.
	 */
	public static int find(int ID[], int N) throws Exception{
		if(ID == null || ID.length <= 0)
			return -1;
		int candidate = 0;
		int nTimes, i;
		for(i=nTimes=0; i<N; i++){
			if(nTimes == 0){// a new round
				candidate = ID[i];
				nTimes = 1;
			}else{
				if(candidate == ID[i])
					nTimes ++;
				else nTimes --;
				
			}
		}
		
		//��������Ƿ���Ч
		i = 0;
		for(int j=0; j<N;j++){
			if(candidate == ID[j])
				i++;
		}
		if(i * 2 <= N)
			throw new Exception("Invalid Input");
		
		return candidate;
	}
	
	/**********************************************************
	 *  Test !!
	 * @throws Exception 
	 */
	public static void test0() throws Exception{
		int[] a ={1,2,3,4,};// {0,1,2,1,1};
		System.out.println(find(a, a.length));
	}
	public static void main(String[] args) throws Exception {
		test0();
	}

}


class Type{
	int id;
	String name;
	String content;
	
}




