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
* ��������
*/
public class FaceSearch {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
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
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
        	
        	Map<String, Object> resultMap = new HashMap<>();//��score��user�������������
        	
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_1");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            map.put("user_id", "admin");

            String param = GsonUtils.toJson(map);

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            
            String error_code=FaceResultUtil.getCode(result);
            String scoreStr = null;
            if(error_code.equals("0")){
            	String temp=result.split(",")[9];
            	/*scoreStr=temp.substring(temp.indexOf(":")+1,temp.indexOf("."));
            	System.out.println(scoreStr);*/
            	
            	//��ȡuser_info
            	String user_info=result.split("user_info\":\"")[1].split("\"")[0];
            	
            	 scoreStr=temp.substring(temp.indexOf(":")+1,temp.indexOf("."));
            	//AI�������������ص�User_info �������ݿ��û���ȥƥ��
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