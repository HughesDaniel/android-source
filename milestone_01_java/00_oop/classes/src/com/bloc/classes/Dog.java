package com.bloc.classes;

class Dog {
    // The length of hair which
    final float HAIR_CUT_LENGTH = 0.15f;
    // Minimum weight that any Dog can be
    final float MIN_WEIGHT = 1.25f;
	// Amount of weight to gain after eating
	final float WEIGHT_GAIN = 0.25f;
	// Amount of weight to lose after playing
	final float WEIGHT_LOSS = 0.2f;
	// Hair length
	float mHairLength;
	// Gender, either "male" or "female"
	String mGender;
	// Size, either "tiny", "small", "average", or "large"
	String mSize;
	// Its age
	int mAge;
	// Its weight in pounds
	float mWeight;
	// The color of its coat
	String mColor;

	// The number of meals
	int mNumMeals;
	//number of plays
	int mNumPlays;

	/*
	 * getHairLength
	 * @return this Dog's hair length
	 */
	public float getHairLength() {
		return mHairLength;
	}

	/*
	 * setHairLength
	 * Sets the length of the Dog's hair
	 * @param hairLength the new length of the hair, a float
	 * @return nothing
	 */
	public void setHairLength(float length) {
		mHairLength = length;
	}

	/*
	 * getGender
	 * @return this Dog's gender
	 */
	public String getGender() {
		return mGender;
	}

	/*
	 * setGender
	 * Sets this Dog's gender
	 * @param gender the new gender of the Dog, a String
	 * @return nothing
	 */
	public void setGender(String gender) {
		if (gender.equals("male") || gender.equals("female")) {
			mGender = gender;
		} else {
			System.out.println("incorrect string for gender");
		}
	}

	/*
	 * getSize
	 * @return the size of the dog
	 */
	public String getSize() {
		return mSize;
	}

	/*
	 * setSize
	 * Sets the size of the Dog
	 * @param size the new size of the Dog, a String
	 * @return nothing
	 */
	public void setSize(String size) {
		if (size.equals("tiny") || size.equals("small")
			|| size.equals("average") || size.equals("large")) {
			mSize = size;
		} else {
			System.out.println("incorrect string for size");
		}
	}

	/*
	 * getAge
	 * @return this Dog's age
	 */
	public int getAge() {
		return mAge;
	}

	/*
	 * setAge
	 * Sets the age of the Dog
	 * @param age the new age of the Dog, an int
	 * @return nothing
	 */
	public void setAge(int age) {
		mAge = age;
	}

	/*
	 * getWeight
	 * @return this Dog's weight
	 */
	public float getWeight() {
		return mWeight;
	}

	/*
	 * setWeight
	 * Sets the weight of the Dog
	 * @param weight the new weight of the Dog, a float
	 * @return nothing
	 */
	public void setWeight(float weight) {
		if (weight >= MIN_WEIGHT) {
			mWeight = weight;
		} else {
			System.out.println("Illegal value for weight, not big enough");
		}
	}

	/*
	 * getColor
	 * @return this Dog's color
	 */
	public String getColor() {
		return mColor;
	}

	/*
	 * setColor
	 * Sets the color of the Dog
	 * @param color the new color of the Dog's coat, a String
	 * @return nothing
	 */
	public void setColor(String color) {
		mColor = color;
	}

	/*
	 * feed
	 * Side-effect: 1. The Dog gains weight, specifically WEIGHT_GAIN
	 *              2. Every 3 meals, the Dog grows to a larger size, if *                 possible
	 *              i.e. "tiny" (3 meals later ->) "small" (3 meals later ->)
	 *                   "average" (3 meals later ->) "large"
	 * @return nothing
	 */
	public void feed() {
		mWeight += WEIGHT_GAIN;
		mNumMeals++;
		if (mNumMeals == 3) {
			increaseSize(); 
			mNumMeals = 0;  
		}
	}

	/*
	 * play
	 * Side-effect: 1. The Dog loses weight, specifically WEIGHT_LOSS
	 *				2. Every 6 play invocations, the Dog shrinks to a smaller *                 size, if possible
	 *				i.e. "large" (6 plays later->) "average" (6 plays later->) *                   "small" -> "tiny"
     *              3. The Dog cannot shrink to a weight smaller than *                 MIN_WEIGHT
	 * @return nothing
	 */
	public void play() {
		mWeight -= WEIGHT_LOSS;
		mNumPlays++;
		if (mWeight < MIN_WEIGHT) mWeight = MIN_WEIGHT;
		if (mNumPlays == 6) {
			decreaseSize();
			mNumPlays = 0;
		}		
	}

	/*
	 * cutHair
	 * Side-effect: the Dog's hair length is reduced by HAIR_CUT_LENGTH
     * The Dog's hair cannot be shorter than 0f
	 * @return nothing
	 */
	public void cutHair() {
		mHairLength -= HAIR_CUT_LENGTH;
		if (mHairLength < 0) mHairLength = 0;
	}

	/*
	* increaseSize
	* private helper function for feed() method. Every 3 meals size must increase
	* when that happens this function is called to increase the size of the dog
	* @return nothing
	*/
	private void increaseSize() {
		String size = mSize;
		switch (size) {
			case "tiny":
				mSize = "small";
				break;
			case "small":
				mSize = "average";
				break;
			case "average":
				mSize = "large";
				break;
			case "large":
				break;
			default:
				System.out.println("If you see this, you screwed up somehow:" +
					" increaseSize()");
		}
	}

	/*
	* decreaseSize
	* private helper function for play() method. Every 6 plays size must 
	* decrease. When that happens this function is called to decrease the
	* size of the dog
	* @return nothing
	*/
	private void decreaseSize() {
		String size = mSize;
		switch (size) {
			case "large":
				mSize = "average";
				break;
			case "average":
				mSize = "small";
				break;
			case "small":
				mSize = "tiny";
				break;
			case "tiny":
				break;
			default:
			System.out.println("IF you see this, the end is nigh: decreaseSize");
		}
	}

}