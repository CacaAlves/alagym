package model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Measure {
	private double quantity;

	private String measureUnit;

	public Measure() {}

	public Measure(double quantity, String measureUnit) {
		BigDecimal bd = new BigDecimal(quantity).setScale(2, RoundingMode.HALF_EVEN);
		this.quantity = bd.doubleValue();
		this.measureUnit = measureUnit;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		BigDecimal bd = new BigDecimal(quantity).setScale(2, RoundingMode.HALF_EVEN);
		this.quantity = bd.doubleValue();
	}

	public String getMeasurementUnit() {
		return measureUnit;
	}

	public void setMeasurementUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	@Override
	public String toString() {
		return quantity + measureUnit;
	}
}
