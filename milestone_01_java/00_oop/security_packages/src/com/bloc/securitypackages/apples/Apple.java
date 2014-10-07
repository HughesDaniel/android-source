package com.bloc.securitypackages.apples;

import com.bloc.securitypackages.Fruit;
import com.bloc.securitypackages.Color;

public abstract class Apple extends Fruit {

	public Apple(String name, int i, Color color, double d) {
		super(name, i, color, d);
	}

	public abstract void bite();

}