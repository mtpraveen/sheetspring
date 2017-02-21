package com.sheet.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.sheet.dao.SheetDao;
import com.sheet.model.SheetModel;
import com.sheet.services.SheetService;

public class SheetServiceImpl implements SheetService {
	
	@Autowired
	private SheetDao mSheetDao;
	
	/**
	 * adding data to sql
	 */
	@Override
	public void addSheetData(SheetModel sheetmodel) {
			mSheetDao.addSheetData(sheetmodel);
			
	}
	/**
	 * getting data
	 */
	@Override
	public List<SheetModel> displayAllData() {
		List<SheetModel> sheetList =mSheetDao.displayAllData();
		return sheetList;
	}
	/**
	 * getting timestamp data
	 */
	@Override
	public List getTimeStampData() {
		List  timeStampList =mSheetDao.getTimeStampData();
		return timeStampList;
	}
	/**
	 * getting data
	 */
	@Override
	public SheetModel timeStampExist(String timeStamp) {
		SheetModel sheetModel=mSheetDao.timeStampExist(timeStamp);
		return sheetModel;
	}
	/**
	 * getting data based on enggid
	 */
	@Override
	public List<SheetModel> displayAllDetails(String enggId) {
		List<SheetModel> sheetDetails = mSheetDao.displayAllDetails(enggId);
		return sheetDetails;
	}
	/**
	 * getting url data 
	 */
	@Override
	public List getURLData() {
		List  sheetURLList =mSheetDao.getURLData();
		return sheetURLList;
	}
	@Override
	public List<String> getSuggestion(String name) {
		List  sheetSuggestion =mSheetDao.getSuggestion(name);
		return sheetSuggestion;
	}
	@Override
	public List<SheetModel> getSearchNameData(String pName) {
		List<SheetModel> sheetSearchDetails = mSheetDao.getSearchNameData(pName);
		return sheetSearchDetails;
	}
	

}
