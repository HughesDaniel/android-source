package com.bloc.singletons;

import java.util.*;
/*
 * This is a singleton class which maintains communication
 * between classes and Listeners
 */
public class Speakerphone extends Object {

	private static Speakerphone sSpeakerphone;

	/*
	 * get
	 * @return the singleton instance of Speakerphone
	 */
	public static Speakerphone get() {
		if (sSpeakerphone == null) {
			sSpeakerphone = new Speakerphone();
		}
		return sSpeakerphone;
	}

	private HashSet<Listener> mListenerSet;

	private Speakerphone() {
		mListenerSet = new HashSet<Listener>();
	}

	/*
	 * addListener
	 * Add a listener to Speakerphone's list
	 * @param listener an instance of the Listener interface
	 */
	public void addListener(Listener l) {
		mListenerSet.add(l);
	}

	/*
	 * removeListener
	 * Remove a Listener from the Speakerphone's list
	 * @param listener the Listener to remove
	 */
	public void removeListener(Listener l) {
		mListenerSet.remove(l);
	}

	/*
	 * shoutMessage
	 * Sends the message to all of the Listeners tracked by Speakerphone
	 * @param talker a Talker whose message will be sent
	 */
	public void shoutMessage(Talker t) {
		for (Listener l: mListenerSet) {
			l.onMessageReceived(t.getMessage());
		}
	}

	/*
	 * shoutMessage
	 * Sends the message to all of the Listeners which are instances of
	 * the class parameter
	 * @param talker a Talker whose message will be sent
	 * @param cls a Class object representing the type which the Listener
	 *			  should extend from in order to receive the message
	 *
	 * HINT: see Class.isAssignableFrom()
	 *		 http://docs.oracle.com/javase/7/docs/api/java/lang/Class.html#isAssignableFrom(java.lang.Class)
	 */
	public void shoutMessage(Talker t, Class cls) {
		for (Listener l: mListenerSet) {
			if (l.getClass().isAssignableFrom(cls)) {
				l.onMessageReceived(t.getMessage());
			}
		}
	}

	/*
	 * removeAll
	 * Removes all Listeners from Speakerphone
	 */
	public void removeAll() {
		mListenerSet.clear();
	}
}