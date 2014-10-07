package com.bloc.securitypackages.apples;

import com.bloc.securitypackages.colors.*;

public class Green extends Apple {

	public Green() {
		super(Green.class.getSimpleName(), 230, new LimeGreen(), 0.21d);
	}

	public void bite() {
		setWeight(getWeight() - 0.02d);
	}

}