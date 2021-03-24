package zytb.rest.inter;

import java.util.Map;

import zytb.bean.University;

public interface UniversityRestInter {

	Map<String, University> getUniversityInfo(String names);
}
