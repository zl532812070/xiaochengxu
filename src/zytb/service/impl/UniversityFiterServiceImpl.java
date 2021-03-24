package zytb.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import zytb.bean.LookUpCommon;
import zytb.bean.Params;
import zytb.bean.University_filter;
import zytb.service.inter.UniversityFiterServiceInter;
import zytb.util.Util;

@Service("universityFiterService")
public class UniversityFiterServiceImpl implements UniversityFiterServiceInter {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public List<University_filter> getOneUniversity(Params params) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("subject", params.getSubject());
		map.put("address", Arrays.asList(params.getLocations()));
		map.put("major", Arrays.asList(params.getCategorys()));
		return sqlSessionTemplate.selectList("getOneUniversity", map);
	}

	public List<University_filter> getTwoUniversity(Params params) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("subject", params.getSubject());
		return sqlSessionTemplate.selectList("getTwoUniversity", map);
	}

	public List<LookUpCommon> getUniversity(Params params) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("subject", params.getSubject());
		map.put("address", Arrays.asList(params.getLocations().split(",")));
		List<String> categorys = Arrays.asList(params.getCategorys().split(","));
		map.put("category", categorys);
		map.put("differenceFrom",params.getDifference()-50);
		map.put("differenceTo",params.getDifference()+10);
		map.put("flag", params.getFlag());
//		System.out.println("subject:"+params.getSubject());
//		System.out.println("address:"+params.getLocations());
//		System.out.println("category:"+categorys);
//		System.out.println("differenceFrom:"+(params.getDifference()-50));
//		System.out.println("differenceTo:"+(params.getDifference()+10));
//		System.out.println("flag:"+params.getFlag());
		return sqlSessionTemplate.selectList("getUniversity", map);
	}

}
