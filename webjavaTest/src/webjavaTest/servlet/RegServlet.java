package webjavaTest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webjavaTest.service.FaceAdd;
import webjavaTest.service.FaceDetect;
import webjavaTest.service.FaceSearch;
import webjavaTest.utils.FaceDetectUtil;
import webjavaTest.utils.FaceResultUtil;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		
		String name = request.getParameter("userName");
		//String pwd = request.getParameter("userPwd");
		String imageBase64=request.getParameter("imageBase64");//接受图片的base64码值
		String code=null;
		
		//注册前先判断是否在人脸库中存在
		//所有得score值小于80，即不存在，就可以注册
		//若有，弹出提示：已有账号请登录
		String scoreStr=(String) FaceSearch.faceSearch(imageBase64).get("score");
		
		int score = Integer.parseInt(scoreStr);
		
		if(score > 80){
			out.print("1");
		}
		else{
			
			//检测人脸信息
			String detectResult = FaceDetect.faceDetect(imageBase64);
			
			boolean flag=FaceDetectUtil.checkFace(detectResult);
			if(flag){
				String result=FaceAdd.add(imageBase64,name,detectResult);
				
				out.print(FaceResultUtil.getCode(result));//向前台写数据
				
			}
			else{
				out.print(2);
				return;
			}
		}
		
		//doGet(request, response);
	}

}
