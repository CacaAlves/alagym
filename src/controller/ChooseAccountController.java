package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entities.Profile;
import model.services.ProfileServices;
import view.MealManager;
import view.ViewFactory;
import view.utils.ChooseProfileButton;

public class ChooseAccountController extends BaseController implements Initializable {
	@FXML
	private VBox vBox;

	@FXML
	private Button addAccountBtn;
	
	private ProfileServices profileServices = new ProfileServices();

	public ChooseAccountController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		super(mealManager, viewFactory, fxmlName);
	}

	private void setUpVBox() {
		List<Profile> profiles = profileServices.getProfiles();
		
		if (profiles == null) return;

		for (Profile p : profiles) {
			ChooseProfileButton button = new ChooseProfileButton(p.getName());
			button.setOnAction(e -> {
				//closing this window
				Stage stage = (Stage) vBox.getParent().getScene().getWindow();
				viewFactory.closeStage(stage);

				
				//setting the clicked button profile as in current use
				mealManager.setProfile(p);
				viewFactory.showMainWindow();
			});
			vBox.getChildren().add(button);
		}
	}
	
	private void setUpButton() {
		addAccountBtn.setOnAction(e -> {
			//closing this window
			Stage stage = (Stage) vBox.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);
			
			viewFactory.showSetUpNewProfile();
		});
	}
	
	private void settingProfileToNull() {
		mealManager.setProfile(null);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		settingProfileToNull();
		setUpVBox();
		setUpButton();
	}
	
	

}
