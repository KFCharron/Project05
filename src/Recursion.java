public class Recursion {
	
	
	int[] diskNumber = new int[100000];
	char[] fromShaft = new char[100000];
	char[] toShaft = new char[100000];
	int moveCount = 0;
	
	public Recursion()
	{
	}
	
	public float SummingSeries(float n)
	{	
		if (n == 1)
			return 1;
			
		else
			return (n/(2*n - 1)) + SummingSeries(n-1);
	}
	
	public float Combination(int n, int k)
	{
		return factorial(n) / ( factorial(k) * factorial(n - k));
	}
	
	public void TowersOfHanoi(int n, char from, char to, char using)
	{

		
		
		if (n == 1){
			diskNumber[moveCount] = n;
			fromShaft[moveCount] = from;
			toShaft[moveCount] = to;
			moveCount++;
		}
		else {
			TowersOfHanoi(n-1, from, using, to);
			diskNumber[moveCount] = n;
			fromShaft[moveCount] = from;
			toShaft[moveCount] = to;
			moveCount++;
			TowersOfHanoi(n-1, using, to, from);
			
			
			
		}
	}
	
	public long factorial (long n)
	  {
	    if (n == 1)                                 
	    	return 1;
	    else                                               
	    	return (n * factorial ( n - 1 ));
	  }
}
