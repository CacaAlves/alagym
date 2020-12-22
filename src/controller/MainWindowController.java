package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.entities.MealFood;
import model.entities.MealRecord;
import model.entities.Measure;
import model.services.MealFoodServices;
import model.services.MealRecordServices;
import model.services.ProfileServices;
import view.MealManager;
import view.ViewFactory;
import view.utils.Alerts;
import view.utils.Constraints;

public class MainWindowController extends BaseController implements Initializable {
	@FXML
	private ImageView profileImgImageView;

	@FXML
	private Text profileNameText;

	@FXML
	private VBox profileVBox;

	@FXML
	private Button recordsBtn;

	@FXML
	private Button deleteAccBtn;

	@FXML
	private MenuItem addFoodMenuItem;

	@FXML
	private MenuItem editGoalsMenuItem;

	@FXML
	private MenuItem registerFoodMenuItem;

	@FXML
	private MenuItem registerProfileFoodMenuItem;

	@FXML
	private MenuItem listFoodsProfileMenuItem;

	@FXML
	private MenuItem aboutMenuItem;

	@FXML
	private Button breakfastBtn;

	@FXML
	private Button lunchBtn;

	@FXML
	private Button dinnerBtn;

	@FXML
	private Button snacksBtn;

	@FXML
	private Button totalBtn;

	@FXML
	private Button changeBtn;

	@FXML
	private Text currentlySelectedCol;

	@FXML
	private TextField currentlySelectedColGoal;

	@FXML
	private HBox nutrientsHBox;

	@FXML
	private TableView<MealFood> macroTableView;

	@FXML
	private TableView<MealFood> microTableView;

	@FXML
	private TableColumn<MealFood, String> macroFoodTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> macroPortionTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> caloriesTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> proteinTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> carbTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> fatTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> fiberTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> sugarTableColumn;

	@FXML
	private TableColumn<MealFood, String> microFoodTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> microPortionTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> vitB12TableColumn;

	@FXML
	private TableColumn<MealFood, Measure> vitDTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> vitATableColumn;

	@FXML
	private TableColumn<MealFood, Measure> vitCTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> vitETableColumn;

	@FXML
	private TableColumn<MealFood, Measure> calciumTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> iodineTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> ironTableColumn;

	@FXML
	private TableColumn<MealFood, Measure> zincTableColumn;

	@FXML
	private Button macroBtn;

	@FXML
	private Button microBtn;

	private MenuItem macroDeleteFood = new MenuItem("remove");

	private MenuItem microDeleteFood = new MenuItem("remove");

	private MealFood currentlySelectedFood = null;

	private final String MACRO = "MACRO";
	private final String MICRO = "MICRO";

	private String currentTableView = null;

	private String currentlySelectedNutrient = null;

	private MealRecord todayMealRecord = new MealRecord();

	private ProfileServices profileServices = new ProfileServices();

	private MealRecordServices mealRecordServices = new MealRecordServices();

	private MealFoodServices mealFoodServices = new MealFoodServices();

