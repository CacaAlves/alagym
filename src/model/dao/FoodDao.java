package model.dao;

import java.util.List;

import model.entities.Food;

public interface FoodDao {
	void insert(Food obj);

	void update(Food obj);

	void deleteById(Integer id);

	Food findById(Integer id);

	List<Food> findAll();
}
