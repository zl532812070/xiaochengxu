package zytb.bean;

import javax.ws.rs.QueryParam;

public class VerifyUrl {

	@QueryParam("signature")
	private String signature;
	@QueryParam("timestamp")
	private String timestamp;
	@QueryParam("nonce")
	private String nonce;
	@QueryParam("nonce")
	private String echostr;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

}
