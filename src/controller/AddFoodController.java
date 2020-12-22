package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.MealFood;
import model.entities.MealType;
import model.services.FoodServices;
import model.services.MealFoodServices;
import model.services.MealRecordServices;
import view.MealManager;
import view.ViewFactory;
import view.utils.Alerts;
import view.utils.Constraints;

public class AddFoodController extends BaseController implements Initializable {

	@FXML
	private Button selectFoodButton;

	@FXML
	private TextField quantityTextField;

	@FXML
	private ChoiceBox<String> mealChoiceBox;

	@FXML
	private Button saveBtn;

	private MealFoodServices mealFoodServices = new MealFoodServices();

	private FoodServices foodServices = new FoodServices();
	
	private MealRecordServices mealRecordServices = new MealRecordServices();

	public AddFoodController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		super(mealManager, viewFactory, fxmlName);
	}

	private void setUpButtons() {
		selectFoodButton.setOnAction(e -> {
			viewFactory.showListFoods(true);
		});

		saveBtn.setOnAction(e -> {
			if (mealManager.getListFoodsSelectedFood() == null) {
				Alerts.showAlert("Add food error", null, "Choose a food", AlertType.ERROR);
				return;
			}

			if (quantityTextField.getText().isBlank()) {
				Alerts.showAlert("Add food error", null, "Input a quantity to the food", AlertType.ERROR);
				return;
			}

			MealFood mealFood = foodServices.parseToMealFood(
					foodServices.getFood(mealManager.getListFoodsSelectedFood().getId()));

			mealFood.getQuantity().setQuantity(Double.parseDouble(quantityTextField.getText()));

			
			MealType mealType = null;

			switch (mealChoiceBox.getValue()) {
			case "Breakfast":
				mealType = MealType.BREAKFAST;
				break;
			case "Lunch":
				mealType = MealType.LUNCH;
				break;
			case "Dinner":
				mealType = MealType.DINNER;
				break;
			case "Snacks":
				mealType = MealType.SNACKS;
				break;

			default:
				break;
			}

			mealFood.setMealType(mealType);

			mealFoodServices.addFoodToRecords(
					mealRecordServices.getMealRecordFromToday(mealManager.getProfile()),
					mealFood); 

			mealManager.setListFoodsSelectedFood(null);
			
			mealManager.setTableViewsWithTodaysMeal(mealRecordServices.getMealRecordFromToday(mealManager.getProfile()).getBreakfast());

			// close window
			Stage stage = (Stage) saveBtn.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);

		});
	}

	private void setUpTextField() {
		Constraints.setTextFieldDouble(quantityTextField);
	}

	private void setUpChoiceBox() {
		mealChoiceBox.getItems().add("Breakfast");
		mealChoiceBox.getItems().add("Lunch");
		mealChoiceBox.getItems().add("Dinner");
		mealChoiceBox.getItems().add("Snacks");
		mealChoiceBox.getSelectionModel().selectFirst();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpButtons();
		setUpTextField();
		setUpChoiceBox();
	}

}