	public MainWindowController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		super(mealManager, viewFactory, fxmlName);
	}

	private void setUpButtons() {
		macroBtn.setOnAction(e -> {
			setCurrentTableViewToMacro();
		});
		microBtn.setOnAction(e -> {
			setCurrentTableViewToMicro();
		});

		breakfastBtn.setOnAction(e -> {
			todayMealRecord = mealRecordServices.getMealRecordFromToday(mealManager.getProfile());
				setTableViewsWithTodaysMeal(todayMealRecord.getBreakfast(), false);
		});

		lunchBtn.setOnAction(e -> {
			todayMealRecord = mealRecordServices.getMealRecordFromToday(mealManager.getProfile());
				setTableViewsWithTodaysMeal(todayMealRecord.getLunch(), false);
		});

		dinnerBtn.setOnAction(e -> {
			todayMealRecord = mealRecordServices.getMealRecordFromToday(mealManager.getProfile());
				setTableViewsWithTodaysMeal(todayMealRecord.getDinner(), false);
		});

		snacksBtn.setOnAction(e -> {
			todayMealRecord = mealRecordServices.getMealRecordFromToday(mealManager.getProfile());
				setTableViewsWithTodaysMeal(todayMealRecord.getSnacks(), false);
		});

		totalBtn.setOnAction(e -> {
			todayMealRecord = mealRecordServices.getMealRecordFromToday(mealManager.getProfile());
				setTableViewsWithTodaysMeal(todayMealRecord.getFoods(), true);
		});

		changeBtn.setOnAction(e -> {
			if (currentlySelectedCol.getText().isBlank())
				return;
			Double goal = Double.parseDouble(currentlySelectedColGoal.getText());

			switch (currentlySelectedNutrient) {
			case "Calories":
				mealManager.getProfile().getCalories().setQuantity(goal);
				break;
			case "Protein":
				mealManager.getProfile().getProtein().setQuantity(goal);
				break;
			case "Carb":
				mealManager.getProfile().getCarb().setQuantity(goal);
				break;
			case "Fat":
				mealManager.getProfile().getFat().setQuantity(goal);
				break;
			case "Fiber":
				mealManager.getProfile().getFiber().setQuantity(goal);
				break;
			case "Sugar":
				mealManager.getProfile().getSugar().setQuantity(goal);
				break;
			case "Vit B12":
				mealManager.getProfile().getVitB12().setQuantity(goal);
				break;
			case "Vit D":
				mealManager.getProfile().getVitD().setQuantity(goal);
				break;
			case "Vit A":
				mealManager.getProfile().getVitA().setQuantity(goal);
				break;
			case "Vit C":
				mealManager.getProfile().getVitC().setQuantity(goal);
				break;
			case "Vit E":
				mealManager.getProfile().getVitE().setQuantity(goal);
				break;
			case "Calcium":
				mealManager.getProfile().getCalcium().setQuantity(goal);
				break;
			case "Iodine":
				mealManager.getProfile().getIodine().setQuantity(goal);
				break;
			case "Iron":
				mealManager.getProfile().getIron().setQuantity(goal);
				break;
			case "Zinc":
				mealManager.getProfile().getZinc().setQuantity(goal);
				break;
			default:

			}

			profileServices.updateProfile(mealManager.getProfile());
		});

		recordsBtn.setOnAction(e -> {
			viewFactory.showRecords();
		});

		deleteAccBtn.setOnAction(e -> {
			Optional<ButtonType> optional = Alerts.showConfirmation("Confirmation", "Do you really want to delete your account ?"); 
			boolean confirmation = optional.get().getText().equals("OK");
			if (confirmation) {
				
			
			profileServices.deleteAccount(mealManager.getProfile());

			// closing this window
			Stage stage = (Stage) deleteAccBtn.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);

			// opening the choose account window
			viewFactory.showChooseAccount();
			}
		});

	}

	private void setCurrentTableViewToMacro() {
		if (currentTableView == MACRO)
			return;
		nutrientsHBox.getChildren().clear();
		nutrientsHBox.getChildren().add(macroTableView);
		currentTableView = MACRO;
	}

	private void setCurrentTableViewToMicro() {
		if (currentTableView == MICRO)
			return;
		nutrientsHBox.getChildren().clear();
		nutrientsHBox.getChildren().add(microTableView);
		currentTableView = MICRO;
	}

	private void setUpTableView() {
		macroTableView.getSelectionModel().setCellSelectionEnabled(true);
		microTableView.getSelectionModel().setCellSelectionEnabled(true);

		macroFoodTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, String>("name"));
		macroPortionTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("quantity"));
		caloriesTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedCalories"));
		proteinTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedProtein"));
		carbTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedCarb"));
		fatTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedFat"));
		fiberTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedFiber"));
		sugarTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedSugar"));
		microFoodTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, String>("name"));
		microPortionTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("quantity"));
		vitB12TableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedVitB12"));
		vitDTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedVitD"));
		vitATableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedVitA"));
		vitCTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedVitC"));
		vitETableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedVitE"));
		calciumTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedCalcium"));
		iodineTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedIodine"));
		ironTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedIron"));
		zincTableColumn.setCellValueFactory(new PropertyValueFactory<MealFood, Measure>("calculatedZinc"));

		final ObservableList<TablePosition> macroSelectedCells = macroTableView.getSelectionModel().getSelectedCells();
		macroSelectedCells.addListener(new ListChangeListener<TablePosition>() {
			@Override
			public void onChanged(Change change) {
				for (TablePosition pos : macroSelectedCells) {
					setCurrentlySelectedRowAndCol(macroTableView.getSelectionModel().getSelectedItem(),
							pos.getTableColumn().getText());

				}
			};
		});

		final ObservableList<TablePosition> microSelectedCells = microTableView.getSelectionModel().getSelectedCells();
		microSelectedCells.addListener(new ListChangeListener<TablePosition>() {
			@Override
			public void onChanged(Change change) {
				for (TablePosition pos : microSelectedCells) {
					setCurrentlySelectedRowAndCol(microTableView.getSelectionModel().getSelectedItem(),
							pos.getTableColumn().getText());
				}
			};
		});

		macroTableView.setContextMenu(new ContextMenu(macroDeleteFood));
		microTableView.setContextMenu(new ContextMenu(microDeleteFood));

		todayMealRecord = mealRecordServices.getMealRecordFromToday(mealManager.getProfile());

		setTableViewsWithTodaysMeal(todayMealRecord.getBreakfast(), false);
	}

	private void setTableViewsWithTodaysMeal(List<MealFood> list, boolean noFood) {
		if (list == null) {
			noFood = true;
		} else if (list.size() == 0) {
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
			goal.getCalories().setQuantity(mealManager.getProfile().getCalories().getQuantity());
			goal.getProtein().setQuantity(mealManager.getProfile().getProtein().getQuantity());
			goal.getCarb().setQuantity(mealManager.getProfile().getCarb().getQuantity());
			goal.getFat().setQuantity(mealManager.getProfile().getFat().getQuantity());
			goal.getFiber().setQuantity(mealManager.getProfile().getFiber().getQuantity());
			goal.getSugar().setQuantity(mealManager.getProfile().getSugar().getQuantity());
			goal.getVitB12().setQuantity(mealManager.getProfile().getVitB12().getQuantity());
			goal.getVitD().setQuantity(mealManager.getProfile().getVitD().getQuantity());
			goal.getVitA().setQuantity(mealManager.getProfile().getVitA().getQuantity());
			goal.getVitC().setQuantity(mealManager.getProfile().getVitC().getQuantity());
			goal.getVitE().setQuantity(mealManager.getProfile().getVitE().getQuantity());
			goal.getCalcium().setQuantity(mealManager.getProfile().getCalcium().getQuantity());
			goal.getIodine().setQuantity(mealManager.getProfile().getIodine().getQuantity());
			goal.getIron().setQuantity(mealManager.getProfile().getIron().getQuantity());
			goal.getZinc().setQuantity(mealManager.getProfile().getZinc().getQuantity());

		macroTableView.getItems().clear();
		microTableView.getItems().clear();
		if (!noFood) {

			for (MealFood f : list) {
				macroTableView.getItems().add(f);
				microTableView.getItems().add(f);
			}
		}
		
		
		total.setCalculatedItemsEqualsToItem();
		//i don't want to calculate the item for goal because there is no portion in goal
		goal.setCalculatedItemsEqualsToItem();
		macroTableView.getItems().add(total);
		macroTableView.getItems().add(goal);
		microTableView.getItems().add(total);
		microTableView.getItems().add(goal);
	}

	private void setUpContextMenus() {
		macroDeleteFood.setOnAction(e -> {
			mealFoodServices.deleteFoodFromRecords(currentlySelectedFood);
			setTableViewsWithTodaysMeal(
					mealRecordServices.getMealRecordFromToday(mealManager.getProfile()).getBreakfast(), false);
		});

		microDeleteFood.setOnAction(e -> {
			mealFoodServices.deleteFoodFromRecords(currentlySelectedFood);
			setTableViewsWithTodaysMeal(
					mealRecordServices.getMealRecordFromToday(mealManager.getProfile()).getBreakfast(), false);
		});

	}

	private void setCurrentlySelectedRowAndCol(MealFood food, String col) {
		currentlySelectedFood = food;
		if (!col.equals("Food") && !col.equals("Portion")) {
			currentlySelectedCol.setText(col);
		}
		switch (col) {
		case "Calories":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getCalories().getQuantity()));
			break;
		case "Protein":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getProtein().getQuantity()));
			break;
		case "Carb":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getCarb().getQuantity()));
			break;
		case "Fat":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getFat().getQuantity()));
			break;
		case "Fiber":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getFiber().getQuantity()));
			break;
		case "Sugar":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getSugar().getQuantity()));
			break;
		case "Vit B12":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getVitB12().getQuantity()));
			break;
		case "Vit D":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getVitD().getQuantity()));
			break;
		case "Vit A":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getVitA().getQuantity()));
			break;
		case "Vit C":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getVitC().getQuantity()));
			break;
		case "Vit E":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getVitE().getQuantity()));
			break;
		case "Calcium":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getCalcium().getQuantity()));
			break;
		case "Iodine":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getIodine().getQuantity()));
			break;
		case "Iron":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getIron().getQuantity()));
			break;
		case "Zinc":
			currentlySelectedNutrient = col;
			currentlySelectedColGoal.setText(Double.toString(mealManager.getProfile().getZinc().getQuantity()));
			break;
		default:

		}

	}

	private void setUpTextFields() {
		Constraints.setTextFieldDouble(currentlySelectedColGoal);
		Constraints.setTextFieldMaxLength(currentlySelectedColGoal, 20);
	}

	private void setUpProfileImg() {
		if (mealManager.getProfile().getProfileImgPath() == null) {
			try {
				Image image = new Image(new FileInputStream(System.getProperty("user.dir") + "/img/duck.jpg"));
				profileImgImageView.setImage(image);
			} catch (FileNotFoundException e) {
				Alerts.showAlert("Error with the image", null, e.toString(), AlertType.ERROR);
				mealManager.getProfile().setProfileImgPath(null);
				System.out.println(e);
			}
		} else {
			try {
				Image image = new Image(new FileInputStream(mealManager.getProfile().getProfileImgPath()));
				profileImgImageView.setImage(image);
			} catch (FileNotFoundException e) {
				Alerts.showAlert("Error with the image", null, e.toString(), AlertType.ERROR);
				mealManager.getProfile().setProfileImgPath(null);
			}
		}

		profileImgImageView.setOnMouseClicked(e -> {
			// closing this window
			Stage stage = (Stage) profileImgImageView.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);

			viewFactory.showSetUpNewProfile();
		});

	}

	private void setUpTexts() {
		profileNameText.setText(mealManager.getProfile().getName());
	}

	private void setUpMenuItems() {
		listFoodsProfileMenuItem.setOnAction(e -> {
			viewFactory.showListFoods(false);
		});

		addFoodMenuItem.setOnAction(e -> {
			mealManager.setMainWindowMacroTableView(macroTableView);
			mealManager.setMainWindowMicroTableView(microTableView);
			viewFactory.showAddFood();
		});

		editGoalsMenuItem.setOnAction(e -> {
			viewFactory.showEditGoals();
		});

		registerProfileFoodMenuItem.setOnAction(e -> {
			// setting the profile to null, otherwise it we just update this profile
			mealManager.setProfile(null);

			// close this window
			Stage stage = (Stage) breakfastBtn.getParent().getScene().getWindow();
			viewFactory.closeStage(stage);

			viewFactory.showSetUpNewProfile();
		});

		registerFoodMenuItem.setOnAction(e -> {
			viewFactory.showRegisterFood();
		});

		aboutMenuItem.setOnAction(e -> {
			viewFactory.showAbout();
		});
	}

	private void setUpTodayMealRecord() {
		if (mealRecordServices.getMealRecordFromToday(mealManager.getProfile()) == null) {
			mealRecordServices.addMealRecordFromToday(mealManager.getProfile());
		}
	}

	// in the table view, don't forget the delete option to a line

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTodayMealRecord();
		setCurrentTableViewToMacro();
		setUpButtons();
		setUpTableView();
		setUpContextMenus();
		setUpTextFields();
		setUpProfileImg();
		setUpTexts();
		setUpMenuItems();
	}

}
