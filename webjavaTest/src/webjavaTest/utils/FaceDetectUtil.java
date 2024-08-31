package webjavaTest.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class FaceDetectUtil {
	
	
	/**
	 * 获得分析结果中是否包含人脸
	 * @param json
	 * @return  true-检测到人脸，false-未检测到人脸
	 */
	public static boolean checkFace(String json){	
		JSONObject dataJson= new JSONObject(json); 
        String code = (String) dataJson.get("error_code").toString(); 
		 return  code.equals("0")?true:false;
	}
	
	
	/**
	 * 获得分析结果中的性别
	 * @param json
	 * @return
	 */
	public static String getInfo(String json,String p,String key){	
        JSONArray face_lists = getFaceList(json);//  用JSONArray对象获取face_list数组数据
        String value = "";
		 for(int i=0;i<face_lists.length();i++){//循环数组，取出想要的数据
		            JSONObject face1 = (JSONObject) face_lists.get(i);
		            if(p!=null){
		            	    value= (String) face1.get(p).toString();
				            JSONObject sexdata = new JSONObject(value);
				            value= (String) sexdata.get(key);
		            }else{
		            	value = face1.get(key).toString();
		            }
		        }
		 return value;
	}
	
	
	/**
	 * 返回人脸标识信息
	 * @param json
	 * @return
	 */
	public static JSONArray getFaceList(String json){	
		JSONObject dataJson= new JSONObject(json);//把字符串转成json数据
		json = (String) dataJson.get("result").toString();//获取到json数据中result大括号内的数据
        JSONObject face_list = new JSONObject(json);//把result内的字符串转成json数据，这边我要获取face_list数组里的数据
        JSONArray face_lists = face_list.getJSONArray("face_list");//  用JSONArray对象获取face_list数组数据
            return face_lists;
	}
}

