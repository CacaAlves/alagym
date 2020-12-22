package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealRecord {
	
	private Integer id = null;
	
	private Profile profile;
	
	private LocalDate date;
	
	private List<MealFood> breakfast;
	
	private List<MealFood> lunch;
	
	private List<MealFood> dinner;
	
	private List<MealFood> snacks;
	
	public MealRecord() {
		this(null, null, null);
	}

	public MealRecord(Integer id, Profile profile, LocalDate date) {
		this.id = id;
		this.profile = profile;
		this.date = date;
		this.breakfast = new ArrayList<>();
		this.lunch = new ArrayList<>();
		this.dinner = new ArrayList<>();
		this.snacks = new ArrayList<>();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<MealFood> getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(List<MealFood> breakfast) {
		this.breakfast = breakfast;
	}

	public List<MealFood> getLunch() {
		return lunch;
	}

	public void setLunch(List<MealFood> lunch) {
		this.lunch = lunch;
	}

	public List<MealFood> getDinner() {
		return dinner;
	}

	public void setDinner(List<MealFood> dinner) {
		this.dinner = dinner;
	}

	public List<MealFood> getSnacks() {
		return snacks;
	}

	public void setSnacks(List<MealFood> snacks) {
		this.snacks = snacks;
	}
	
	public List<MealFood> getFoods() {
		List<MealFood> foods = new ArrayList<>();
		for (MealFood f : breakfast) {
			foods.add(f);
		}
		for (MealFood f : lunch) {
			foods.add(f);
		}
		for (MealFood f : dinner) {
			foods.add(f);
		}
		for (MealFood f : snacks) {
			foods.add(f);
		}
		
		return foods;
	}
	
}
