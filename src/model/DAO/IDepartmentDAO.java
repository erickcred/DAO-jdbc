package model.DAO;

import java.util.List;

import model.enties.Department;

public interface IDepartmentDAO {

	public void insert(Department department);
	public void update(Department department);
	public void detedById(Integer id);
	public Department findById(Integer id);
	public List<Department> findAll();
}
