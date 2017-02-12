package com.sheet.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sheet.dao.SheetDao;
import com.sheet.model.SheetModel;

@Repository
@Transactional
public class SheetDaoImpl implements SheetDao {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void addSheetData(SheetModel sheetmodel) {
		Session sess = sessionFactory.getCurrentSession();
		try {
			sess.save(sheetmodel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<SheetModel> displayAllData() {

		Session sess = sessionFactory.getCurrentSession();
		Query qry = sess.createQuery("FROM SheetModel");
		List<SheetModel> sheetInfo = qry.list();
		return sheetInfo;
	}
	
	
}
