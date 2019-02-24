package modelo.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;

import java.sql.PreparedStatement;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.seller;

public class SellerDaoJdbc implements SellerDao {
	private Connection conn;
	
	 public SellerDaoJdbc(Connection conn) {
		this.conn = conn;
		
	}
	@Override
	public void insert(seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public seller findById(Integer id) {
	String sql= "SELECT seller.*,department.Name as DepName "
			+"FROM seller INNER JOIN department "
			+"ON seller.DepartmentId = department.Id "
			+"WHERE seller.Id = ? ";
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1,id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				seller obj = new seller();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthdate(rs.getDate("BirthDate"));
				obj.setDepartment(dep);
				return obj;
				
				
			}
			return null;
		}catch(SQLException e) {
		 throw new DbException( "erro " + e.getMessage()); 
		 
		}finally {
		DB.closeStatement(st);
		DB.closeResultSet(rs);
		}
	}

	@Override
	public List<seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

