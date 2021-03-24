package zytb.rest.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.jersey.servlet.ServletContainer;

public class MyServletContainer extends ServletContainer {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Method", "GET, POST"); 
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
		super.service(request, response);
	}

}
