package com.sheet.services;

import java.util.List;
import java.util.Map;

import com.sheet.model.SheetModel;

public interface SheetService {
	
	public  void addSheetData(SheetModel sheetmodel);
	public List<SheetModel> displayAllData();
		
	

}
