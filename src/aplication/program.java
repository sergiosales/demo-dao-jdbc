package aplication;

import java.util.Date;

import model.entities.Department;
import model.entities.seller;

public class program {

	public static void main(String[] args) {
	Department obj = new Department(1,"Logistica");
	seller seller = new seller(2, "paulo","sales@bol",new Date(),4.000,obj);
	System.out.println(obj);
	System.out.println(seller);

	}

}
