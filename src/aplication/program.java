package aplication;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.seller;
import modelo.dao.imp.SellerDaoJdbc;

public class program {

	public static void main(String[] args) {
	Department obj = new Department(1,"Logistica");
	seller seller = new seller(2, "paulo","sales@bol",new Date(),4.000,obj);
	SellerDao sellerDao = DaoFactory.createSellerDao();
	
	
	System.out.println(obj);
	System.out.println(seller);

	}

}
