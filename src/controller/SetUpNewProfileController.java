package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.entities.GymGoal;
import model.entities.Profile;
import model.services.ProfileServices;
import view.MealManager;
import view.ViewFactory;
import view.utils.Alerts;
import view.utils.Constraints;

public class SetUpNewProfileController extends BaseController implements Initializable {

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField ageTextField;

	@FXML
	private TextField weightTextField;

	@FXML
	private TextField heightTextField;

	@FXML
	private ChoiceBox<String> genreChoiceBox;

	@FXML
	private ChoiceBox<String> goalChoiceBox;

	@FXML
	private Button choosePhotoButton;

	@FXML
	private Button saveBtn;

	private Profile profile = new Profile();

	private ProfileServices profileServices = new ProfileServices();

	public SetUpNewProfileController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		super(mealManager, viewFactory, fxmlName);
	}

	private void setUpButtons() {
		saveBtn.setOnAction(e -> {
			boolean isUpdate = (mealManager.getProfile() != null);

			if (nameTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter a name", AlertType.ERROR);
				return;
			}
			if (isUpdate) {
				 mealManager.getProfile().setName(nameTextField.getText());
			} else {
				profile.setName(nameTextField.getText());				
			}

			if (ageTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter an age", AlertType.ERROR);
				return;
			}
			if (isUpdate) {
				 mealManager.getProfile().setAge(Integer.parseInt(ageTextField.getText()));
			} else {
				profile.setAge(Integer.parseInt(ageTextField.getText()));
			}

			if (weightTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter a weight", AlertType.ERROR);
				return;
			}
			if (isUpdate) {
				 mealManager.getProfile().setWeight(Double.parseDouble(weightTextField.getText()));
			} else {
				profile.setWeight(Double.parseDouble(weightTextField.getText()));
			}

			if (heightTextField.getText().isBlank()) {
				Alerts.showAlert("Invalid form", null, "You have to enter a height", AlertType.ERROR);
				return;
			}
			if (isUpdate) {
				 mealManager.getProfile().setHeight(Double.parseDouble(heightTextField.getText()));
			} else {
				profile.setHeight(Double.parseDouble(heightTextField.getText()));
			}
			
			if (isUpdate) {
				 mealManager.getProfile().setBiologicalGenre(genreChoiceBox.getValue().charAt(0));
			} else {
				profile.setBiologicalGenre(genreChoiceBox.getValue().charAt(0));
			}
			
			GymGoal gymGoal = null;
			switch (goalChoiceBox.getValue()) {
			case "Gain weight":
				gymGoal = GymGoal.GAIN_WEIGHT;
				break;
			case "Maintain weight":
				gymGoal = GymGoal.MAINTAIN_WEIGHT;
				break;
			case "Lose weight":
				gymGoal = GymGoal.LOSE_WEIGHT;
				break;
			default:
				break;
			}
			if (isUpdate) {
				mealManager.getProfile().setGymGoal(gymGoal);
			} else {
				profile.setGymGoal(gymGoal);
			}
			
			if (isUpdate) {
				profile = null;
				profileServices.updateProfile(mealManager.getProfile());
			} else {
				profile.setUpNutrients();
				profileServices.saveProfile(profile);
			}

			// closing this window
			Stage stage = (Stage) saveBtn.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);

			viewFactory.showChooseAccount();
		});

		choosePhotoButton.setOnAction(e -> {
			Stage stage = (Stage) nameTextField.getParent().getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File selectedFile = fileChooser.showOpenDialog(stage);
			profile.setProfileImgPath(selectedFile.getAbsolutePath());
		});
	}

	private void setUpTextFields() {
		Constraints.setTextFieldMaxLength(nameTextField, 40);
		Constraints.setTextFieldInteger(ageTextField);
		Constraints.setTextFieldDouble(weightTextField);
		Constraints.setTextFieldDouble(heightTextField);

		if (mealManager.getProfile() != null) {
			nameTextField.setText(mealManager.getProfile().getName());
			ageTextField.setText(mealManager.getProfile().getAge().toString());
			weightTextField.setText(Double.toString(mealManager.getProfile().getWeight()));
			heightTextField.setText(Double.toString(mealManager.getProfile().getHeight()));
		}
	}

	private void setUpChoiceBoxes() {
		if (mealManager.getProfile() != null) {
			genreChoiceBox.getItems().add("M");
			genreChoiceBox.getItems().add("F");
			int index = (Character.valueOf(mealManager.getProfile().getBiologicalGenre())
					.compareTo(Character.valueOf('M')) == 0) ? 0 : 1;
			genreChoiceBox.getSelectionModel().select(index);

			goalChoiceBox.getItems().add("Gain weight");
			goalChoiceBox.getItems().add("Maintain weight");
			goalChoiceBox.getItems().add("Lose weight");
			index = 0;
			switch (mealManager.getProfile().getGymGoal()) {
			case GAIN_WEIGHT:
				index = 0;
				break;
			case MAINTAIN_WEIGHT:
				index = 1;
				break;
			case LOSE_WEIGHT:
				index = 2;
				break;
			default:
				break;
			}
			goalChoiceBox.getSelectionModel().select(index);
			return;
		}

		goalChoiceBox.getItems().add("Gain weight");
		goalChoiceBox.getItems().add("Maintain weight");
		goalChoiceBox.getItems().add("Lose weight");
		goalChoiceBox.getSelectionModel().selectFirst();

		genreChoiceBox.getItems().add("M");
		genreChoiceBox.getItems().add("F");
		genreChoiceBox.getSelectionModel().selectFirst();
		goalChoiceBox.getSelectionModel().selectFirst();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpButtons();
		setUpTextFields();
		setUpChoiceBoxes();
	}
}
