package zytb.util;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zytb.bean.LookUpCommon;

public class Util {

	private static String[] nations;
	private static String[] locations;
	private static Integer one_score_li;
	private static Integer one_score_wen;
	private static Integer two_score_li;
	private static Integer two_score_wen;

	// static {
	// nations = new String[] { "汉族", "满族", "蒙古族", "回族", "维吾尔族", "藏族", "壮族" };
	// locations = new String[] { "全部", "北京", "天津", "上海", "重庆", "河北",
	// "山西", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西",
	// "山东", "河南", "湖北", "湖南", "广东", "海南", "四川", "贵州", "云南",
	// "陕西", "甘肃", "青海", "台湾", "内蒙古自治区", "广西壮族自治区", "西藏自治区",
	// "宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区" };
	// }

	// public static String createRandomList(LookUpCommon common, int n) {
	// // TODO Auto-generated method stub
	// String result = "";
	// Map map = new HashMap();
	// String[] professionArray = common.getProfessions().split(",");
	// int length = professionArray.length;
	// common.setProfessionsSize(length);
	// if(length<=n){
	// for(int i = 0;i<professionArray.length;i++){
	// //System.out.println(result);
	// result += (i+1)+"."+professionArray[i]+" ";
	// // if(i == length-1){
	// // result += (i+1)+"."+professionArray[i];
	// // }else{
	// // result += (i+1)+"."+professionArray[i]+",";
	// // }
	// }
	// }else{
	// while(map.size()<n){
	// int random = (int) (Math.random() * length);
	// if (!map.containsKey(random)) {
	// map.put(random, "");
	// int size = map.size();
	// result += size+"."+professionArray[random]+" ";
	// // if(size == n){
	// // result += size+"."+professionArray[random];
	// // }else{
	// // result += size+"."+professionArray[random]+",";
	// // }
	// }
	// }
	// }
	// //System.out.println(result);
	// return result;
	// }

	public static void createRandomList(LookUpCommon common, int n) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		List<String> professionListResult = new ArrayList<String>();
		List<String> professionRemarkListResult = new ArrayList<String>();
		// List<String> majorProfessionListResult = new ArrayList<String>();
		List<String> professionList = common.getProfessionList();
		List<String> professionRemarkList = common.getProfessionRemarkList();
//		System.out.println("professionList的长度："+professionList.size());
//		System.out.println("professionRemarkList的长度："+professionRemarkList.size());
		// List<String> majorProfessionList = common.getMajorProfessionList();
		int length = professionList.size();
		common.setProfessionsSize(length);
		if (length <= n) {
			for (int i = 0; i < length; i++) {
				// System.out.println(result);
				professionListResult.add(professionList.get(i));
				professionRemarkListResult.add(professionRemarkList.get(i));
				// majorProfessionListResult.add(majorProfessionList.get(i));
				// if(i == length-1){
				// result += (i+1)+"."+professionArray[i];
				// }else{
				// result += (i+1)+"."+professionArray[i]+",";
				// }
			}
		} else {
			while (map.size() < n) {
				int random = (int) (Math.random() * length);
				if (!map.containsKey(random)) {
					map.put(random, "");
					int size = map.size();
					professionListResult.add(professionList.get(random));
					professionRemarkListResult.add(professionRemarkList
							.get(random));
					// majorProfessionListResult.add(majorProfessionList
					// .get(random));
					// if(size == n){
					// result += size+"."+professionArray[random];
					// }else{
					// result += size+"."+professionArray[random]+",";
					// }
				}
			}
		}
		common.setProfessionList(professionListResult);
		common.setProfessionRemarkList(professionRemarkListResult);
		//common.setMajorProfessionList(majorProfessionListResult);
		// System.out.println(result);
	}

	public static String[] getNations() {
		return nations;
	}

	public static String[] getLocations() {
		return locations;
	}

	public static void setScore(Integer one_li, Integer one_wen,
			Integer two_li, Integer two_wen) {
		one_score_li = one_li;
		one_score_wen = one_wen;
		two_score_li = two_li;
		two_score_wen = two_wen;
	}

	public static Integer getOne_score_li() {
		return one_score_li;
	}

	public static Integer getOne_score_wen() {
		return one_score_wen;
	}

	public static Integer getTwo_score_li() {
		return two_score_li;
	}

	public static Integer getTwo_score_wen() {
		return two_score_wen;
	}

	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();// MD5

			char str[] = new char[16 * 2];

			int k = 0;
			for (int i = 0; i < 16; i++) {

				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];

				str[k++] = hexDigits[byte0 & 0xf];

			}
			s = new String(str);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

}
