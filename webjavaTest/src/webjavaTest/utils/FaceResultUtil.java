package webjavaTest.utils;

public class FaceResultUtil {
	public static String getCode(String result){
		return result.split(",")[0].split(":")[1];
	}
}
