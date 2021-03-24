package zytb.rest.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;

import zytb.bean.LookUpCommon;
import zytb.bean.Params;
import zytb.bean.UniversityResult;
import zytb.bean.University_filter;
import zytb.rest.inter.UniversityFiterRestInter;
import zytb.service.inter.UniversityFiterServiceInter;
import zytb.util.Util;

@Controller
@Path("/universityFiter")
public class UniversityFiterRestImpl implements UniversityFiterRestInter {

	@Resource
	private UniversityFiterServiceInter universityFiterService;

	@POST
	@Path("/execute")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/json;charset=utf-8")
	public UniversityResult execute(@BeanParam Params params) {
		// TODO Auto-generated method stub
		List<LookUpCommon> one_university = null;
		List<LookUpCommon> two_university = null;

		UniversityResult result = new UniversityResult();
		double score = params.getScore();
		if ("理科".equals(params.getSubject())) {
			if (score >= Util.getOne_score_li()) {// 大于一本线
				params.setDifference(score-Util.getOne_score_li());
				params.setFlag(1);
			} else if (score >= Util.getTwo_score_li()) {// 大于2本线
				params.setDifference(score-Util.getTwo_score_li());
				params.setFlag(2);
			} else {// 小于2本线
				return result;
			}
		} else {
			if (score >= Util.getOne_score_wen()) {
				params.setDifference(score-Util.getOne_score_wen());
				params.setFlag(1);
			} else if (score >= Util.getTwo_score_wen()) {
				params.setDifference(score-Util.getTwo_score_wen());
				params.setFlag(2);
			} else {
				return result;
			}
		}
		List<LookUpCommon> commons = universityFiterService
				.getUniversity(params);
		if (commons.size() > 0) {
			one_university = new ArrayList<LookUpCommon>();
			two_university = new ArrayList<LookUpCommon>();
			//System.out.println("commonSize:"+commons.size());
			for (LookUpCommon common : commons) {
				Util.createRandomList(common, 6);
				if ("一本".equals(common.getType())) {
					one_university.add(common);
				} else {
					two_university.add(common);
				}
			}
			if(one_university.size()>0){
				Collections.sort(one_university);
			}else if(two_university.size()>0){
				Collections.sort(two_university);
			}
		}
		result.setOne_university(one_university);
		result.setTwo_university(two_university);
		return result;
	}

}
