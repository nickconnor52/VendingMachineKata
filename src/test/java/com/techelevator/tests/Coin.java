package com.techelevator.tests;

public class Coin {

	private double weight;
	private double diameter;
	
	public Coin() {
		setWeight(0);
		setDiameter(0);
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getWeight() {
		return weight;
	}

	public double getDiameter() {
		return diameter;
	}

}
