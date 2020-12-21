package model.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.exception.DbException;
import model.DAO.ISellerDao;
import model.enties.Department;
import model.enties.Seller;

public class SellerDaoJDBC implements ISellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detedById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
			stmt = conn.prepareStatement("SELECT seller.*, department.name AS dpName FROM seller "
					+ "INNER JOIN department ON seller.departmentId = department.id WHERE seller.id = ?");
			
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				Department dp = new Department();
				dp.setId(resultSet.getInt("departmentId"));
				dp.setName(resultSet.getString("dpName"));
				
				Seller obj = new Seller();
				obj.setId(resultSet.getInt("id"));
				obj.setName(resultSet.getString("name"));
				obj.setEmail(resultSet.getString("email"));
				obj.setBirthDate(resultSet.getDate("birthDate"));
				obj.setBaseSalary(resultSet.getDouble("baseSalary"));
				obj.setDepartment(dp);
				
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
