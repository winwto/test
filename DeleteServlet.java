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
public class DeleteServlet extends HttpServlet {

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
        A =String2Byte(A);
        B =String2Byte(B);
        JschUtils ju = new JschUtils();
        System.out.println("-------------------------");
        try {
			ju.exec("docker exec cli /bin/bash ./get.sh '{\"Args\":[\"delete\",\"" + A + "\",\"" + B + "\"]}'");
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
	private String  String2Byte(String string) {
		byte[] bs=string.getBytes();
		String byteString =new String();
		for (int i = 0; i < bs.length; i++) {
			byteString=byteString+new Byte(bs[i]).toString()+"+";
		}
		return byteString;
	}
	
	private String Byte2String(String string) {
		String[] strings=string.split("\\+");
		byte[] bs=new byte[strings.length];
		for (int i = 0; i < strings.length; i++) {
			int a=Integer.parseInt(strings[i]);
			bs[i]=(byte)a;
		}
		return new String(bs);
	}

}
