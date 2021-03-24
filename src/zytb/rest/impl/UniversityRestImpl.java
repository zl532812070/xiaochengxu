package zytb.rest.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;

import zytb.bean.University;
import zytb.rest.inter.UniversityRestInter;
import zytb.service.inter.UniversityServiceInter;

@Controller
@Path("/university")
public class UniversityRestImpl implements UniversityRestInter {

	@Resource
	private UniversityServiceInter universityService;

	@GET
	@Path("universityInfo/{names}")
	@Produces("text/json;charset=utf-8")
	public Map<String,University> getUniversityInfo(@PathParam("names") String names) {
		// TODO Auto-generated method stub
		HashMap<String, University> map = null;
		List<String> params = new ArrayList<String>();
		for (String name : names.split(",")) {
			params.add(name);
		}
		List<University>  universities = universityService.getUniversityInfo(params);
		if(universities.size()>0){
			map = new HashMap<String, University>();
			for(University university : universities){
				map.put(university.getName(), university);
			}
		}
		return map;
	}


}
