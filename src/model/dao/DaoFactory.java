package model.dao;

import modelo.dao.imp.SellerDaoJdbc;

public class DaoFactory {

	
	public static SellerDao createSellerDao() {
		
		
		return new SellerDaoJdbc();
		
	}
}
