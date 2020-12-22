package model.entities;

import java.util.List;

public class Meal {
	
	private Integer id;
	
	private List<Food> foods;
	
	private MealType mealType;
	
	public Meal() {
		this(null, null, null);
	}

	public Meal(Integer id, List<Food> foods, MealType mealType) {
		this.id = id;
		this.foods = foods;
		this.mealType = mealType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

}
