package zytb.rest.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;

import zytb.bean.PayResult;
import zytb.bean.PaymentPo;
import zytb.util.HttpClient;
import zytb.util.MessageUtil;
import zytb.util.PayUtil;
import zytb.util.UUIDHexGenerator;
import zytb.util.WriteText;

@Controller
@Path("/pay")
public class PayRestImpl {

	@Context
	private HttpServletRequest request;

	@POST
	@Path("/execute")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/json;charset=utf-8")
	public PayResult execute(@BeanParam PaymentPo paymentPo) {
		String today = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());

		String code = PayUtil.createCode(8);
		paymentPo.setAppid(PayUtil.APPID_X);
		paymentPo.setMch_id(PayUtil.MCH_ID);
		paymentPo.setNonce_str(UUIDHexGenerator.generate());
		paymentPo.setSpbill_create_ip(getRemortIP(request));
		paymentPo.setOut_trade_no(paymentPo.getMch_id() + today + code);
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("appid", PayUtil.APPID_X);
		sParaTemp.put("mch_id", PayUtil.MCH_ID);
		sParaTemp.put("nonce_str", paymentPo.getNonce_str());
		sParaTemp.put("body", paymentPo.getBody());
		sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
		sParaTemp.put("total_fee", paymentPo.getTotal_fee());
		sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
		sParaTemp.put("notify_url", paymentPo.getNotify_url());
		sParaTemp.put("trade_type", paymentPo.getTrade_type());
		sParaTemp.put("openid", paymentPo.getOpenid());

