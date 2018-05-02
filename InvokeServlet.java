package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jcraft.jsch.JSchException;
import utils.JschUtils;

/**
* @author 作者
* @version 创建时间：2018年4月3日下午5:32:59
* 类说明 初始化界面
*/
public class InvokeServlet extends HttpServlet {

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		String A = request.getParameter("usr1");
        String B = request.getParameter("usr2");
        String money = request.getParameter("num1");
        String thing = request.getParameter("num2");
        String time = request.getParameter("time");
        String account = thing + " " + time + " " + money;
        JschUtils ju = new JschUtils();
        System.out.println("-------------------------");
        try {
			ju.exec("docker exec cli /bin/bash ./get.sh '{\"Args\":[\"invoke\",\"" + A + "\",\"" + B + "\",\"" + account + "\"]}'");
		} catch (JSchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
        String str = "";
		File file = new File("exec.txt");
		FileInputStream fin = null;
		int size = 0;
		byte[] bs = null;
		try {
			fin = new FileInputStream(file);
			size = fin.available();
			bs = new byte[size];
			fin.read(bs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fin != null)
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		str = new String(bs);
		System.out.println(str);
		
			//response.sendRedirect("/Right1.0/jsp/initSuccess.jsp");
//        if(isOk){
//            response.sendRedirect("/Right1.0/jsp/4.jsp");
//        }else {
//            response.sendRedirect("/Right1.0/jsp/6.jsp");
//        }
    }

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
		
	}

	
	public void init() throws ServletException {
		
	}

}
