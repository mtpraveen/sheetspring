package com.sheet.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sheet.dao.SheetDao;
import com.sheet.model.SheetModel;
import com.sheet.services.SheetService;

public class SheetServiceImpl implements SheetService {
	
	@Autowired
	private SheetDao sheetDao;
	
	public void addSheetData(SheetModel sheetmodel) {
			sheetDao.addSheetData(sheetmodel);
			
	}
	public List<SheetModel> displayAllData() {
		List<SheetModel> sheetList =sheetDao.displayAllData();
		return sheetList;
	}
	@Override
	public List getTimeStampData() {
		List  timeStampList =sheetDao.getTimeStampData();
		return timeStampList;
	}
	@Override
	public SheetModel timeStampExist(String timeStamp) {
		SheetModel sheetModel=sheetDao.timeStampExist(timeStamp);
		return sheetModel;
	}

}
