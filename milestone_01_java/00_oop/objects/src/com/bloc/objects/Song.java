package com.bloc.objects;

class Song extends Object {
	// The ensemble which produced it
	Ensemble mEnsemble;
	// Title of the song
	String mTitle;
	// The year it was released
	int mYearReleased;

	/*
	 * Basic Constructor
	 * Side-effects: Assigns some default ensemble, title and
	 *				 and year of your choosing
	 */
	Song() {
		Artist[] artists = {new Artist("Dan", "Auerbach"),
							new Artist("Patrick", "Carney")};
		Ensemble theBlackKeys = new Ensemble("The Black Keys", artists);
		//why wont below work?
		//this(theBlackKeys, "I Got Mine", 2008);
		mEnsemble = theBlackKeys;
		mTitle = "I Got Mine";
		mYearReleased = 2008;
	}

	/*
	 * Partial Constructor
	 * Side-effects: Sets the year of release to 0
	 * @param ensemble the ensemble responsible
	 * @param title the song title
	 */
	Song(Ensemble ensemble, String title) {
		this(ensemble, title, 0);
	}

	/*
	 * Full Constructor
	 * @param ensemble the ensemble responsible
	 * @param title the song title
	 * @param yearReleased the year the song was released
	 */
	Song(Ensemble ensemble, String title, int yearReleased) {
		mEnsemble = ensemble;
		mTitle = title;
		mYearReleased = yearReleased;
	}
}