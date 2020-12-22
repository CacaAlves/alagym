package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.entities.Food;
import model.entities.Measure;
import model.services.FoodServices;
import view.MealManager;
import view.ViewFactory;

public class ListFoodsController extends BaseController implements Initializable {

	@FXML
	private Button macroBtn;

	@FXML
	private Button microBtn;

	@FXML
	private HBox nutrientsHBox;

	@FXML
	private TableView<Food> macroTableView;

	@FXML
	private TableView<Food> microTableView;

	@FXML
	private TableColumn<Food, String> macroFoodTableColumn;
	
	@FXML
	private TableColumn<Food, Measure> macroPortionTableColumn;

	@FXML
	private TableColumn<Food, Measure> caloriesTableColumn;

	@FXML
	private TableColumn<Food, Measure> proteinTableColumn;

	@FXML
	private TableColumn<Food, Measure> carbTableColumn;

	@FXML
	private TableColumn<Food, Measure> fatTableColumn;

	@FXML
	private TableColumn<Food, Measure> fiberTableColumn;

	@FXML
	private TableColumn<Food, Measure> sugarTableColumn;

	@FXML
	private TableColumn<Food, String> microFoodTableColumn;
	
	@FXML
	private TableColumn<Food, Measure> microPortionTableColumn;

	@FXML
	private TableColumn<Food, Measure> vitB12TableColumn;

	@FXML
	private TableColumn<Food, Measure> vitDTableColumn;

	@FXML
	private TableColumn<Food, Measure> vitATableColumn;

	@FXML
	private TableColumn<Food, Measure> vitCTableColumn;

	@FXML
	private TableColumn<Food, Measure> vitETableColumn;

	@FXML
	private TableColumn<Food, Measure> calciumTableColumn;

	@FXML
	private TableColumn<Food, Measure> iodineTableColumn;

	@FXML
	private TableColumn<Food, Measure> ironTableColumn;

	@FXML
	private TableColumn<Food, Measure> zincTableColumn;

	private MenuItem macroDeleteFood = new MenuItem("delete");

	private MenuItem microDeleteFood = new MenuItem("delete");
	
	private String currentTableView = null;
	
	private Food selectedFood = null;
	
	private Boolean selection;

	private FoodServices foodServices = new FoodServices();
	
	public ListFoodsController(MealManager mealManager, ViewFactory viewFactory, String fxmlName, Boolean selection) {
		super(mealManager, viewFactory, fxmlName);
		this.selection = selection;
	}

	private void setUpTableViews() {
		macroTableView.getSelectionModel().setCellSelectionEnabled(true);
		microTableView.getSelectionModel().setCellSelectionEnabled(true);

		macroFoodTableColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
		macroPortionTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("portion"));
		caloriesTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("calories"));
		proteinTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("protein"));
		carbTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("carb"));
		fatTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("fat"));
		fiberTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("fiber"));
		sugarTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("sugar"));
		microFoodTableColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
		microPortionTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("portion"));
		vitB12TableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("vitB12"));
		vitDTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("vitD"));
		vitATableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("vitA"));
		vitCTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("vitC"));
		vitETableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("vitE"));
		calciumTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("calcium"));
		iodineTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("iodine"));
		ironTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("iron"));
		zincTableColumn.setCellValueFactory(new PropertyValueFactory<Food, Measure>("zinc"));

		setTableViewsWithFoods(foodServices.getAllFoods());

		final ObservableList<TablePosition> macroSelectedCells = macroTableView.getSelectionModel().getSelectedCells();
		macroSelectedCells.addListener(new ListChangeListener<TablePosition>() {
			@Override
			public void onChanged(Change change) {
				for (TablePosition pos : macroSelectedCells) {
					setCurrentlySelectedRow(macroTableView.getSelectionModel().getSelectedItem());
					if (selection) {
						mealManager.setListFoodsSelectedFood(selectedFood);
						Stage stage = (Stage) macroTableView.getParent().getScene().getWindow();
						viewFactory.closeStage(stage);
					}
				}
			};
		});

		final ObservableList<TablePosition> microSelectedCells = microTableView.getSelectionModel().getSelectedCells();
		microSelectedCells.addListener(new ListChangeListener<TablePosition>() {
			@Override
			public void onChanged(Change change) {
				for (TablePosition pos : microSelectedCells) {
					setCurrentlySelectedRow(microTableView.getSelectionModel().getSelectedItem());
					if (selection) {
						mealManager.setListFoodsSelectedFood(selectedFood);
						Stage stage = (Stage) microTableView.getParent().getScene().getWindow();
						viewFactory.closeStage(stage);
					}
				}
			};
		});

		macroTableView.setContextMenu(new ContextMenu(macroDeleteFood));
		microTableView.setContextMenu(new ContextMenu(microDeleteFood));

	}

	private void setUpContextMenus() {
		macroDeleteFood.setOnAction(e -> {
			foodServices.deleteFood(selectedFood);
			setTableViewsWithFoods(foodServices.getAllFoods());
		});

		microDeleteFood.setOnAction(e -> {
			foodServices.deleteFood(selectedFood);
			setTableViewsWithFoods(foodServices.getAllFoods());
		});

	}
	
	private void setCurrentlySelectedRow(Food food) {
		selectedFood = food;
	}

	private void setCurrentTableViewToMacro() {
		if (currentTableView == "MACRO")
			return;
		nutrientsHBox.getChildren().clear();
		nutrientsHBox.getChildren().add(macroTableView);
		currentTableView = "MACRO";
	}

	private void setCurrentTableViewToMicro() {
		if (currentTableView == "MICRO")
			return;
		nutrientsHBox.getChildren().clear();
		nutrientsHBox.getChildren().add(microTableView);
		currentTableView = "MICRO";
	}

	private void setUpButtons() {
		microBtn.setOnAction(e -> {
			setCurrentTableViewToMicro();
		});

		macroBtn.setOnAction(e -> {
			setCurrentTableViewToMacro();
		});
	}

	private void setTableViewsWithFoods(List<Food> foods) {
		macroTableView.getItems().clear();
		microTableView.getItems().clear();
		
		if (foods == null) 
			return;

		if (foods.size() == 0) 
			return;

		for (Food food : foods) {
			macroTableView.getItems().add(food);
			microTableView.getItems().add(food);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTableViews();
		setUpContextMenus();
		setCurrentTableViewToMacro();
		setUpButtons();
	}

}
