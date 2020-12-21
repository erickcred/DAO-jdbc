package model.DAO;

import java.util.List;

import model.enties.Department;
import model.enties.Seller;

public interface ISellerDao {

	public void insert(Seller seller);
	public void update(Seller seller);
	public void detedById(Integer id);
	public Seller findById(Integer id);
	public List<Seller> findAll();
	public List<Seller> findByDepartment(Department department);
}
