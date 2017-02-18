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
	SheetService sheetService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displaySheetDetails(Model model) {

		List<SheetModel> sheetinfo = sheetService.getURLData();
		return new ModelAndView("display", "sheetDetails", sheetinfo);

	}
	
	public ModelAndView updateDatabse()
	{
		List getURLdata =sheetService.getURLData();
		for (Iterator iterator = getURLdata.iterator(); iterator.hasNext();) {
			String url = (String) iterator.next();
			Map<String, String> map = new HashMap<String, String>();
			int i=1;
			map.put("URL", url);
			FireBaseDao.saveDataToFireBase(map);
			
		}
		return null;
	}
}
