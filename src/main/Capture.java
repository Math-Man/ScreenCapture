package main;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;


public class Capture{
	
	
	private BufferedImage capture() throws AWTException 
	{   
		Robot r = new Robot();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		Rectangle rec = new Rectangle();
		rec.width = (int) width;
		rec.height = (int) height;
		
		return r.createScreenCapture(rec);
	}
	
	
	
	private void save(String Addition) throws AWTException 
	{
	
		try {
			BufferedImage b = this.capture();
		    File outputfile = new File("capture\\saved"+Addition+".png");
		    ImageIO.write(b, "png", outputfile);
		} catch (IOException e) {

		}
		
	}
	
	public void capture(int tickRate, int times) throws InterruptedException, AWTException 
	{
		int indexer = 0;
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start("capture\\GIF.gif");
		e.setDelay(1000);  
		
		while(times > indexer) 
		{
			Thread.sleep(tickRate);
			this.save(indexer + "");
			System.out.println(indexer);
			e.addFrame(this.capture());
			System.out.println("AddingFrame");
			indexer++;
		}
		e.finish();
	}
	
	
}
