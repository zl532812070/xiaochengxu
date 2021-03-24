var dialog = new auiDialog();
var toast = new auiToast();
var categories_name = ["理学类","工业类","农业类","医学类","教育学类","文学类","历史学类","法学类","政治学类","经济学类","管理学类","哲学类"];
var categories_value = ["理学","工学","农学","医学","教育学","文学","历史学","法学","政治学","经济学","管理学","哲学"];
initView(categories_name);

function initView(categories_name){
	var subject = localStorage.subject;
	var locationArray = localStorage.locationArray == null?[]:localStorage.locationArray.split(",");
	var categoryArray = localStorage.categoryArray == null?[]:localStorage.categoryArray.split(",");
	if(subject == "理科"){
		$("#subject_li").attr("checked","checked");
	}else if(subject == "文科"){
		$("#subject_wen").attr("checked","checked");
	}
	if(localStorage.sorce != ""){
		$("#score").val(localStorage.score);
	}
	
	if(locationArray.length >=3 ){
		$("#location_warn").text("已选择完成");
	}else{
		$("#location_warn").text("至少选3项");
	}
	for(var i in categories_name){
		var checked = $.inArray(categories_value[i],categoryArray)==-1?"":"checked";
		$("#category").append("<li class='aui-list-item' name='category'>"+
									"<div class='aui-list-item-inner'>"+
                    					"<label><input class='aui-checkbox' type='checkbox' "+checked+" name='category' value='"+categories_value[i]+"'>　"+categories_name[i]+"</label>"+
                					"</div>"+
            					"</li>");
	}
	$("#payButtonInfo").text("查阅详细信息");
	/*if(localStorage.isPay == "true"){
		$("#payButtonInfo").text("查阅详细版志愿表");
	}else{
		$("#payButtonInfo").html("￥2.99(<span class='cancel'>原价￥10</span>)查阅详细版志愿表");
	}*/
	var tab = new auiTab( {
		element : document.getElementById("footer")
	}, function(ret) {
		if (ret) {
			var mainObj = $($api.byId("main"));
			var contactObj = $($api.byId("contact"));
			var index = ret.index;
			if(index == 1){
				mainObj.css("display","block");
				contactObj.css("display","none");
			}else{
				mainObj.css("display","none");
				contactObj.css("display","block");
			}
		}
	});
	$("li[name='category']").css("width",$("#content").width());
	$("#content").css("height",$(window).height() - $("#footer").height());
	var one_universities = localStorage.one_universities == null?null:JSON.parse(localStorage.one_universities);
	var two_universities = localStorage.two_universities == null?null:JSON.parse(localStorage.two_universities);
	setUniversities(one_universities,two_universities);
	if(sessionStorage.getItem("openid") == null){
		getOpenId();
	}
}

function getOpenId(){
	var code = GetQueryString("code");
	$.ajax({
	   url: basePath+"/zytb/rest/pay/h5openid?code="+code,
	   dataType:"json",
	   type:"GET",
	   success: function(res){
		  // alert("openId:"+res.openid);
		  sessionStorage.setItem("openid",res.openid);
	   }
	});
}

function subject_verify(){
	var length = $("input[name='subject']:checked").size();
	return length;
}

function score_verify(){
	if(localStorage.score != ""){
		var score = parseInt(localStorage.score);
		if(score<=0||score>=750){
	        return false;
		}
		return true;
	}
	return false;
}

function location_verify(){
	var locationArray = localStorage.locationArray == null?[]:localStorage.locationArray.split(",");
	if(locationArray.length<3){
		return false;
	}
	return true;
}

function category_verify(){
	var categoryArray = localStorage.categoryArray == null?[]:localStorage.categoryArray.split(",");
	if(categoryArray.length < 3){ 
		return false;
	}
	return true;
}

