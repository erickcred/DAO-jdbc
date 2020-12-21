package model.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement stmt = null;
//		conn.setAutoCommit(false);
		
		try {
			stmt = conn.prepareStatement(
				"INSERT INTO seller (name, email, birthDate, baseSalary, departmentId) VALUES (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
			);
			
			stmt.setString(1, seller.getName());
			stmt.setString(2, seller.getEmail());
			stmt.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			stmt.setDouble(4, seller.getBaseSalary());
			stmt.setInt(5, seller.getDepartment().getId());
			
			int rowsAffected = stmt.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected erro! No rows asffected");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
		}
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
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement("SELECT seller.*, department.name AS dpName FROM seller "
					+ "INNER JOIN department ON seller.departmentId = department.id WHERE seller.id = ?");
			
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Department dp = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dp);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException  {
		Seller obj = new Seller();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setBirthDate(rs.getDate("birthDate"));
		obj.setBaseSalary(rs.getDouble("baseSalary"));
		obj.setDepartment(dp);
		
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dp = new Department();
		dp.setId(rs.getInt("departmentId"));
		dp.setName(rs.getString("dpName"));
		
		return dp;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(
				"SELECT seller.*, department.name AS dpName FROM seller "
				+ "INNER JOIN department ON seller.departmentId = department.id ORDER BY name"	
			);
			
			rs = stmt.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				Department dp = map.get(rs.getInt("departmentId"));
				
				if (dp == null) {
					dp = instantiateDepartment(rs);
					map.put(rs.getInt("departmentId"), dp);
				}

				Seller obj = instantiateSeller(rs, dp);
				list.add(obj);
			}
			return list;			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
		}
	}
	
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(
				"SELECT seller.*, department.name as dpName From seller INNER JOIN department"
				+ " ON seller.departmentId = department.id WHERE departmentId = ? ORDER BY name"	
			);
			
			stmt.setInt(1, department.getId());
			rs = stmt.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while (rs.next()) {
				Department dp = map.get(rs.getInt("departmentId"));
				
				if (dp == null) {
					dp = instantiateDepartment(rs);
					map.put(rs.getInt("departmentId"), dp);
				}
				Seller obj = instantiateSeller(rs, dp);
				list.add(obj);
			}			
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
		}
	}

}
