package model.entities;

public class MealFood extends Food {

	private Integer MealFoodId;

	private MealType mealType;

	private Measure quantity;

	private Measure calculatedCalories;

	protected Measure calculatedProtein;

	protected Measure calculatedCarb;

	protected Measure calculatedFat;

	protected Measure calculatedSugar;

	protected Measure calculatedFiber;

	protected Measure calculatedVitB12;

	protected Measure calculatedVitD;

	protected Measure calculatedVitA;

	protected Measure calculatedVitC;

	protected Measure calculatedVitE;

	protected Measure calculatedCalcium;

	protected Measure calculatedIodine;

	protected Measure calculatedIron;

	protected Measure calculatedZinc;

	// not saved in the database, calculated variables

	public MealFood() {
		this(null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0);
	}

	public MealFood(Integer MealFoodId, Integer id, String name, double portion, double calories, double protein,
			double carb, double fat, double sugar, double fiber, double vitB12, double vitD, double vitA, double vitC,
			double vitE, double calcium, double iodine, double iron, double zinc, MealType mealType, double quantity) {
		super(id, name, portion, calories, protein, carb, fat, sugar, fiber, vitB12, vitD, vitA, vitC, vitE, calcium,
				iodine, iron, zinc);
		this.mealType = mealType;
		this.quantity = new Measure(quantity, "g");
		setUpCalculatedItems();
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public Measure getQuantity() {
		return quantity;
	}

	public void setQuantity(Measure quantity) {
		this.quantity = quantity;
	}

	public Integer getMealFoodId() {
		return MealFoodId;
	}

	public void setMealFoodId(Integer mealFoodId) {
		MealFoodId = mealFoodId;
	}

	public Measure getCalculatedCalories() {
		return calculatedCalories;
	}

	public void setCalculatedCalories(Measure calculatedCalories) {
		this.calculatedCalories = calculatedCalories;
	}
	
	public Measure getCalculatedProtein() {
		return calculatedProtein;
	}

	public void setCalculatedProtein(Measure calculatedProtein) {
		this.calculatedProtein = calculatedProtein;
	}

	public Measure getCalculatedCarb() {
		return calculatedCarb;
	}

	public void setCalculatedCarb(Measure calculatedCarb) {
		this.calculatedCarb = calculatedCarb;
	}

	public Measure getCalculatedFat() {
		return calculatedFat;
	}

	public void setCalculatedFat(Measure calculatedFat) {
		this.calculatedFat = calculatedFat;
	}

	public Measure getCalculatedSugar() {
		return calculatedSugar;
	}

	public void setCalculatedSugar(Measure calculatedSugar) {
		this.calculatedSugar = calculatedSugar;
	}

	public Measure getCalculatedFiber() {
		return calculatedFiber;
	}

	public void setCalculatedFiber(Measure calculatedFiber) {
		this.calculatedFiber = calculatedFiber;
	}

	public Measure getCalculatedVitB12() {
		return calculatedVitB12;
	}

	public void setCalculatedVitB12(Measure calculatedVitB12) {
		this.calculatedVitB12 = calculatedVitB12;
	}

	public Measure getCalculatedVitD() {
		return calculatedVitD;
	}

	public void setCalculatedVitD(Measure calculatedVitD) {
		this.calculatedVitD = calculatedVitD;
	}

	public Measure getCalculatedVitA() {
		return calculatedVitA;
	}

	public void setCalculatedVitA(Measure calculatedVitA) {
		this.calculatedVitA = calculatedVitA;
	}

	public Measure getCalculatedVitC() {
		return calculatedVitC;
	}

	public void setCalculatedVitC(Measure calculatedVitC) {
		this.calculatedVitC = calculatedVitC;
	}

	public Measure getCalculatedVitE() {
		return calculatedVitE;
	}

	public void setCalculatedVitE(Measure calculatedVitE) {
		this.calculatedVitE = calculatedVitE;
	}

	public Measure getCalculatedCalcium() {
		return calculatedCalcium;
	}

	public void setCalculatedCalcium(Measure calculatedCalcium) {
		this.calculatedCalcium = calculatedCalcium;
	}

	public Measure getCalculatedIodine() {
		return calculatedIodine;
	}

	public void setCalculatedIodine(Measure calculatedIodine) {
		this.calculatedIodine = calculatedIodine;
	}

	public Measure getCalculatedIron() {
		return calculatedIron;
	}

	public void setCalculatedIron(Measure calculatedIron) {
		this.calculatedIron = calculatedIron;
	}

	public Measure getCalculatedZinc() {
		return calculatedZinc;
	}

	public void setCalculatedZinc(Measure calculatedZinc) {
		this.calculatedZinc = calculatedZinc;
	}

	public void setUpCalculatedItems() {
		setUpCalculatedCalories();
		setUpCalculatedProtein();
		setUpCalculatedCarb();
		setUpCalculatedFat();
		setUpCalculatedSugar();
		setUpCalculatedFiber();
		setUpCalculatedVitB12();
		setUpCalculatedVitD();
		setUpCalculatedVitA();
		setUpCalculatedVitC();
		setUpCalculatedVitE();
		setUpCalculatedCalcium();
		setUpCalculatedIodine();
		setUpCalculatedIron();
		setUpCalculatedZinc();
	}

	private void setUpCalculatedCalories() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (calories.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedCalories = new Measure(calculation, "kcal");
	}

	private void setUpCalculatedProtein() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (protein.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedProtein = new Measure(calculation, "g");
	}

	private void setUpCalculatedCarb() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (carb.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedCarb = new Measure(calculation, "g");
	}

	private void setUpCalculatedFat() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (fat.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedFat = new Measure(calculation, "g");
	}

	private void setUpCalculatedSugar() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (sugar.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedSugar = new Measure(calculation, "g");
	}

	private void setUpCalculatedFiber() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (fiber.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedFiber = new Measure(calculation, "g");
	}

	private void setUpCalculatedVitB12() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (vitB12.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedVitB12 = new Measure(calculation, "μg");
	}

	private void setUpCalculatedVitD() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (vitD.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedVitD = new Measure(calculation, "μg");
	}

	private void setUpCalculatedVitA() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (vitA.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedVitA = new Measure(calculation, "μg");
	}

	private void setUpCalculatedVitC() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (vitC.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedVitC = new Measure(calculation, "mg");
	}

	private void setUpCalculatedVitE() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (vitE.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedVitE = new Measure(calculation, "mg");
	}

	private void setUpCalculatedCalcium() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (calcium.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedCalcium = new Measure(calculation, "mg");
	}

	private void setUpCalculatedIodine() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (iodine.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedIodine = new Measure(calculation, "μg");
	}

	private void setUpCalculatedIron() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (iron.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedIron = new Measure(calculation, "mg");
	}

	private void setUpCalculatedZinc() {
		if (quantity == null || portion == null) {
			return;
		}
		Double calculation = (zinc.getQuantity() * quantity.getQuantity()) / portion.getQuantity();
		if (Double.isInfinite(calculation) || Double.isNaN(calculation)) {
			calculation = 0.0;
		}
		calculatedZinc = new Measure(calculation, "mg");
	}

	public void setCalculatedItemsEqualsToItem() {
		calculatedCalories = calories;
		calculatedProtein = protein;
		calculatedCarb = carb;
		calculatedFat = fat;
		calculatedSugar = sugar;
		calculatedFiber = fiber;
		calculatedVitB12 = vitB12;
		calculatedVitD = vitD;
		calculatedVitA = vitA;
		calculatedVitC = vitC;
		calculatedVitE = vitE;
		calculatedCalcium = calcium;
		calculatedIodine = iodine;
		calculatedIron = iron;
		calculatedZinc = zinc;

	}

}
