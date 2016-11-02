package com.mnr.usermanagement.model;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ImageManipulator {

	public static Image cropImage(String path){
		
		File imgFile = new File( ImageManipulator.class.getClassLoader().getResource(path).getFile() );
		
		if( imgFile.exists() && !imgFile.isDirectory() ){
			Image img = new Image(path);

			int newWidth = 100;
			int newHeight = 80;
			
			if(img.getWidth()>=newWidth && img.getHeight()>=newHeight){
				
				int x = (int) ( img.getWidth() - newWidth )/2;
				int y = (int) ( img.getHeight() - newHeight )/2;
				
				WritableImage wi = new WritableImage(img.getPixelReader(), x, y, newWidth, newHeight);
				
				return wi;
				
			}
			
		}
		
		return null;
		
	}
	
	
}
