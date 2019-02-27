package modelo.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	String sql = "INSERT INTO seller " + 
			"(Name, Email, BirthDate, BaseSalary, DepartmentId) " + 
			"VALUES " + 
			"(?, ?, ?, ?, ?)" ;
	PreparedStatement  st = null;
	try {
		st = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		st.setString(1,obj.getName());
		st.setString(2,obj.getEmail());
		st.setDate(3,new java.sql.Date(obj.getBirthdate().getTime()));
		st.setDouble(4,obj.getBaseSalary());
		st.setInt(5,obj.getDepartment().getId());
		
		int rowsAffected = st.executeUpdate();
		if(rowsAffected >0) {
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				obj.setId(id);
				
				
				
			}
			DB.closeResultSet( rs);
			
			
		}else {
			throw new DbException("Erro inexperado nenhuma linha afetada");
			
			
			
		}
		
		
	} catch (Exception e) {
		throw new DbException(e.getMessage());
	}finally {
		db.DB.closeStatement(st);
	}

	
	
	
			
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
				Department dep = instanciateDepartment(rs);				
				seller obj = instanciateSeller(rs,dep);				
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

	private seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
		seller obj=	new seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthdate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}
	private Department instanciateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
		
	}
	@Override
	public List<seller> findAll() {
		String sql= "SELECT seller.*,department.Name as DepName " + 
				"FROM seller INNER JOIN department " + 
				"ON seller.DepartmentId = department.Id " + 
				"ORDER BY Name "; 
				
			
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st= conn.prepareStatement(sql);
				
				rs = st.executeQuery();
		List<seller> lista = new ArrayList<>();	
		Map<Integer,Department> map = new HashMap<>();
			while(rs.next()) {
			    	Department dep = map.get(rs.getInt("DepartmentId"));	
			    	if(dep ==null) {
				   dep = instanciateDepartment(rs);	
					map.put(rs.getInt("DepartmentId"), dep);
			    	}
					seller obj = instanciateSeller(rs,dep);				
					lista.add(obj);
					
					
				}
				return lista;
			}catch(SQLException e) {
			 throw new DbException( "erro " + e.getMessage()); 
			 
			}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			}
	}
	@Override
	public List<seller> findByDepartment(Department department) {
	
		String sql= "SELECT seller.*,department.Name as DepName " + 
				"FROM seller INNER JOIN department " + 
				"ON seller.DepartmentId = department.Id " + 
				"WHERE DepartmentId = ? " + 
				"ORDER BY Name "; 
				
			
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st= conn.prepareStatement(sql);
				st.setInt(1,department.getId());
				rs = st.executeQuery();
		List<seller> lista = new ArrayList<>();	
		Map<Integer,Department> map = new HashMap<>();
			while(rs.next()) {
			    	Department dep = map.get(rs.getInt("DepartmentId"));	
			    	if(dep ==null) {
				   dep = instanciateDepartment(rs);	
					map.put(rs.getInt("DepartmentId"), dep);
			    	}
					seller obj = instanciateSeller(rs,dep);				
					lista.add(obj);
					
					
				}
				return lista;
			}catch(SQLException e) {
			 throw new DbException( "erro " + e.getMessage()); 
			 
			}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			}
	}
	
}

