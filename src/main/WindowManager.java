package main;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class WindowManager extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		drawMenu(primaryStage);
		
	}
	
	public static void drawMenu(Stage primaryStage1) 
	{
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 600, 150);
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(3);
		
		//Add controls
		Button bStart = new Button("Start");
		GridPane.setConstraints(bStart,0,0);
		grid.getChildren().add(bStart);
		
		Slider sl1 = new Slider();
		sl1.setMin(10);
		sl1.setMax(1500);
		sl1.setValue(10);
		sl1.setMinWidth(350);
		sl1.setMinorTickCount(5);
		sl1.setBlockIncrement(10);
		GridPane.setConstraints(sl1,0,2);
		grid.getChildren().add(sl1);
		
		Slider sl2 = new Slider();
		sl2.setMin(10);
		sl2.setMax(25000);
		sl2.setValue(10);
		sl2.setMinWidth(350);
		sl2.setMinorTickCount(5);
		sl2.setBlockIncrement(10);
		GridPane.setConstraints(sl2,0,3);
		grid.getChildren().add(sl2);
		
		Label info = new Label(" ");
		GridPane.setConstraints(info,0,6);
		grid.getChildren().add(info);
		
		Label sl1L = new Label(sl1.getValue() + " MS");
		GridPane.setConstraints(sl1L,1,2);
		grid.getChildren().add(sl1L);
		
		Label sl1LE = new Label("(Time between each snapshot)");
		GridPane.setConstraints(sl1LE,2,2);
		grid.getChildren().add(sl1LE);
		
		Label sl2L = new Label(sl2.getValue() + " Shots");
		GridPane.setConstraints(sl2L,1,3);
		grid.getChildren().add(sl2L);
		
		Label sl2LE = new Label("(Number of snapshots to take)");
		GridPane.setConstraints(sl2LE,2,3);
		grid.getChildren().add(sl2LE);
		
		CheckBox autoRemove = new CheckBox("Auto Remove Pictures?");
		autoRemove.setSelected(true);
		GridPane.setConstraints(autoRemove,0,4);
		grid.getChildren().add(autoRemove);
		


		
		
		//Controls added
		
		primaryStage1.setTitle("Screen Recorder");
		primaryStage1.setScene(scene); 
		primaryStage1.setResizable(false);
		primaryStage1.show(); 
		
		//Event handler setup
		 
		
		bStart.setOnAction(new EventHandler<ActionEvent>() { 
	            public void handle(ActionEvent event) {
	            	
	            		info.setText("Started");
	            		
	            		Threader t12 = new Threader((int)sl1.getValue() + 16, (int)sl2.getValue() , (int)sl1.getValue() ,autoRemove.isSelected());

	        			Thread a = new Thread(t12);
	        			a.start();

	        			//TODO this needs to change
	        			primaryStage1.focusedProperty().addListener(new ChangeListener<Boolean>()
	        			{
	        			  @Override
	        			  public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
	        			  {
	        				
	        			    if(!a.isAlive()) {info.setText("Done");}
	        			  }
	        			});
	        		
	        			
	            }
	        });
			
			
			autoRemove.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent event) {
						
					if(autoRemove.isSelected()) {}
					
				}	
			});
			
			
		sl1.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				 
				sl1L.setText((int)sl1.getValue() + " MS");
				
			}
		});
		
		sl2.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				sl2L.setText((int)sl2.getValue() + " Times");
				
			
			}
		});
			
		
		
		//Event handlers set
	
		
	}
	
}
