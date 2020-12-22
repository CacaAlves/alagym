package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.services.ProfileServices;
import view.MealManager;
import view.ViewFactory;
import view.utils.Alerts;
import view.utils.Constraints;

public class EditGoalsController extends BaseController implements Initializable {

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

	ProfileServices profileServices = new ProfileServices();

	public EditGoalsController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		super(mealManager, viewFactory, fxmlName);
	}

	private void setUpTextFields() {
		Constraints.setTextFieldDouble(protTextField);
		protTextField.setText(Double.toString(mealManager.getProfile().getProtein().getQuantity()));
		Constraints.setTextFieldDouble(carbTextField);
		carbTextField.setText(Double.toString(mealManager.getProfile().getCarb().getQuantity()));
		Constraints.setTextFieldDouble(fatTextField);
		fatTextField.setText(Double.toString(mealManager.getProfile().getFat().getQuantity()));
		Constraints.setTextFieldDouble(fiberTextField);
		fiberTextField.setText(Double.toString(mealManager.getProfile().getFiber().getQuantity()));
		Constraints.setTextFieldDouble(sugarTextField);
		sugarTextField.setText(Double.toString(mealManager.getProfile().getSugar().getQuantity()));
		Constraints.setTextFieldDouble(vitB12TextField);
		vitB12TextField.setText(Double.toString(mealManager.getProfile().getVitB12().getQuantity()));
		Constraints.setTextFieldDouble(vitDTextField);
		vitDTextField.setText(Double.toString(mealManager.getProfile().getVitD().getQuantity()));
		Constraints.setTextFieldDouble(vitATextField);
		vitATextField.setText(Double.toString(mealManager.getProfile().getVitA().getQuantity()));
		Constraints.setTextFieldDouble(vitCTextField);
		vitCTextField.setText(Double.toString(mealManager.getProfile().getVitC().getQuantity()));
		Constraints.setTextFieldDouble(vitETextField);
		vitETextField.setText(Double.toString(mealManager.getProfile().getVitE().getQuantity()));
		Constraints.setTextFieldDouble(calciumTextField);
		calciumTextField.setText(Double.toString(mealManager.getProfile().getCalcium().getQuantity()));
		Constraints.setTextFieldDouble(iodineTextField);
		iodineTextField.setText(Double.toString(mealManager.getProfile().getIodine().getQuantity()));
		Constraints.setTextFieldDouble(ironTextField);
		ironTextField.setText(Double.toString(mealManager.getProfile().getIron().getQuantity()));
		Constraints.setTextFieldDouble(zincTextField);
		zincTextField.setText(Double.toString(mealManager.getProfile().getZinc().getQuantity()));
		Constraints.setTextFieldDouble(caloriesTextField);
		caloriesTextField.setText(Double.toString(mealManager.getProfile().getCalories().getQuantity()));
	}

	private void setUpButton() {
		saveBtn.setOnAction(e -> {
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

			mealManager.getProfile().getCalories().setQuantity(Double.parseDouble(caloriesTextField.getText()));
			mealManager.getProfile().getProtein().setQuantity(Double.parseDouble(protTextField.getText()));
			mealManager.getProfile().getCarb().setQuantity(Double.parseDouble(carbTextField.getText()));
			mealManager.getProfile().getFat().setQuantity(Double.parseDouble(fatTextField.getText()));
			mealManager.getProfile().getFiber().setQuantity(Double.parseDouble(fiberTextField.getText()));
			mealManager.getProfile().getSugar().setQuantity(Double.parseDouble(sugarTextField.getText()));
			mealManager.getProfile().getVitB12().setQuantity(Double.parseDouble(vitB12TextField.getText()));
			mealManager.getProfile().getVitD().setQuantity(Double.parseDouble(vitDTextField.getText()));
			mealManager.getProfile().getVitA().setQuantity(Double.parseDouble(vitATextField.getText()));
			mealManager.getProfile().getVitC().setQuantity(Double.parseDouble(vitCTextField.getText()));
			mealManager.getProfile().getVitE().setQuantity(Double.parseDouble(vitETextField.getText()));
			mealManager.getProfile().getCalcium().setQuantity(Double.parseDouble(calciumTextField.getText()));
			mealManager.getProfile().getIodine().setQuantity(Double.parseDouble(iodineTextField.getText()));
			mealManager.getProfile().getIron().setQuantity(Double.parseDouble(ironTextField.getText()));
			mealManager.getProfile().getZinc().setQuantity(Double.parseDouble(zincTextField.getText()));

			profileServices.updateProfile(mealManager.getProfile());

			// close window
			Stage stage = (Stage) saveBtn.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTextFields();
		setUpButton();
	}

}
