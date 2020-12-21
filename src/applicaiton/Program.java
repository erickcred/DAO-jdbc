package applicaiton;

import db.DB;
import model.DAO.DaoFactory;
import model.DAO.ISellerDao;
import model.enties.Seller;

public class Program {

	public static void main(String[] args) {
		
		ISellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(2);
		
		System.out.println("=== TEST 1: seller findByid ===");
		System.out.println(seller);
	}
}
