package com.sheet.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sheet.model.SheetModel;

public interface SheetService {
	
	/** ADD data to database. */
	public  void addSheetData(SheetModel sheetmodel);
	
	/** get data from database. */
	public List<SheetModel> displayAllData();
	
	public List getTimeStampData();
	
	/** get timestamp data from database. */
	public SheetModel timeStampExist(String timeStamp);
	
	/** get  data from database based on email . */
	public List<SheetModel> displayAllDetails(String email);
	
	/** get url data . */
	public List getURLData();
	
	/** get  suggestion data from database based on keyword . */
	public List<String> getSuggestion(String name);
	
	/** get  data from database based on keyword . */
	public List<SheetModel> getSearchNameData(String pName);

		
	

}
