package applicaiton;

import java.util.Date;
import java.util.List;

import model.DAO.DaoFactory;
import model.DAO.ISellerDao;
import model.enties.Department;
import model.enties.Seller;

public class Program {

	public static void main(String[] args) {
		
		ISellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(2);
		
		System.out.println("=== TEST 1: seller findByid ===");
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		Department dp = new Department(1, null);
		List<Seller> list = sellerDao.findByDepartment(dp);
		list.forEach(obj -> {
			System.out.println(obj);
		});
		
		System.out.println("\n=== TEST 3: seller findAll ===");
		list.forEach(obj -> {
			System.out.println(obj);
		});
		
		System.out.println("\n=== TEST 4: sellset insert ===");
		Seller newSeller = new Seller(null, "Jederson", "email_jed@teste.com", new Date(), 1580d, dp);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
	}
}
