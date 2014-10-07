package com.bloc.objects;

class PopSong extends Song {
	// The number of weeks this song stayed on Billboard's top 100
	int mWeeksOnBillboard;

	/*
	 * Basic Constructor
	 * Side-effects: Assigns some default ensemble, title,
	 *				 year and weeks on billboard
	 */
	
	PopSong() {
		mWeeksOnBillboard = 24;
	}

	/*
	 * Partial Constructor
	 * Side-effects: Sets the year of release to 0
	 * @param ensemble the ensemble responsible
	 * @param title the song title
	 */
	PopSong(Ensemble ensemble, String title) {
		super(ensemble, title);
		mWeeksOnBillboard = 0;		
	}

	/*
	 * Full Song Constructor
	 * Side-effects: Sets the weeks on billboard to 0
	 * @param ensemble the ensemble responsible
	 * @param title the song title
	 * @param yearReleased the year the song was released
	 */
	PopSong(Ensemble ensemble, String title, int releaseYear) {
		super(ensemble, title, releaseYear);
		mWeeksOnBillboard = 0;
	}

	/*
	 * Full PopSong Constructor
	 * @param ensemble the ensemble responsible
	 * @param title the song title
	 * @param yearReleased the year the song was released
	 * @param weeksOnBillboard number of weeks this song lasted on the
	 *		  				   Billboard's top 100
	 */
	PopSong(Ensemble ensemble, String title, int releaseYear, int top100weeks) {
		super(ensemble, title, releaseYear);
		mWeeksOnBillboard = top100weeks;
	}
}