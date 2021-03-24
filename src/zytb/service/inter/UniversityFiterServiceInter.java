package zytb.service.inter;

import java.util.List;

import zytb.bean.LookUpCommon;
import zytb.bean.Params;
import zytb.bean.University_filter;


public interface UniversityFiterServiceInter {

	List<University_filter> getOneUniversity(Params params);
	
	List<University_filter> getTwoUniversity(Params params);
	
	List<LookUpCommon> getUniversity(Params params);
}
