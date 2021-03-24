package zytb.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;

import zytb.util.SignUtil;
import zytb.util.WriteText;

@Controller
@Path("/verify")
public class VerifyRestImpl {

	@GET
	@Path("callBack")
	@Produces("text/json;charset=utf-8")
	public String callBack(@QueryParam("signature") String signature,
			@QueryParam("timestamp") String timestamp,
			@QueryParam("nonce") String nonce,
			@QueryParam("echostr") String echostr,
			@QueryParam("code") String code, @QueryParam("state") String state) {
		WriteText.print("=====================");
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			 getAccessToken(code,state);
		}
		return null;
	}

	public void getAccessToken(String code, String state) {
		System.out.println(code);
		WriteText.print(code);
	}
}
