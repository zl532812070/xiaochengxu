package zytb.bean;

import javax.ws.rs.FormParam;

public class PaymentPo {
	private String appid = "";// 小程序ID
	private String mch_id = "";// 商户号
	private String device_info;// 设备号
	private String nonce_str;// 随机字符串
	private String sign;// 签名
	@FormParam("body")
	private String body;// 商品描述
	@FormParam("detail")
	private String detail;// 商品详情
	private String attach;// 附加数据
	private String out_trade_no;// 商户订单号
	private String fee_type;// 货币类型
	private String spbill_create_ip;// 终端IP
	private String time_start;// 交易起始时间
	private String time_expire;// 交易结束时间
	private String goods_tag;// 商品标记
	private String total_fee = "1";// 总金额(分)
	private String notify_url = "https://92994055.qcloud.la/zytb/rest/pay/payCallBack";// 通知地址
	private String trade_type = "JSAPI";// 交易类型
	private String limit_pay;// 指定支付方式
	@FormParam("openid")
	private String openid;// 用户标识
	@FormParam("code")
	private String code;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mchId) {
		mch_id = mchId;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String feeType) {
		fee_type = feeType;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbillCreateIp) {
		spbill_create_ip = spbillCreateIp;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String timeStart) {
		time_start = timeStart;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String timeExpire) {
		time_expire = timeExpire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goodsTag) {
		goods_tag = goodsTag;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String totalFee) {
		total_fee = totalFee;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limitPay) {
		limit_pay = limitPay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
