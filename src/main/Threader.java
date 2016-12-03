package main;

import java.awt.AWTException;

public class Threader implements Runnable{

	private int tickRate;
	private int times;
	private int imageDelay;
	private boolean AutoDelete;
	

	
	public Threader(int tickRate , int times , int imageDelay , boolean AutoDelete) 
	{	
		this.tickRate = tickRate;
		this.times = times;
		this.imageDelay = imageDelay;
		this.AutoDelete = AutoDelete;
	}
	
	@Override
	public void run() {
		
		Capture n = new Capture();
		try {
			n.capture(tickRate, times, imageDelay, AutoDelete);
			
		} catch (InterruptedException | AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	
	
}
