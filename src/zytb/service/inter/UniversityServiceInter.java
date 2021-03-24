package zytb.service.inter;

import java.util.List;

import zytb.bean.University;


public interface UniversityServiceInter {
	
	List<University> getUniversityInfo(List<String> names);
}
