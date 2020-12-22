package model.services;

import model.dao.DaoFactory;
import model.dao.MealFoodDao;
import model.entities.MealFood;
import model.entities.MealRecord;

public class MealFoodServices {
	
	MealFoodDao dao = DaoFactory.createMealFoodDao();
	
	public void addFoodToRecords(MealRecord mealRecord, MealFood mealFood) {
		dao.insert(mealRecord.getId(), mealFood);
	}
	
	public void deleteFoodFromRecords(MealFood mealFood) {
		dao.deleteById(mealFood.getMealFoodId());
	}
}
