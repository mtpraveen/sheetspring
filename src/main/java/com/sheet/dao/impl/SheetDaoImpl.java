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
	SessionFactory mSessionFactory;
	
	/** store the data from sheet. */
	@Override
	public void addSheetData(SheetModel sheetmodel) {
		Session sess = mSessionFactory.getCurrentSession();
		try {
			sess.save(sheetmodel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** get the data from sheet. */
	@Override
	public List<SheetModel> displayAllData() {

		Session sess = mSessionFactory.getCurrentSession();
		Query qry = sess.createQuery("FROM SheetModel");
		List<SheetModel> sheetInfo = qry.list();
		return sheetInfo;
	}
	
	@Override
	public List getTimeStampData() {
		Session sess = mSessionFactory.getCurrentSession();
		Query qry = sess.createQuery("SELECT timestamp FROM SheetModel");
		List timeStampInfo = qry.list();
		return timeStampInfo;
	}
	/** get timestamp data from database. */
	@Override
	public SheetModel timeStampExist(String timeStamp) {
		Session sess = mSessionFactory.getCurrentSession();
		
		Criteria cr = sess.createCriteria(SheetModel.class);
		SheetModel sheetModel=(SheetModel) cr.add(Restrictions.eq("timestamp",timeStamp)).uniqueResult();
		return sheetModel;
	}
	
	/** get all data from database. */
	@Override
	public List<SheetModel> displayAllDetails(String email) {
		Session sess = mSessionFactory.getCurrentSession();
		Query qry = sess.createQuery("from SheetModel where email=:email");
		qry.setParameter("email", email);
		List<SheetModel> sheetDetails = qry.list();
		return sheetDetails;
	}
	/** get url data from database. */
	@Override
	public List getURLData() {
		Session sess = mSessionFactory.getCurrentSession();
		Query qry = sess.createQuery("SELECT facebookurl FROM SheetModel");
		List sheetURLInfo = qry.list();
		return sheetURLInfo;
	}
	
	
}
