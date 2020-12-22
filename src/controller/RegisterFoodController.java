package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.entities.Food;
import model.services.FoodServices;
import view.MealManager;
import view.ViewFactory;
import view.utils.Alerts;
import view.utils.Constraints;

public class RegisterFoodController extends BaseController implements Initializable {

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField portionTextField;

	@FXML
	private TextField protTextField;

	@FXML
	private TextField carbTextField;

	@FXML
	private TextField fatTextField;

	@FXML
	private TextField fiberTextField;

	@FXML
	private TextField sugarTextField;

	@FXML
	private TextField vitB12TextField;

	@FXML
	private TextField vitDTextField;

	@FXML
	private TextField vitATextField;

	@FXML
	private TextField vitCTextField;

	@FXML
	private TextField vitETextField;

	@FXML
	private TextField calciumTextField;

	@FXML
	private TextField iodineTextField;

	@FXML
	private TextField ironTextField;

	@FXML
	private TextField zincTextField;

	@FXML
	private TextField caloriesTextField;

	@FXML
	private Button saveBtn;

	FoodServices foodServices = new FoodServices();
	
	public RegisterFoodController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		super(mealManager, viewFactory, fxmlName);
		// TODO Auto-generated constructor stub
	}

	private void setUpTextFields() {
		Constraints.setTextFieldMaxLength(nameTextField, 40);
		Constraints.setTextFieldDouble(portionTextField);
		portionTextField.setText("0.0");
		Constraints.setTextFieldDouble(protTextField);
		protTextField.setText("0.0");
		Constraints.setTextFieldDouble(carbTextField);
		carbTextField.setText("0.0");
		Constraints.setTextFieldDouble(fatTextField);
		fatTextField.setText("0.0");
		Constraints.setTextFieldDouble(fiberTextField);
		fiberTextField.setText("0.0");
		Constraints.setTextFieldDouble(sugarTextField);
		sugarTextField.setText("0.0");
		Constraints.setTextFieldDouble(vitB12TextField);
		vitB12TextField.setText("0.0");
		Constraints.setTextFieldDouble(vitDTextField);
		vitDTextField.setText("0.0");
		Constraints.setTextFieldDouble(vitATextField);
		vitATextField.setText("0.0");
		Constraints.setTextFieldDouble(vitCTextField);
		vitCTextField.setText("0.0");
		Constraints.setTextFieldDouble(vitETextField);
		vitETextField.setText("0.0");
		Constraints.setTextFieldDouble(calciumTextField);
		calciumTextField.setText("0.0");
		Constraints.setTextFieldDouble(iodineTextField);
		iodineTextField.setText("0.0");
		Constraints.setTextFieldDouble(ironTextField);
		ironTextField.setText("0.0");
		Constraints.setTextFieldDouble(zincTextField);
		zincTextField.setText("0.0");
		Constraints.setTextFieldDouble(caloriesTextField);
		caloriesTextField.setText("0.0");

	}

	private void setUpButton() {
		saveBtn.setOnAction(e -> {
			
			if (nameTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter a name", AlertType.ERROR);
				return;
			}
			
			if (portionTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter a portion", AlertType.ERROR);
				return;
			}
			
			if (protTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the protein field", AlertType.ERROR);
				return;
			}
			
			if (carbTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the carb field", AlertType.ERROR);
				return;
			}
			
			if (fatTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the fat field", AlertType.ERROR);
				return;
			}
			
			if (sugarTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the sugar field", AlertType.ERROR);
				return;
			}
			
			if (fiberTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the fiber field", AlertType.ERROR);
				return;
			}
			
			if (vitB12TextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the vitB12 field", AlertType.ERROR);
				return;
			}
			
			if (vitDTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the vitD field", AlertType.ERROR);
				return;
			}
			
			if (vitATextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the vitA field", AlertType.ERROR);
				return;
			}
			
			if (vitCTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the vitC field", AlertType.ERROR);
				return;
			}
			
			if (vitETextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the vitE field", AlertType.ERROR);
				return;
			}
			
			if (calciumTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the calcium field", AlertType.ERROR);
				return;
			}
			
			if (iodineTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the iodine field", AlertType.ERROR);
				return;
			}
			
			if (ironTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the iron field", AlertType.ERROR);
				return;
			}
			
			if (zincTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the zinc field", AlertType.ERROR);
				return;
			}
			
			if (caloriesTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter the calories field", AlertType.ERROR);
				return;
			}
			
			Food food = new Food(
			null,
			nameTextField.getText(),
			Double.parseDouble(portionTextField.getText()),
			Double.parseDouble(caloriesTextField.getText()),
			Double.parseDouble(protTextField.getText()),
			Double.parseDouble(carbTextField.getText()),
			Double.parseDouble(fatTextField.getText()),
			Double.parseDouble(sugarTextField.getText()),
			Double.parseDouble(fiberTextField.getText()),
			Double.parseDouble(vitB12TextField.getText()),
			Double.parseDouble(vitDTextField.getText()),
			Double.parseDouble(vitATextField.getText()),
			Double.parseDouble(vitCTextField.getText()),
			Double.parseDouble(vitETextField.getText()),
			Double.parseDouble(calciumTextField.getText()),
			Double.parseDouble(iodineTextField.getText()),
			Double.parseDouble(ironTextField.getText()),
			Double.parseDouble(zincTextField.getText())
		);
			foodServices.registerFood(food);
			
			//closing window 
			Stage stage = (Stage) portionTextField.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTextFields();
		setUpButton();
	}

}
