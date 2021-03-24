show();
function show(){
	var names = "";
	var one_universities = localStorage.one_universities == null?"":JSON.parse(localStorage.one_universities);
	var two_universities = localStorage.two_universities == null?"":JSON.parse(localStorage.two_universities);
	console.log(one_universities);
	console.log(two_universities);
	if(one_universities != ""){
		for(var i in one_universities){
			names += one_universities[i].name + ",";
		}
	}else{
		for(var i in two_universities){
			names += two_universities[i].name + ",";
		}
	}
	$.ajax({
	   url: basePath+"/zytb/rest/university/universityInfo/"+names,
	   dataType:"json",
	   type:"GET",
	   success: function(json){
		if(one_universities != ""){
			var university = null;
			var professionList = null;
			//var majorProfessionList = null;
			var professionRemarkList = null;
			var html = "";
			var professionHtml = "";
			var majorProfessionHtml = "";
			var universityFlag = "";
			var universityDetail = null;
			var universityDetailHtml = "";
			for(var i in one_universities){
				university = one_universities[i];
				/*if(university.remark != ""){
					universityFlag += "<div class='aui-label aui-label-danger'>"+university.remark+"</div>";
				}*/
				if(university.major != ""){
					universityFlag += "<div class='aui-label aui-label-danger'>"+university.major+"</div>";
				}
				professionList = university.professionList;
				professionRemarkList = university.professionRemarkList;
				//majorProfessionList = university.majorProfessionList;
				if(professionList.length > 0){
					for(var j in professionList){
						professionHtml += (parseInt(j)+1)+"."+professionList[j] + "<br>";
						/*if(majorProfessionList[j] != " "){
							professionHtml += "&nbsp;&nbsp;<div class='aui-label aui-label-warning'>"+majorProfessionList[j]+"</div>";
						}*/
						//professionHtml += "<br>";
						if(professionRemarkList[j].trim() != ''){
							professionHtml += "<font color='red'>备注:"+professionRemarkList[j]+"</font><br>";
						}
					}
				}
				universityDetail = json[university.name];
				if(universityDetail != null){
					universityDetailHtml = "当地批次:&nbsp;"+universityDetail.local_batch+"<br>"+
											"建校时间:&nbsp;"+universityDetail.create_university+"<br>"+
											"属性:&nbsp;"+universityDetail.attribute+"<br>"+
											"主管部门:&nbsp;"+universityDetail.competent_department+"<br>"+
											"硕士点数:&nbsp;"+universityDetail.master_points+"<br>"+
											"博士点数:&nbsp;"+universityDetail.doctoral_points+"<br>"+
											"专业优势:&nbsp;"+universityDetail.advantage_specialty+"<br>"+
											"占地面积:&nbsp;"+universityDetail.area+"<br>"+
											"学校地址:&nbsp;"+universityDetail.address+"<br>"+
											"学校到火车站:&nbsp;"+universityDetail.toTrain+"<br>"+
											//"郊区校区信息:&nbsp;"+universityDetail.suburb+"<br>"+
											"到天津交通情况:&nbsp;"+universityDetail.toTianJin+"<br>"+
											"特色:&nbsp;"+universityDetail.characteristic+"<br>";
				}
				html += "<div class='aui-card-list'>"+
			            "<div class='aui-card-list-header2'>"+
			            	university.name +
			            	"&nbsp;&nbsp;" +
			            	universityFlag +
			            "</div>"+
			            "<div class='aui-card-list-content-padded'>"+
			            	professionHtml +
			            "</div>"+
			            "<div class='aui-card-list-footer'>"+
			            	universityDetailHtml+
			            "</div>"+
			        "</div>";
				professionHtml = "";
				universityFlag = "";
			}
			$("#content").append(html);
		}else{
			var university = null;
			var professionList = null;
			//var majorProfessionList = null;
			var professionRemarkList = null;
			var html = "";
			var professionHtml = "";
			var majorProfessionHtml = "";
			var universityFlag = "";
			var universityDetail = null;
			var universityDetailHtml = "";
			for(var i in two_universities){
				university = two_universities[i];
				/*if(university.remark != ""){
					universityFlag += "<div class='aui-label aui-label-danger'>"+university.remark+"</div>&nbsp;";
				}*/
				if(university.major != ""){
					universityFlag += "<div class='aui-label aui-label-danger'>"+university.major+"</div>&nbsp;";
				}
				professionList = university.professionList;
				professionRemarkList = university.professionRemarkList;
				//majorProfessionList = university.majorProfessionList;
				if(professionList.length > 0){
					for(var j in professionList){
						professionHtml += (parseInt(j)+1)+"."+professionList[j] + "<br>";
						/*if(majorProfessionList[j] != " "){
							professionHtml += "&nbsp;&nbsp;<div class='aui-label aui-label-warning'>"+majorProfessionList[j]+"</div>";
						}*/
						//professionHtml += "<br>";
						if(professionRemarkList[j].trim() != ''){
							professionHtml += "<font color='red'>备注:"+professionRemarkList[j]+"</font><br>";
						}
					}
				}
				universityDetail = json[university.name];
				if(universityDetail != null){
					universityDetailHtml = "当地批次:&nbsp;"+universityDetail.local_batch+"<br>"+
											"建校时间:&nbsp;"+universityDetail.create_university+"<br>"+
											"属性:&nbsp;"+universityDetail.attribute+"<br>"+
											"主管部门:&nbsp;"+universityDetail.competent_department+"<br>"+
											"硕士点数:&nbsp;"+universityDetail.master_points+"<br>"+
											"博士点数:&nbsp;"+universityDetail.doctoral_points+"<br>"+
											"专业优势:&nbsp;"+universityDetail.advantage_specialty+"<br>"+
											"占地面积:&nbsp;"+universityDetail.area+"<br>"+
											"学校地址:&nbsp;"+universityDetail.address+"<br>"+
											"学校到火车站:&nbsp;"+universityDetail.toTrain+"<br>"+
											//"郊区校区信息:&nbsp;"+universityDetail.suburb+"<br>"+
											"到天津交通情况:&nbsp;"+universityDetail.toTianJin+"<br>"+
											"特色:&nbsp;"+universityDetail.characteristic+"<br>";
				}
				html += "<div class='aui-card-list'>"+
			            "<div class='aui-card-list-header2'>"+
			            	university.name +
			            	"&nbsp;&nbsp;" +
			            	universityFlag +
			            "</div>"+
			            "<div class='aui-card-list-content-padded'>"+
			            	professionHtml +
			            "</div>"+
			            "<div class='aui-card-list-footer'>"+
			            	universityDetailHtml+
			            "</div>"+
			        "</div>";
				professionHtml = "";
				universityFlag = "";
			}
			$("#content").append(html);
		}
	   }
	});
}