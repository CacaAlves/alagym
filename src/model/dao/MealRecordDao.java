package model.dao;

import java.time.LocalDate;
import java.util.List;

import model.entities.MealRecord;

public interface MealRecordDao {
	void insert(MealRecord obj);

	void update(MealRecord obj);

	void deleteById(Integer id);

	MealRecord find(Integer profileId, LocalDate date);
	
	List<MealRecord> findAll();
}
