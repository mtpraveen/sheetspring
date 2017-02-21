package com.sheet.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sheet.dao.impl.FireBaseDao;
import com.sheet.model.SheetModel;
import com.sheet.services.SheetService;

@Controller
public class SheetControllerRedis {
	@Autowired
	SheetService mSheetService;
	
	String Name,Company,Email,TimeStamp,Image,Hiringcity,CompanyLocation,Domain,EngineerStatus;
	public String name;
	public String profile;
	public String mKey;
	public String company;
	public String email;
	public String hcity;
	public String domain;
	public String clocation;
	public String status;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displaySheetDetails(Model model) {

		List<SheetModel> sheetinfo = mSheetService.getURLData();
		return new ModelAndView("display", "sheetDetails", sheetinfo);

	}
	/*
	 * Storing into firebase database
	 */
	public ModelAndView updateDatabse()
	{
		System.out.println("storing into firebase");
		List<SheetModel> getdata =mSheetService.displayAllData();
		
	//	Map<String, HashMap<String, String>> mapKey = new HashMap<String, HashMap<String, String>>();
			
		
		
		for(SheetModel l:getdata)
		{	
			Map<String,String> map = new HashMap<String, String>();
			System.out.println("---------------");
				System.out.println(l.getName());
				name=l.getName();
				company=l.getCompany();
				email=l.getEmail();
				hcity=l.getHiringCity();
				domain=l.getDomain();
				clocation=l.getCompanyLocation();
				status=l.getEnggStatus();
				profile=l.getProfilePicture();
				map.put("Company",company);
				map.put("Name",name);
				map.put("Email",email);
				map.put("HirinGcity",hcity);
				map.put("CompanyLocation",clocation);
				map.put("Domain",domain);
				map.put("EngineerStatus",status);
				map.put("Image",profile);
				mKey=l.getTimestamp();
				FireBaseDao.saveDataToFireBase(mKey,map);
			}
		return null;
	}
}
