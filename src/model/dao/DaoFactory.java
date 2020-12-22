package model.dao;

import db.DB;
import model.dao.imp.FoodDaoJDBC;
import model.dao.imp.MealFoodDaoJDBC;
import model.dao.imp.MealRecordDaoJDBC;
import model.dao.imp.ProfileDaoJDBC;

public class DaoFactory {
	
	public static ProfileDao createProfileDao() { 
		return new ProfileDaoJDBC(DB.getConnection());
	}
	
	public static FoodDao createFoodDao() {
		return new FoodDaoJDBC(DB.getConnection());
	}

	public static MealRecordDao createMealRecordDao() {
		return new MealRecordDaoJDBC(DB.getConnection());
	}
	
	public static MealFoodDao createMealFoodDao() {
		return new MealFoodDaoJDBC(DB.getConnection());
	}
}
