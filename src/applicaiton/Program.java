package applicaiton;

import java.util.Date;

import model.DAO.DaoFactory;
import model.DAO.ISellerDao;
import model.enties.Department;
import model.enties.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		
		Seller seller = new Seller(1, "João da Silva", "j.silva@gamail.com", new Date(), 2550d, obj);
		
		ISellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println(obj + "\n" + seller);
	}
}
