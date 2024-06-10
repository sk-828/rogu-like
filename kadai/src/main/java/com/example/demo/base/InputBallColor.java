package com.example.demo.base;

import lombok.Data;

@Data
public class InputBallColor {
	private String ball1;
	private String ball2;
	private String ball3;
	private String ball4;

	public int[] returnIntBallColors(){
		int[] balls = { Integer.parseInt(getBall1()), Integer.parseInt(getBall2()),
				Integer.parseInt(getBall3()), Integer.parseInt(getBall4()) };
		return balls;
	}
}
