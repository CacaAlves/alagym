package model.entities;

public class Food {
	
	protected Integer id = null;

	protected String name;
	
	protected Measure portion;

	protected Measure calories;

	protected Measure protein;

	protected Measure carb;

	protected Measure fat;

	protected Measure sugar;

	protected Measure fiber;

	protected Measure vitB12;

	protected Measure vitD;

	protected Measure vitA;

	protected Measure vitC;

	protected Measure vitE;

	protected Measure calcium;

	protected Measure iodine;

	protected Measure iron;

	protected Measure zinc;

	public Food() {
		this(null, null, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0);
	}
	
	public Food(Integer id, String name, double portion, double calories, double protein, double carb, double fat,
			double sugar, double fiber, double vitB12, double vitD, double vitA, double vitC, double vitE,
			double calcium, double iodine, double iron, double zinc) {
		this.id = id;
		this.name = name;
		this.portion = new Measure(portion, "g");
		this.calories = new Measure(calories, "kcal");
		this.protein = new Measure(protein, "g");
		this.carb = new Measure(carb, "g");
		this.fat = new Measure(fat, "g");
		this.sugar = new Measure(sugar, "g");
		this.fiber = new Measure(fiber, "g");
		this.vitB12 = new Measure(vitB12, "µg");
		this.vitD = new Measure(vitD, "µg");
		this.vitA = new Measure(vitA, "µg");
		this.vitC = new Measure(vitC, "mg");
		this.vitE = new Measure(vitE, "mg");
		this.calcium = new Measure(calcium, "mg");
		this.iodine = new Measure(iodine, "µg");
		this.iron = new Measure(iron, "mg");
		this.zinc = new Measure(zinc, "mg");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Measure getPortion() {
		return portion;
	}

	public void setPortion(Measure portion) {
		this.portion = portion;
	}

	public Measure getCalories() {
		return calories;
	}

	public void setCalories(Measure calories) {
		this.calories = calories;
	}

	public Measure getProtein() {
		return protein;
	}

	public void setProtein(Measure protein) {
		this.protein = protein;
	}

	public Measure getCarb() {
		return carb;
	}

	public void setCarb(Measure carb) {
		this.carb = carb;
	}

	public Measure getFat() {
		return fat;
	}

	public void setFat(Measure fat) {
		this.fat = fat;
	}

	public Measure getSugar() {
		return sugar;
	}

	public void setSugar(Measure sugar) {
		this.sugar = sugar;
	}

	public Measure getFiber() {
		return fiber;
	}

	public void setFiber(Measure fiber) {
		this.fiber = fiber;
	}

	public Measure getVitB12() {
		return vitB12;
	}

	public void setVitB12(Measure vitB12) {
		this.vitB12 = vitB12;
	}

	public Measure getVitD() {
		return vitD;
	}

	public void setVitD(Measure vitD) {
		this.vitD = vitD;
	}

	public Measure getVitA() {
		return vitA;
	}

	public void setVitA(Measure vitA) {
		this.vitA = vitA;
	}

	public Measure getVitC() {
		return vitC;
	}

	public void setVitC(Measure vitC) {
		this.vitC = vitC;
	}

	public Measure getVitE() {
		return vitE;
	}

	public void setVitE(Measure vitE) {
		this.vitE = vitE;
	}

	public Measure getCalcium() {
		return calcium;
	}

	public void setCalcium(Measure calcium) {
		this.calcium = calcium;
	}

	public Measure getIodine() {
		return iodine;
	}

	public void setIodine(Measure iodine) {
		this.iodine = iodine;
	}

	public Measure getIron() {
		return iron;
	}

	public void setIron(Measure iron) {
		this.iron = iron;
	}

	public Measure getZinc() {
		return zinc;
	}

	public void setZinc(Measure zinc) {
		this.zinc = zinc;
	}
	
}
