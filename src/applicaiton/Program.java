package applicaiton;

import db.DB;
import model.DAO.DaoFactory;
import model.DAO.ISellerDao;
import model.enties.Seller;

public class Program {

	public static void main(String[] args) {
				
		Seller seller = null;
		
		ISellerDao sellerDao = DaoFactory.createSellerDao();
		seller = sellerDao.findById(2);
		DB.closeConnection();
		
		System.out.println(seller);
	}
}
