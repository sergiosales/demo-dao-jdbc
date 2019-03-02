package aplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.seller;


public class program {

	public static void main(String[] args) {
//	Department obj = new Department(1,"Logistica");
//	seller seller = new seller(2, "paulo","sales@bol",new Date(),4.000,obj);
		
		Scanner sc  = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("Teste 1====findById implementation======");
		seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("\n Teste 2==== seller findByDepartment======");
		Department department = new Department(2, null);
		List<seller> lista = sellerDao.findByDepartment(department);
		for (seller obj : lista) {
			System.out.println(obj);

		}
		
		System.out.println("\n Teste 3==== seller findBall======");

		 lista = sellerDao.findAll();
		for (seller obj : lista) {
			System.out.println(obj);

		}

		System.out.println("\n Teste 4==== seller insert======");
		seller newseller = new seller(null,"Greg","gokkkkkk",new Date(),4000.0, department);
		sellerDao.insert(newseller);
		System.out.println("inserido New id="+ newseller.getId());

		
		System.out.println("\n Teste 5==== seller update======");
		seller= sellerDao.findById(1);
		seller.setName("Paulo Sergio Sales");
		seller.setEmail("paulounopar@hotmail.com");
		sellerDao.update(seller);
		System.out.println("update concluido");
		
		System.out.println("\n Teste 6==== seller Delete======");
		System.out.println("Entre com o numero para deletar");
		int x  = sc.nextInt();
		sellerDao.deletById(x);
		System.out.println("delete  concluido");
		sc.close();
		
//System.out.println(obj);
	

	}

}
