package webjavaTest.test;

public class Test {
	public static void main(String[] args){
		String result = "{abc:123,sdf:4,lla:65,score:78}";
		
		//取出score后的值
		String arr=result.split(",")[3];
		
		/*for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}*/
		
		System.out.println(arr.substring(arr.indexOf(":")+1,arr.length()-1));
		
		
	}
}
