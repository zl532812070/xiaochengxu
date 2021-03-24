package zytb.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import zytb.bean.University;
import zytb.service.inter.UniversityServiceInter;

@Service("universityService")
public class UniversityServiceImpl implements UniversityServiceInter {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public List<University> getUniversityInfo(List<String> names) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("getUniversityInfo",names);
	}


}
