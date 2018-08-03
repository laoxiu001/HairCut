package com.pidstudiodemo.view.model;

public class IncomeAndSpending {

	private double sum;
	private int month;
	private double count;
	

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public IncomeAndSpending(double sum, int month) {
		super();
		this.sum = sum;
		this.month = month;
	}
	public IncomeAndSpending(double sum, int month, double count) {
		super();
		this.sum = sum;
		this.month = month;
		this.count = count;
	}

	
}
