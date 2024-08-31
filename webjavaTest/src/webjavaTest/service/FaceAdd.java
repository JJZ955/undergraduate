package webjavaTest.service;

/*import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.GsonUtils;*/

import java.util.*;

import webjavaTest.dao.UserDao;
import webjavaTest.utils.FaceDetectUtil;
import webjavaTest.utils.GsonUtils;
import webjavaTest.utils.HttpUtil;

/**
* ����ע��
*/
public class FaceAdd {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String add(String image,String name,String userInfo) {
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("group_id", "group_1");
            map.put("user_id", "admin");
            map.put("user_info", name);
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            
            String message = result.split(",")[0].split(":")[1];
            if(message .equals("0")){
            	
            	String sex = FaceDetectUtil.getInfo(userInfo, "gender","type");//�ӷ�������л���Ա���Ϣ
            	String glass = FaceDetectUtil.getInfo(userInfo, "glasses", "type");
            	System.out.println("sex="+sex);
            	System.out.println("glasses="+glass);
            	//���û������Ա�浽���ݿ�
            	UserDao userdao = new UserDao();
            	userdao.insertUser(name,sex);
            	
            }
            
            
            //System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}