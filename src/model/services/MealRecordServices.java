package model.services;

import java.time.LocalDate;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.MealFoodDao;
import model.dao.MealRecordDao;
import model.entities.MealFood;
import model.entities.MealRecord;
import model.entities.Profile;

public class MealRecordServices {
	
	MealRecordDao dao = DaoFactory.createMealRecordDao();
	
	MealFoodDao mealFoodDao = DaoFactory.createMealFoodDao();
	
	public MealRecord getMealRecordFromToday(Profile profile) {
		MealRecord mealRecord = dao.find(profile.getId(), LocalDate.now());
		if (mealRecord == null) return null;
		List<MealFood> foods = mealFoodDao.findByMealRecordId(mealRecord.getId());
		for (MealFood food : foods) {
			food.setUpCalculatedItems();
			switch (food.getMealType()) {
			case BREAKFAST:
				mealRecord.getBreakfast().add(food);
				break;
			case LUNCH :
				mealRecord.getLunch().add(food);
				break;
			case DINNER:
				mealRecord.getDinner().add(food);
				break;
			case SNACKS:
				mealRecord.getSnacks().add(food);
				break;
				default:
			}
		}
		
		return mealRecord;
		
	}
	
	
	public MealRecord getMealRecordFrom(Profile profile, LocalDate date) {
		MealRecord mealRecord = dao.find(profile.getId(), date);
		if (mealRecord == null) return mealRecord;
		List<MealFood> foods = mealFoodDao.findByMealRecordId(mealRecord.getId());
		for (MealFood food : foods) {
			food.setUpCalculatedItems();
			switch (food.getMealType()) {
			case BREAKFAST:
				mealRecord.getBreakfast().add(food);
				break;
			case LUNCH :
				mealRecord.getLunch().add(food);
				break;
			case DINNER:
				mealRecord.getDinner().add(food);
				break;
			case SNACKS:
				mealRecord.getSnacks().add(food);
				break;
				default:
			}
		}
		
		return mealRecord;
	
	}
	
	public void addMealRecordFromToday(Profile profile) {
		MealRecord mealRecord = new MealRecord();
		mealRecord.setProfile(profile);
		mealRecord.setDate(LocalDate.now());
		dao.insert(mealRecord);
	}
	
	public List<MealRecord> getAllRecords() {	
		return dao.findAll();
	}
	
	public void deleteMealRecord(MealRecord mealRecord) {
		dao.deleteById(mealRecord.getId());
	}
}
