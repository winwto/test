package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcraft.jsch.JSchException;

import utils.JschUtils;

/**
* @author 作者
* @version 创建时间：2018年4月3日下午9:05:32
* 类说明
*/
public class QueryServlet extends HttpServlet {

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String A = request.getParameter("usr1");
		String B = request.getParameter("usr2");
        JschUtils ju = new JschUtils();
        System.out.println("-------------------------");
        try {
			ju.exec("docker exec cli /bin/bash ./get.sh '{\"Args\":[\"query\",\"" + A + "\",\"" + B + "\"]}'");
		} catch (JSchException e1) {
			e1.printStackTrace();
		}
		System.out.println("xxxxxxxxxxxxxxx");
		String str = "";
		File file = new File("exec.txt");
		FileInputStream fin = new FileInputStream(file);
		int size = fin.available();
		byte[] bs = new byte[size];
		fin.read(bs);
		fin.close();
		str = new String(bs);
		System.out.println("-------------------------");
		
	
		//request.getRequestDispatcher("/jsp/paperInfo.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
