package view.utils;

import javafx.scene.control.Button;

public class ChooseProfileButton extends Button {
	public ChooseProfileButton(String profileName){
        getStylesheets().add(getClass().getResource("ChooseProfileButton.css").toExternalForm());
		getStyleClass().add("chooseProfileButton");
        this.setMinWidth(150);
		this.setMinHeight(50);
		this.setText(profileName);
	}
}
