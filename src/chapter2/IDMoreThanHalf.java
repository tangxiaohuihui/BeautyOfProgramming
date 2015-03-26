package chapter2;

public class IDMoreThanHalf {
	
	/*
	 * 1. 先排序 然后统计每个ID出现的次数   O(NlogN + N)
	 */
	/*
	 * 2. 先排序，出现次数超过一半的数字肯定在中间    O(NlogN)  
	 */
	/*
	 * 3. 数组中有个数字出现的次数超过了数组长度的一半 ，说明有个数字出现的次数比其他所有数字出现次数的和还要多。
	 * 因此可以对出现频次进行累计，当遍历到下一个数字的时候，如果下一个数字和我们之前保存的数字相同，则次数加1。
	 * 如果下一个数字和我们之前保存的数字不同，则次数减1。如果次数为零，我们需要保存下一个数字，并把次数重新设为1。
	 * 最后保存的数字肯定是出现次数超过一半的  因为经过了那么多次的抵消 它仍然存在
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
		
		//检查输入是否有效
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




