package controller;

import view.MealManager;
import view.ViewFactory;

public class BaseController {
	protected MealManager mealManager;
	protected ViewFactory viewFactory;
	protected String fxmlName;

	public BaseController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		this.mealManager = mealManager;
		this.viewFactory = viewFactory;
		this.fxmlName = fxmlName;
	}

	public String getFxmlName() {
		return this.fxmlName;
	}
}
