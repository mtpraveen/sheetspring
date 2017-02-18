package com.sheet.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sheet.dao.SheetDao;
import com.sheet.model.SheetModel;
import com.sheet.services.SheetService;

public class SheetServiceImpl implements SheetService {
	
	@Autowired
	private SheetDao sheetDao;
	
	/**
	 * adding data to sql
	 */
	@Override
	public void addSheetData(SheetModel sheetmodel) {
			sheetDao.addSheetData(sheetmodel);
			
	}
	/**
	 * getting data
	 */
	@Override
	public List<SheetModel> displayAllData() {
		List<SheetModel> sheetList =sheetDao.displayAllData();
		return sheetList;
	}
	/**
	 * getting timestamp data
	 */
	@Override
	public List getTimeStampData() {
		List  timeStampList =sheetDao.getTimeStampData();
		return timeStampList;
	}
	/**
	 * getting data
	 */
	@Override
	public SheetModel timeStampExist(String timeStamp) {
		SheetModel sheetModel=sheetDao.timeStampExist(timeStamp);
		return sheetModel;
	}
	/**
	 * getting data based on enggid
	 */
	@Override
	public List<SheetModel> displayAllDetails(String enggId) {
		List<SheetModel> sheetDetails = sheetDao.displayAllDetails(enggId);
		return sheetDetails;
	}
	/**
	 * getting url data 
	 */
	@Override
	public List getURLData() {
		List  sheetURLList =sheetDao.getURLData();
		return sheetURLList;
	}

}
