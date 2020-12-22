package view;

import java.io.IOException;

import controller.AboutController;
import controller.AddFoodController;
import controller.BaseController;
import controller.ChooseAccountController;
import controller.EditGoalsController;
import controller.ListFoodsController;
import controller.MainWindowController;
import controller.RecordsController;
import controller.RegisterFoodController;
import controller.SetUpNewProfileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {
	public MealManager mealManager;

	public ViewFactory(MealManager mealManager) {
		this.mealManager = mealManager;
	}
//
	public void showMainWindow() {
		BaseController controller = new MainWindowController(mealManager, this, "MainWindow.fxml");
		Stage stage = initializeStage(controller);
		stage.setMaximized(true);
	}

	public void showChooseAccount() {
		BaseController controller = new ChooseAccountController(mealManager, this, "ChooseAccount.fxml");
		initializeStage(controller);
	}

	public void showSetUpNewProfile() {
		BaseController controller = new SetUpNewProfileController(mealManager, this, "SetUpNewProfile.fxml");
		Stage stage = initializeStage(controller);
		stage.setResizable(false);
	}

	public void showRecords() {
		BaseController controller = new RecordsController(mealManager, this, "Records.fxml");
		Stage stage = initializeStage(controller);
		stage.setMaximized(true);
	}

	public void showAddFood() {
		BaseController controller = new AddFoodController(mealManager, this, "AddFood.fxml");
		Stage stage = initializeStage(controller);
		stage.setResizable(false);
	}

	public void showEditGoals() {
		BaseController controller = new EditGoalsController(mealManager, this, "EditGoals.fxml");
		Stage stage = initializeStage(controller);
		stage.setResizable(false);
	}

	public void showListFoods(boolean selection) {
		BaseController controller = new ListFoodsController(mealManager, this, "ListFoods.fxml", selection);
		Stage stage = initializeStage(controller);
		stage.setMaximized(true);
	}

	public void showRegisterFood() {
		BaseController controller = new RegisterFoodController(mealManager, this, "RegisterFood.fxml");
		Stage stage = initializeStage(controller);
		stage.setResizable(false);
	}

	public void showAbout() {
		BaseController controller = new AboutController(mealManager, this, "About.fxml");
		Stage stage = initializeStage(controller);
		stage.setResizable(false);
	}

	private Stage initializeStage(BaseController controller) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setController(controller);
		fxmlLoader.setLocation(getClass().getResource(controller.getFxmlName()));
		Parent parent;
		try {
			parent = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		return stage;
	}

	public void closeStage(Stage stage) {
		stage.close();
	}

}
