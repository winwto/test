package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcraft.jsch.JSchException;

import utils.JschUtils;

/**
* @author ����
* @version ����ʱ�䣺2018��4��4������11:06:35
* ��˵��
*/
public class CalculaServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String A = request.getParameter("usr1");
		String B = request.getParameter("usr2"); 
		 
		JschUtils ju = new JschUtils();
		try {
			ju.exec("docker exec cli /bin/bash ./get.sh '{\"Args\":[\"calculate\",\"" + A + "\",\"" + B + "\"]}'");
		} catch (JSchException e1) {
			e1.printStackTrace();
		}
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
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
