package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.FoodDao;
import model.entities.Food;
import model.entities.MealFood;

public class FoodServices {
	
	FoodDao dao = DaoFactory.createFoodDao();
	
	public Food getFood(Integer id) {
		return dao.findById(id);
	}

	public void registerFood(Food food) {
		dao.insert(food);
	}
	
	public List<Food> getAllFoods() {
		return dao.findAll();
	}

	public void deleteFood(Food food) {
		dao.deleteById(food.getId());
		
	}

	public MealFood parseToMealFood(Food food) {
		MealFood mealFood = new MealFood();
		mealFood.setId(food.getId());
		mealFood.setCalcium(food.getCalcium());
		mealFood.setCalories(food.getCalories());
		mealFood.setCarb(food.getCarb());
		mealFood.setFat(food.getFat());
		mealFood.setFiber(food.getFiber());
		mealFood.setIodine(food.getIodine());
		mealFood.setIron(food.getIron());
		mealFood.setIron(food.getIron());
		mealFood.setName(food.getName());
		mealFood.setPortion(food.getPortion());
		mealFood.setProtein(food.getProtein());
		mealFood.setSugar(food.getSugar());
		mealFood.setVitA(food.getVitA());
		mealFood.setVitB12(food.getVitB12());
		mealFood.setVitC(food.getVitC());
		mealFood.setVitD(food.getVitD());
		mealFood.setVitE(food.getVitE());
		mealFood.setZinc(food.getZinc());
		
		return mealFood;
	}
	
}
