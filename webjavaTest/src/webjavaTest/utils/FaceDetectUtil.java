package webjavaTest.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class FaceDetectUtil {
	
	
	/**
	 * ��÷���������Ƿ��������
	 * @param json
	 * @return  true-��⵽������false-δ��⵽����
	 */
	public static boolean checkFace(String json){	
		JSONObject dataJson= new JSONObject(json); 
        String code = (String) dataJson.get("error_code").toString(); 
		 return  code.equals("0")?true:false;
	}
	
	
	/**
	 * ��÷�������е��Ա�
	 * @param json
	 * @return
	 */
	public static String getInfo(String json,String p,String key){	
        JSONArray face_lists = getFaceList(json);//  ��JSONArray�����ȡface_list��������
        String value = "";
		 for(int i=0;i<face_lists.length();i++){//ѭ�����飬ȡ����Ҫ������
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
	 * ����������ʶ��Ϣ
	 * @param json
	 * @return
	 */
	public static JSONArray getFaceList(String json){	
		JSONObject dataJson= new JSONObject(json);//���ַ���ת��json����
		json = (String) dataJson.get("result").toString();//��ȡ��json������result�������ڵ�����
        JSONObject face_list = new JSONObject(json);//��result�ڵ��ַ���ת��json���ݣ������Ҫ��ȡface_list�����������
        JSONArray face_lists = face_list.getJSONArray("face_list");//  ��JSONArray�����ȡface_list��������
            return face_lists;
	}
}

