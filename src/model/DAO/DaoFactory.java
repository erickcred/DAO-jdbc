package model.DAO;

import model.DAO.impl.SellerDaoJDBC;

public class DaoFactory {

	public static ISellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
