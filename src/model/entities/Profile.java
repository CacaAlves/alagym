package model.entities;

public class Profile {
	private Integer id = null;
	
	private Integer age;

	private char biologicalGenre;

	private String name;

	private double weight;

	private double height;

	private String profileImgPath = null;

	private GymGoal gymGoal;

	private Measure calories;

	private Measure protein;

	private Measure carb;

	private Measure fat;

	private Measure sugar;

	private Measure fiber;

	private Measure vitB12;

	private Measure vitD;

	private Measure vitA;

	private Measure vitC;

	private Measure vitE;

	private Measure calcium;

	private Measure iodine;

	private Measure iron;

	private Measure zinc;
	
	public Profile() {
		this(null, null, 'M', null, 0, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public Profile(Integer id, Integer age, char biologicalGenre, String name, double weight, double height,
			String profileImgPath, GymGoal gymGoal, double calories, double protein, double carb, double fat,
			double sugar, double fiber, double vitB12, double vitD, double vitA, double vitC, double vitE,
			double calcium, double iodine, double iron, double zinc) {
		this.id = id;
		this.age = age;
		this.biologicalGenre = biologicalGenre;
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.profileImgPath = profileImgPath;
		this.gymGoal = gymGoal;
		this.calories = new Measure(calories, "g");
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public char getBiologicalGenre() {
		return biologicalGenre;
	}

	public void setBiologicalGenre(char biologicalGenre) {
		this.biologicalGenre = biologicalGenre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getProfileImgPath() {
		return profileImgPath;
	}

	public void setProfileImgPath(String profileImgPath) {
		this.profileImgPath = profileImgPath;
	}

	public GymGoal getGymGoal() {
		return gymGoal;
	}

	public void setGymGoal(GymGoal gymGoal) {
		this.gymGoal = gymGoal;
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
	
	public void setUpNutrients() {
		// macros
		switch (gymGoal) {
		case GAIN_WEIGHT:
			double TMB;
			if (biologicalGenre == 'M') {
				TMB = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
			} else {
				TMB = 665 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
			}
			calories.setQuantity(TMB * 1.55);

			protein.setQuantity(2 * weight);

			fat.setQuantity(1 * weight);

			carb.setQuantity(5 * weight);

			break;
		case MAINTAIN_WEIGHT:
			calories.setQuantity(2300.0);

			protein.setQuantity(2 * weight);

			fat.setQuantity(1 * weight);

			carb.setQuantity(5 * weight);
			
			break;
		case LOSE_WEIGHT:
			calories.setQuantity(1800.0);

			protein.setQuantity(2 * weight);

			fat.setQuantity(1 * weight);

			carb.setQuantity(2 * weight);
			
			break;
		default:
			break;
		}
		
		if (age < 18) {
			fiber.setQuantity(25.2);
		}
		else if (biologicalGenre == 'M') {
			fiber.setQuantity(34);
		} else {
			fiber.setQuantity(28);
		}
		
		sugar.setQuantity(0.5 * weight);
		
		//micros
		vitB12.setQuantity(5.0);
		vitD.setQuantity(50.0);
		vitA.setQuantity(900.0);
		vitC.setQuantity(90.0);
		vitE.setQuantity(15.0);
		calcium.setQuantity(1000.0);
		iodine.setQuantity(150.0);
		iron.setQuantity(8.0);
		zinc.setQuantity(11.0);
	}
}