function formSubmit(){
	setLocalStorage();
	if(subject_verify()<=0){
		dialog.alert({
            title:"提示",
            msg:"请选择科目",
            buttons:['确定']
        });
        return;
	}
	if(!score_verify()){
		dialog.alert({
            title:"提示",
            msg:"高考分数输入有误,请重新输入",
            buttons:['确定']
        });
        return;
	}
	
	if(!location_verify()){
		dialog.alert({
            title:"提示",
            msg:"至少选3项院校所在地",
            buttons:['确定']
        });
		return;
	}
	
	if(!category_verify()){
		dialog.alert({
            title:"提示",
            msg:"专业方向至少选3项",
            buttons:['确定']
        });
        return;
	}
	
	var subject = $("input[name='subject']:checked").val();
	var score = $("#score").val();
	var categorys = "";
	var locations = "";
	
	var locationArray = localStorage.locationArray.split(",");
	var categoryArray = localStorage.categoryArray.split(",");
	for(var i in locationArray){
		if(i == length-1){
			locations += locationArray[i];
		}else{
			locations += locationArray[i] + ",";
		}
	}
	
	for(var i in categoryArray){
		if(i == length-1){
			categorys += categoryArray[i];
		}else{
			categorys += categoryArray[i] + ",";
		}
	}
	$.ajax({
	   url: basePath+"/zytb/rest/universityFiter/execute",
	   dataType:"json",
	   type:"POST",
	   data:{
	   		'subject':subject,
	   		'locations':locations,
	   		'score':score,
	   		'categorys':categorys
	   		
	   },
	   success: function(json){
		  var one_universities = json.one_university;
		  var two_universities = json.two_university;
		  if(one_universities == null && two_universities == null){
			  toast.success({
	              title:"没有相应学校",
	              duration:2000
	         });
		  }else{
			  toast.success({
	              title:"查询成功",
	              duration:2000
	         });
		  }
		  localStorage.one_universities = JSON.stringify(one_universities);
		  localStorage.two_universities = JSON.stringify(two_universities);
		  setUniversities(one_universities,two_universities);
		  localStorage.isPay = false;
		  //$("#payButtonInfo").html("￥2.99(<span class='cancel'>原价￥10</span>)查阅详细版志愿表");
		  $("#payButtonInfo").text("查阅详细信息");
	   }
	 });
}

function setUniversities(one_universities,two_universities){
	var html = "";
	  var university = "";
	  var professionStr = "";
	  var professionLists = 0;
	  if(one_universities == null && two_universities == null){
		  $("#one_universities").css("display","none");
		  $("#two_universities").css("display","none");
		  $("#payButton").css("display","none");
	  }else if(one_universities.length > 0){
		  for(var i in one_universities){
			  university = one_universities[i];
			  professionList = university.professionList;
			  if(professionList.length > 0){
				  for(var j in professionList){
					  professionStr += (parseInt(j)+1) + "."+professionList[j] +" "
				  }
			  }
			  html += "<li name='one_universities' class='aui-list-item aui-list-item-arrow'>"+
				                "<div class='aui-media-list-item-inner'>"+
				                    "<div class='aui-list-item-inner'>"+
				                        "<div class='aui-list-item-text'>"+
				                            "<div class='aui-list-item-title'>"+university.name+"</div>"+
				                        "</div>"+
				                        "<div class='aui-list-item-text aui-ellipsis-2'>"+professionStr+"</div>"+
				                    "</div>"+
				                "</div>"+
				            "</li>";
			  professionStr = "";
		  }
		  $("li[name='one_universities']").remove();
		  $("#one_universities").append(html);
		  $("#one_universities").css("display","block");
		  $("#two_universities").css("display","none");
		  $("#payButton").css("display","block");
	  }else {
		  for(var i in two_universities){
			  university = two_universities[i];
			  professionList = university.professionList;
			  if(professionList.length > 0){
				  for(var j in professionList){
					  professionStr += (parseInt(j)+1) + "."+professionList[j] +" "
				  }
			  }
			  html += "<li name='two_universities' class='aui-list-item aui-list-item-arrow'>"+
				                "<div class='aui-media-list-item-inner'>"+
				                    "<div class='aui-list-item-inner'>"+
				                        "<div class='aui-list-item-text'>"+
				                            "<div class='aui-list-item-title'>"+university.name+"</div>"+
				                        "</div>"+
				                        "<div class='aui-list-item-text aui-ellipsis-2'>"+professionStr+"</div>"+
				                    "</div>"+
				                "</div>"+
				            "</li>";
			  professionStr = "";
		  }
		  $("li[name='two_universities']").remove();
		  $("#two_universities").append(html);
		  $("#one_universities").css("display","none");
		  $("#two_universities").css("display","block");
		  $("#payButton").css("display","block");
	  }
}


