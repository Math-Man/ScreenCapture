package main;

import java.awt.AWTException;

public class Main {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub

		Capture n = new Capture();
		n.capture(47, 40 , 47 ,true); //takes y pictures every x/1000 seconds
								     //Tickrate < imagedelay fast gif
									//Boolean denotes if the pngs should be removed
	}

}
