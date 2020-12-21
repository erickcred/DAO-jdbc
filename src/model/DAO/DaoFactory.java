package model.DAO;

import db.DB;
import model.DAO.impl.SellerDaoJDBC;

public class DaoFactory {

	public static ISellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
}
