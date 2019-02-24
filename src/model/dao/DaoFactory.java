package model.dao;


import db.DB;
import modelo.dao.imp.SellerDaoJdbc;

public class DaoFactory {

	
	public static SellerDao createSellerDao() { 
		return new SellerDaoJdbc(DB.getConnection());
		
//	return new SellerDaoJdbc(DB.getConnection());
		
	}
}
