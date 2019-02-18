package model.dao;

import java.util.List;

import model.entities.seller;

public interface SellerDao {
	void insert(seller obj);
	void update(seller obj);
	void deletById(Integer id);
	seller findById(Integer id);
	List<seller>findAll();

}
