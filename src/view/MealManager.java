package view;

import java.util.List;

import javafx.scene.control.TableView;
import model.entities.Food;
import model.entities.MealFood;
import model.entities.Profile;

public class MealManager {

	private Profile profile = null;

	private Food listFoodsSelectedFood = null;

	private TableView<MealFood> mainWindowMacroTableView = null;

	private TableView<MealFood> mainWindowMicroTableView = null;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Food getListFoodsSelectedFood() {
		return listFoodsSelectedFood;
	}

	public void setListFoodsSelectedFood(Food selectedFood) {
		this.listFoodsSelectedFood = selectedFood;

	}

	public TableView<MealFood> getMainWindowMacroTableView() {
		return mainWindowMacroTableView;
	}

	public void setMainWindowMacroTableView(TableView<MealFood> mainWindowMacroTableView) {
		this.mainWindowMacroTableView = mainWindowMacroTableView;
	}

	public TableView<MealFood> getMainWindowMicroTableView() {
		return mainWindowMicroTableView;
	}

	public void setMainWindowMicroTableView(TableView<MealFood> mainWindowMicroTableView) {
		this.mainWindowMicroTableView = mainWindowMicroTableView;
	}

	public void setTableViewsWithTodaysMeal(List<MealFood> list) {
		if (mainWindowMacroTableView == null || mainWindowMicroTableView == null)
			return;

		boolean noFood = false;

		if (list == null) {
			noFood = true;
		}
		if (list.size() == 0) {
			noFood = true;
		}

		MealFood total = new MealFood();
		total.setName("TOTAL");
		MealFood goal = new MealFood();
		goal.setName("GOAL");
		if (list != null) {
			for (MealFood f : list) {
				// TOTAL
				total.getPortion().setQuantity(total.getPortion().getQuantity() + f.getPortion().getQuantity());
				total.getQuantity().setQuantity(total.getQuantity().getQuantity() + f.getQuantity().getQuantity());
				total.getCalories().setQuantity(total.getCalories().getQuantity() + f.getCalculatedCalories().getQuantity());
				total.getProtein().setQuantity(total.getProtein().getQuantity() + f.getCalculatedProtein().getQuantity());
				total.getCarb().setQuantity(total.getCarb().getQuantity() + f.getCalculatedCarb().getQuantity());
				total.getFat().setQuantity(total.getFat().getQuantity() + f.getCalculatedFat().getQuantity());
				total.getFiber().setQuantity(total.getFiber().getQuantity() + f.getCalculatedFiber().getQuantity());
				total.getSugar().setQuantity(total.getSugar().getQuantity() + f.getCalculatedSugar().getQuantity());
				total.getVitB12().setQuantity(total.getVitB12().getQuantity() + f.getCalculatedVitB12().getQuantity());
				total.getVitD().setQuantity(total.getVitD().getQuantity() + f.getCalculatedVitD().getQuantity());
				total.getVitA().setQuantity(total.getVitA().getQuantity() + f.getCalculatedVitA().getQuantity());
				total.getVitC().setQuantity(total.getVitC().getQuantity() + f.getCalculatedVitC().getQuantity());
				total.getVitE().setQuantity(total.getVitE().getQuantity() + f.getCalculatedVitE().getQuantity());
				total.getCalcium().setQuantity(total.getCalcium().getQuantity() + f.getCalculatedCalcium().getQuantity());
				total.getIodine().setQuantity(total.getIodine().getQuantity() + f.getCalculatedIodine().getQuantity());
				total.getIron().setQuantity(total.getIron().getQuantity() + f.getCalculatedIron().getQuantity());
				total.getZinc().setQuantity(total.getZinc().getQuantity() + f.getCalculatedZinc().getQuantity());
			}
		}
		// GOAL
		goal.setPortion(null);
		goal.setQuantity(null);
		goal.getCalories().setQuantity(profile.getCalories().getQuantity());
		goal.getProtein().setQuantity(profile.getProtein().getQuantity());
		goal.getCarb().setQuantity(profile.getCarb().getQuantity());
		goal.getFat().setQuantity(profile.getFat().getQuantity());
		goal.getFiber().setQuantity(profile.getFiber().getQuantity());
		goal.getSugar().setQuantity(profile.getSugar().getQuantity());
		goal.getVitB12().setQuantity(profile.getVitB12().getQuantity());
		goal.getVitD().setQuantity(profile.getVitD().getQuantity());
		goal.getVitA().setQuantity(profile.getVitA().getQuantity());
		goal.getVitC().setQuantity(profile.getVitC().getQuantity());
		goal.getVitE().setQuantity(profile.getVitE().getQuantity());
		goal.getCalcium().setQuantity(profile.getCalcium().getQuantity());
		goal.getIodine().setQuantity(profile.getIodine().getQuantity());
		goal.getIron().setQuantity(profile.getIron().getQuantity());
		goal.getZinc().setQuantity(profile.getZinc().getQuantity());

		mainWindowMacroTableView.getItems().clear();
		mainWindowMicroTableView.getItems().clear();
		if (!noFood) {

			for (MealFood f : list) {
				mainWindowMacroTableView.getItems().add(f);
				mainWindowMicroTableView.getItems().add(f);
			}
		}

		total.setCalculatedItemsEqualsToItem();
		// i don't want to calculate the item for goal because there is no portion in
		// goal
		goal.setCalculatedItemsEqualsToItem();
		mainWindowMacroTableView.getItems().add(total);
		mainWindowMacroTableView.getItems().add(goal);
		mainWindowMicroTableView.getItems().add(total);
		mainWindowMicroTableView.getItems().add(goal);
	}

}
