package model.dao;

import java.util.List;

import model.entities.MealFood;

public interface MealFoodDao {
	void insert(Integer mealRecordId, MealFood obj);

	void update(MealFood obj);

	void deleteById(Integer id);

	MealFood findById(Integer id);
	
	List<MealFood> findByMealRecordId(Integer mealRecordId); 

	List<MealFood> findAll();
}
