package com.sheet.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	@Override
	public List getTimeStampData() {
		Session sess = sessionFactory.getCurrentSession();
		Query qry = sess.createQuery("SELECT timestamp FROM SheetModel");
		List timeStampInfo = qry.list();
		return timeStampInfo;
	}
	@Override
	public SheetModel timeStampExist(String timeStamp) {
		Session sess = sessionFactory.getCurrentSession();
		
		Criteria cr = sess.createCriteria(SheetModel.class);
		SheetModel sheetModel=(SheetModel) cr.add(Restrictions.eq("timestamp",timeStamp)).uniqueResult();
		return sheetModel;
	}
	
	
}
