package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.entities.MealFood;
import model.entities.MealRecord;
import model.entities.Measure;
import model.services.MealRecordServices;
import view.MealManager;
import view.ViewFactory;
import view.utils.Alerts;

public class RecordsController extends BaseController implements Initializable {

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
	private Button macroBtn;

	@FXML
	private Button microBtn;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button deleteAllRecordsBtn;

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

	private String currentTableView = null;

	private MealRecord mealRecord = null;

	private final String MACRO = "MACRO";

	private final String MICRO = "MICRO";

	private MealRecordServices mealRecordServices = new MealRecordServices();

	public RecordsController(MealManager mealManager, ViewFactory viewFactory, String fxmlName) {
		super(mealManager, viewFactory, fxmlName);
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

		setTableViewsWithMeal(mealRecordServices.getMealRecordFromToday(mealManager.getProfile()).getBreakfast(),
				false);
	}

	private void setUpButtons() {
		macroBtn.setOnAction(e -> {
			setCurrentTableViewToMacro();
		});
		microBtn.setOnAction(e -> {
			setCurrentTableViewToMicro();
		});

		breakfastBtn.setOnAction(e -> {
			mealRecord = mealRecordServices.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue());
			if (mealRecord != null)
				setTableViewsWithMeal(mealRecord.getBreakfast(), false);
		});

		lunchBtn.setOnAction(e -> {
			mealRecord = mealRecordServices.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue());

			if (mealRecord != null)
				setTableViewsWithMeal(mealRecord.getLunch(), false);
		});

		dinnerBtn.setOnAction(e -> {
			mealRecord = mealRecordServices.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue());
			if (mealRecord != null)
				setTableViewsWithMeal(mealRecord.getDinner(), false);
		});

		snacksBtn.setOnAction(e -> {
			mealRecord = mealRecordServices.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue());
			if (mealRecord != null)
				setTableViewsWithMeal(mealRecord.getSnacks(), false);
		});

		totalBtn.setOnAction(e -> {
			mealRecord = mealRecordServices.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue());
			if (mealRecord != null) {
				setTableViewsWithMeal(mealRecord.getFoods(), true);

			}
		});

		deleteAllRecordsBtn.setOnAction(e -> {
			Optional<ButtonType> optional = Alerts.showConfirmation("Confirmation",
					"Do you really want to delete all your records ?");
			boolean confirmation = optional.get().getText().equals("OK");

			// delete all records (except today's) from database
			if (confirmation) {
				mealRecord = mealRecordServices.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue());
				if (mealRecord != null)
					setTableViewsWithMeal(mealRecord.getBreakfast(), false);

				List<MealRecord> records = mealRecordServices.getAllRecords();

				for (MealRecord r : records) {
					if (mealRecordServices.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue()) != null) {
						if (r.getId() != mealRecordServices
								.getMealRecordFrom(mealManager.getProfile(), datePicker.getValue()).getId())
							mealRecordServices.deleteMealRecord(r);
					} else {
						mealRecordServices.deleteMealRecord(r);
					}
				}

				datePicker.setValue(LocalDate.now());
			}
		});
	}

	private void setTableViewsWithMeal(List<MealFood> list, boolean noFood) {
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
		// i don't want to calculate the item for goal because there is no portion in
		// goal
		goal.setCalculatedItemsEqualsToItem();
		macroTableView.getItems().add(total);
		macroTableView.getItems().add(goal);
		microTableView.getItems().add(total);
		microTableView.getItems().add(goal);
	}

	private void setUpDatePicker() {
		datePicker.setValue(LocalDate.now());
		datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			mealRecord = mealRecordServices.getMealRecordFrom(mealManager.getProfile(), newValue);
			if (mealRecord == null) {
				macroTableView.getItems().clear();
				microTableView.getItems().clear();
			} else
				setTableViewsWithMeal(mealRecord.getBreakfast(), false);
			breakfastBtn.requestFocus();
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setCurrentTableViewToMacro();
		setUpButtons();
		setUpDatePicker();
		setUpTableView();
	}

}
