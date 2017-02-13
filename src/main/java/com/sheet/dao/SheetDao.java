package com.sheet.dao;

import java.util.List;
import java.util.Map;

import com.sheet.model.SheetModel;

public interface SheetDao {
	
	public  void addSheetData(SheetModel sheetmodel);
	List<SheetModel> displayAllData();
	public List getTimeStampData();
	
	public SheetModel timeStampExist(String timeStamp);
	
}
