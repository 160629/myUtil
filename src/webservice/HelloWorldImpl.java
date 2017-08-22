package webservice;

public class HelloWorldImpl implements HelloWorld{

	@Override
	public String sayhello(String name, int age) {
		
		return "Hello,"+name+"("+age+"year old)";
	}
	
	static int [] array = {1,3,23,27,45,49,88};
	
	public int halfMethod(int [] param, int key){
		
		int min = 0;
		
		int max = param.length-1;
		
		int mid = (max-min)/2;
		
		while(max > mid){
			
		
			if(param[mid]>key){
				
				max = mid - 1;
				mid = (max-min)/2;
			}
			
			if(param[mid]<key){
				
				min = mid + 1;
				mid = (max-min)/2+min;
			}
			
			if(param[mid] == key){
				
				return param[mid];
			}
		
		}
		
		return -1;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(new HelloWorldImpl().halfMethod(array,1));
	}
	
}
