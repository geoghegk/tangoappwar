package com.ibm.ta.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tangopublic")
public class TangoPublicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/***** This Method Is Called By The Servlet Container To Process A 'GET' Request *****/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		/***** Set Response Content Type *****/
		response.setContentType("text/html");

		/***** Print The Response *****/
		PrintWriter out = response.getWriter();
		String title = "Tango Public";
		String docType = "<!DOCTYPE html>\n";
		out.println(docType 
				+ "<html>\n" + "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>" + title + "</title></head>\n" + "<body>");

		out.println("<h2>Tango Public</h2>" + 
				"<div>This is a public area. You are welcome!</div>" +
				"</body>\n</html>");
		out.close();
	}
}