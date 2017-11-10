package com.techelevator.coin;

public class Coin {

	private double weight;
	private double diameter;
	
	public Coin(double coinWeight, double coinDiameter) {
		weight = coinWeight;
		diameter = coinDiameter;
	}

	public double getWeight() {
		return weight;
	}

	public double getDiameter() {
		return diameter;
	}

}
