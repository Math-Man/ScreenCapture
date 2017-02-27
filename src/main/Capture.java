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
	
	
	
	private BufferedImage capture() throws AWTException 	//Captures a single shot in a rectangle
	{   
		Robot r = new Robot();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	//get size of the screen
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		Rectangle rec = new Rectangle();	//create a rectangle object with screen dimensions
		rec.width = (int) width;
		rec.height = (int) height;
		
		File f = new File("capture");	//Creates a directory under root
		try 
		{
			if(f.mkdir()) 
			{
				//Dir created
			}
			
		}
		catch(Exception e) {e.printStackTrace();}
		
		return r.createScreenCapture(rec);	//return the created image
	}
	
	
	
	private void save(String Addition) throws AWTException 	//Saves the image created from capture()  method
	{
	
		try {
			BufferedImage b = this.capture();
		    File outputfile = new File("capture\\saved"+Addition+".png");
		    ImageIO.write(b, "png", outputfile);
		} catch (IOException e) {

		}
		
	}
	
	public void capture(int tickRate, int times , int imageDelay , boolean AutoDelete) throws InterruptedException, AWTException 
	{
		int indexer = 0;
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start("GIF.gif");
		e.setDelay(imageDelay);  
		
		while(times > indexer) 
		{
			Thread.sleep(tickRate);
			this.save(indexer + "");
			//System.out.println(indexer);
			if(AutoDelete)
			{
				File file = new File("capture\\saved"+indexer+".png");
				file.delete();
			}
			e.addFrame(this.capture());
			//System.out.println("AddingFrame");
			indexer++;
		
		}
		e.finish();
	}
	
	
}
