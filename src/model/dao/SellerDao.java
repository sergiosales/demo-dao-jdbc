package model.dao;

import java.util.List;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import model.entities.Department;
import model.entities.seller;

public interface SellerDao {
	void insert(seller obj);
	void update(seller obj);
	void deletById(Integer id);
	seller findById(Integer id);
	List<seller>findAll();
    List<seller> findByDepartment(Department department);

}