		// 除去数组中的空值和签名参数
		Map<String, String> sPara = PayUtil.paraFilter(sParaTemp);
		String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String key = "&key=" + PayUtil.API_KEY; // 商户支付密钥
		// MD5运算生成签名
		// System.out.println(prestr);
		// WriteText.print(prestr);
		String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
		paymentPo.setSign(mysign);
		//WriteText.print(paymentPo.getOpenid());
		// 打包要发送的xml
		String respXml = MessageUtil.messageToXML(paymentPo);
		// 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
		respXml = respXml.replace("__", "_");
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 统一下单API接口链接
		// System.out.println("respXml:" + respXml);
		// WriteText.print("respXml:"+respXml);
		String param = respXml;
		String result = PayUtil.httpRequest(url, "POST", param);
		// 将解析结果存储在HashMap中
		// System.out.println("result:" + result);
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = new ByteArrayInputStream(result.getBytes());
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document;
		PayResult payResult = new PayResult();
		try {
			document = reader.read(in);
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			@SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
			for (Element element : elementList) {
				map.put(element.getName(), element.getText());
			}
			// 返回信息
			String return_code = map.get("return_code");// 返回状态码
			String return_msg = map.get("return_msg");// 返回信息
			if (return_code == "SUCCESS" || return_code.equals(return_code)) {
				// 业务结果
				String prepay_id = map.get("prepay_id");// 返回的预付单信息
				String nonceStr = UUIDHexGenerator.generate();
				payResult.setNonceStr(nonceStr);
				payResult.setPackage_("prepay_id=" + prepay_id);
				Long timeStamp = System.currentTimeMillis() / 1000;
				payResult.setTimeStamp(timeStamp + "");
				String stringSignTemp = "appId=" + paymentPo.getAppid()
						+ "&nonceStr=" + nonceStr + "&package=prepay_id="
						+ prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
				// 再次签名
				String paySign = PayUtil.sign(stringSignTemp, key, "utf-8")
						.toUpperCase();
				payResult.setPaySign(paySign);
			}
			return payResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return payResult;
		}
	}
	
	@POST
	@Path("/h5Pay")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/json;charset=utf-8")
	public PayResult h5Pay(@BeanParam PaymentPo paymentPo) {
		String today = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());

		String code = PayUtil.createCode(8);
		paymentPo.setAppid(PayUtil.APPID_H);
		paymentPo.setMch_id(PayUtil.MCH_ID);
		paymentPo.setNonce_str(UUIDHexGenerator.generate());
		paymentPo.setSpbill_create_ip(getRemortIP(request));
		paymentPo.setOut_trade_no(paymentPo.getMch_id() + today + code);
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("appid", PayUtil.APPID_H);
		sParaTemp.put("mch_id", PayUtil.MCH_ID);
		sParaTemp.put("nonce_str", paymentPo.getNonce_str());
		sParaTemp.put("body", paymentPo.getBody());
		sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
		sParaTemp.put("total_fee", paymentPo.getTotal_fee());
		sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
		sParaTemp.put("notify_url", paymentPo.getNotify_url());
		sParaTemp.put("trade_type", paymentPo.getTrade_type());
		sParaTemp.put("openid", paymentPo.getOpenid());

		// 除去数组中的空值和签名参数
		Map<String, String> sPara = PayUtil.paraFilter(sParaTemp);
		String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String key = "&key=" + PayUtil.API_KEY; // 商户支付密钥
		// MD5运算生成签名
		//System.out.println(prestr);
		// WriteText.print(prestr);
		String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
		paymentPo.setSign(mysign);
		//WriteText.print(paymentPo.getOpenid());
		// 打包要发送的xml
		String respXml = MessageUtil.messageToXML(paymentPo);
		// 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
		respXml = respXml.replace("__", "_");
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 统一下单API接口链接
		//System.out.println("respXml:" + respXml);
		// WriteText.print("respXml:"+respXml);
		String param = respXml;
		String result = PayUtil.httpRequest(url, "POST", param);
		// 将解析结果存储在HashMap中
		//System.out.println("result:" + result);
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = new ByteArrayInputStream(result.getBytes());
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document;
		PayResult payResult = new PayResult();
		try {
			document = reader.read(in);
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			@SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
			for (Element element : elementList) {
				map.put(element.getName(), element.getText());
			}
			// 返回信息
			String return_code = map.get("return_code");// 返回状态码
			String return_msg = map.get("return_msg");// 返回信息
			if (return_code == "SUCCESS" || return_code.equals(return_code)) {
				// 业务结果
				String prepay_id = map.get("prepay_id");// 返回的预付单信息
				String nonceStr = UUIDHexGenerator.generate();
				payResult.setNonceStr(nonceStr);
				payResult.setPackage_("prepay_id=" + prepay_id);
				Long timeStamp = System.currentTimeMillis() / 1000;
				payResult.setTimeStamp(timeStamp + "");
				String stringSignTemp = "appId=" + paymentPo.getAppid()
						+ "&nonceStr=" + nonceStr + "&package=prepay_id="
						+ prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
				// 再次签名
				String paySign = PayUtil.sign(stringSignTemp, key, "utf-8")
						.toUpperCase();
				payResult.setPaySign(paySign);
			}
			return payResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return payResult;
		}
	}

	@GET
	@Path("payCallBack")
	@Produces("text/json;charset=utf-8")
	public void payCallBack() {
		WriteText.print("payCallBack");
	}

	@GET
	@Path("openid")
	@Produces("text/json;charset=utf-8")
	public String getOpenId(@QueryParam("code") String code) {
		return HttpClient
				.sendGet("https://api.weixin.qq.com/sns/jscode2session",
						"appid=" + PayUtil.APPID_X + "&secret="
								+ PayUtil.APP_SECRECT_X + "&js_code=" + code
								+ "&grant_type=authorization_code");
	}

	@GET
	@Path("h5openid")
	@Produces("text/json;charset=utf-8")
	public String getH5OpenId(@QueryParam("code") String code) {
		return HttpClient.sendGet(
				"https://api.weixin.qq.com/sns/oauth2/access_token", "appid="
						+ PayUtil.APPID_H + "&secret=" + PayUtil.APP_SECRECT_H
						+ "&code=" + code + "&grant_type=authorization_code");
	}

	public String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			// WriteText.print("1:"+request.getRemoteAddr());
			return request.getRemoteAddr();
		}
		// WriteText.print("2:"+request.getHeader("x-forwarded-for"));
		return request.getHeader("x-forwarded-for").split(",")[0];
	}
}
