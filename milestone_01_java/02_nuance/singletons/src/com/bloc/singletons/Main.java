package com.bloc.singletons;

import com.bloc.singletons.listeners.*;
import com.bloc.singletons.talkers.*;

public class Main extends Object {

	public static void main(String [] args) {
		// Instantiate some talkers and some listeners
		// Register listeners
		// Send messages!

		Pet dog = new Pet();
		Pet rabbit = new Pet();
		Child sally = new Child();

		PetOwner me = new PetOwner();

		Speakerphone speaker = Speakerphone.get();

		speaker.addListener(dog);
		speaker.addListener(rabbit);
		speaker.addListener(sally);

		speaker.shoutMessage(me);
		System.out.println("\n");
		speaker.shoutMessage(me, Pet.class);
	}
}