function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

function call(){
	window.location.href = "tel:18722583253";
}

function addUser(){
	dialog.prompt({
        title:"用户预约",
        texts:["姓名","联系方式"],
        buttons:['取消','确定']
    },function(ret){
        if(ret.buttonIndex == 2){
        	var userName = ret.userName.trim();
        	var tel = ret.tel.trim();
        	if(userName != "" && tel != ""){
        		if(checkTel(tel)){
        			$.ajax({//往yc_userInfo项目中添加
              		   url: basePath+"/zytb/rest/user/user",
              		   dataType:"json",
              		   type:"POST",
              		   data:{
              		   		'userName':ret.userName,
              		   		'tel':ret.tel,
              		   },
              		   success: function(json){
              			   if(json){
              				   toast.success({
          			              title:"预约成功",
          			              duration:2000
              			       });
              			   }
              		   }
              		 });
        			$.ajax({//往heart_evaluation项目中添加
               		   url: basePath+"/heart_evaluation/customer/addCustomer",
               		   dataType:"json",
               		   type:"POST",
               		   data:{
               		   		'customer.userName':ret.userName,
               		   		'customer.tel':ret.tel,
               		   }
               		 });
        		}else{
        			addUser();
        		}
        	}else{
        		addUser();
        	}
        }
    })
}

function checkTel(val){
	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	if(isMob.test(val)||isPhone.test(val)){
		return true;
	}
	else{
		return false;
	}
}

function openLocationUI(){
	setLocalStorage();
	window.location.href = "location.html";
}

function setLocalStorage(){
	var subject = $("input[name='subject']:checked").val();
	var score = $("#score").val();
	var categoryArray = $("input[name='category']:checked").toArray();
	for(var i in categoryArray){
		categoryArray[i] = $(categoryArray[i]).val();
	}
	localStorage.subject = subject;
	localStorage.score = score;
	localStorage.categoryArray = categoryArray;
}

function pay(){
	window.location.href = "detail.html";
	/*if(localStorage.isPay != "true"){
		$.ajax({
		   url: basePath+"/zytb/rest/pay/h5Pay",
		   dataType:"json",
		   type:"POST",
		   data:{
			"body": "详细版支付",
			//"openid": "oUTo2wNgTnE0waSELPkHZPHnZ4i4"
			"openid": sessionStorage.getItem("openid")
		   },
		   success: function(res){
			   if (typeof WeixinJSBridge == "undefined"){
				   if( document.addEventListener ){
				       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
				   }else if (document.attachEvent){
				       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
				       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				   }
				}else{
				   onBridgeReady(res);
				}
		   }
		});
	}else{
		window.location.href = "detail.html";
	}*/
}
function onBridgeReady(param){
   WeixinJSBridge.invoke(
       'getBrandWCPayRequest', {
           "appId":"wx0055482ddc522e94",     // 公众号名称，由商户传入
           "timeStamp":param.timeStamp,         // 时间戳，自1970年以来的秒数
           "nonceStr":param.nonceStr, // 随机串
           "package":param.package_,     
           "signType":"MD5",         // 微信签名方式：
           "paySign":param.paySign // 微信签名
       },
       function(res){     
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
        	   localStorage.isPay = true;
        	   window.location.href = "detail.html";
           }     
           // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回
		   // ok，但并不保证它绝对可靠。
       }
   ); 
}

