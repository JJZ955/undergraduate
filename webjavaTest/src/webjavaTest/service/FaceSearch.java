package webjavaTest.service;


import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import webjavaTest.bean.User;
import webjavaTest.dao.UserDao;
import webjavaTest.utils.FaceResultUtil;
import webjavaTest.utils.GsonUtils;
import webjavaTest.utils.HttpUtil;

/**
* 人脸搜索
*/
public class FaceSearch {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
	
	/*public static String getInfo(String json,String p,String key){
		JSONArray face_lists = getFaceList(json);
		String value = null;
		for(int i=0;i<face_lists.length();i++){
			JSONObject face1 = (JSONObject) face_lists.get(i);
			if(p!=null){
				value = (String) face1.get(p).toString();
				JSONObject sexdata = new JSONObject(value);
				value = (String) sexdata.get(key);
			}else{
				value = face1.get(key).toString();
			}
		}
		return value;
	}*/
	
	public static Map<String, Object> faceSearch(String image) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
        	
        	Map<String, Object> resultMap = new HashMap<>();//将score和user放在这个变量中
        	
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_1");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            map.put("user_id", "admin");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            
            String error_code=FaceResultUtil.getCode(result);
            String scoreStr = null;
            if(error_code.equals("0")){
            	String temp=result.split(",")[9];
            	/*scoreStr=temp.substring(temp.indexOf(":")+1,temp.indexOf("."));
            	System.out.println(scoreStr);*/
            	
            	//截取user_info
            	String user_info=result.split("user_info\":\"")[1].split("\"")[0];
            	
            	 scoreStr=temp.substring(temp.indexOf(":")+1,temp.indexOf("."));
            	//AI搜索的人脸返回的User_info ，跟数据库用户名去匹配
            	UserDao userdao = new UserDao();
            	User user= userdao.getUserByName(user_info);
            	
            	resultMap.put("score",scoreStr);
            	resultMap.put("user",user);
            	System.out.println(user);
            }
            //System.out.println(result);
            //return scoreStr;
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   
 
}