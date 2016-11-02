package com.mnr.usermanagement.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ImageUtils {

	public static Image cropImage(String path){
		
		File imgFile = new File( ImageUtils.class.getClassLoader().getResource(path).getFile() );
		
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
	
	/**
	 * create random name for image(without extension)
	 * @return
	 */
	public static String createNewName(){
		Random rand = new Random();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<6;i++){
			sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
		}
		
		sb.append(System.currentTimeMillis());
		
		return sb.toString();
		
	}
	
	public static String savePicture(String path){

		try{
			File imgFolder = new File( ImageUtils.class.getClassLoader().getResource("userImages").getFile() );
			if(imgFolder.isDirectory()){
				System.out.println("dir:" + imgFolder);
				
				File sourceImg = new File(path);
				String extension = "";
				int i = sourceImg.toString().lastIndexOf('.');
				if (i > 0) {
				    extension = sourceImg.toString().substring(i+1);
				}
				//BufferedImage bi = new BufferedImage(772,492,BufferedImage.TYPE_INT_ARGB);
				BufferedImage bi = ImageIO.read(sourceImg);
				
				String newName = ImageUtils.createNewName() + "." + extension;
				File newImageFile = new File( imgFolder + "/" + newName );
				newImageFile.createNewFile();
				
				ImageIO.write(bi, extension, newImageFile);
				
				System.out.println("file copied");
				
				return newName;
				
			}else{
				System.out.println("not dir: "+imgFolder);
			}
		}catch (Exception e) {
			System.out.println("exc");
		}
		
		return null;
		
	}
	
}
