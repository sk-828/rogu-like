package com.example.demo.base;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ReturnColorsBalls {

	private Balls[] balls = new Balls[10];
	private int[] answer = { 0, 0, 0, 0 };
	private int count = 0;
	private int score;
	private String[] colors = { "null", "ball0000ff", "ball1e90ff", "ball6495ed", "ball00bfff", "ballee82ee",
			"ballf8f8ff" };
	private String[] colorsAnmika = { "null", "ballffffff", "balleffbfb", "balleffbf8", "balleffbf2", "ballf5fbef",
			"ballfbefef" };
	private String url;
	private String[] urls;
	private ArrayList<String> itemsList;

	ReturnColorsBalls() {
		reset();
		itemsList = new ArrayList<>();
	}

	void setColors(InputBallColor balls, int hit, int blow) {
		if (count < 10) {
			int[] input = { Integer.parseInt(balls.getBall1()), Integer.parseInt(balls.getBall2()),
					Integer.parseInt(balls.getBall3()), Integer.parseInt(balls.getBall4()), hit, blow };
			this.balls[count].setBalls(input);
			count++;
		}

	}

	public void reset() {
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new Balls();
		}
		count = 0;
	}

	public void addItems(String item) {
		itemsList.add(item);
	}
}
