package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import view.MealManager;
import view.ViewFactory;

public class ListFoodsController extends BaseController implements Initializable {

	private Boolean selection;
	
	public ListFoodsController(MealManager mealManager, ViewFactory viewFactory, String fxmlName, Boolean selection) {
		super(mealManager, viewFactory, fxmlName);
		this.selection = selection;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
