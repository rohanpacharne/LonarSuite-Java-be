package com.lonar.vendor.vendorportal.fileupload;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
public class CreateThumbNailImg 
{
	public String readImg(String imgPath, String name) throws IOException
	{
		System.out.println("p        "+imgPath);
		BufferedImage originalBufferedImage = null;
		originalBufferedImage = ImageIO.read(new File(imgPath));
		return scalingImgToSize(originalBufferedImage,imgPath,name);
	}
	public String scalingImgToSize(BufferedImage originalBufferedImage, String imgPath, String name) throws IOException
	{
		int thumbnailWidth = 200;
	 System.out.println("akshay "+  originalBufferedImage.getWidth() +" "+ originalBufferedImage.getHeight());
		int widthToScale, heightToScale;
		if (originalBufferedImage.getWidth() > originalBufferedImage.getHeight()) 
		{
			heightToScale = (int)(1.0 * thumbnailWidth);
			widthToScale = (int)((heightToScale * 1.0) / originalBufferedImage.getHeight() 
	                    * originalBufferedImage.getWidth());
	    } 
		else 
		{
			widthToScale = (int)(1.0 * thumbnailWidth);
			heightToScale = (int)((widthToScale * 1.0) / originalBufferedImage.getWidth() 
	                    * originalBufferedImage.getHeight());
		}
		BufferedImage resizedImage = new BufferedImage(widthToScale, 
			    heightToScale, originalBufferedImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.setComposite(AlphaComposite.Src);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(originalBufferedImage, 0, 0, widthToScale, heightToScale, null);
			g.dispose();

			return croppingTheCenterOfResultantImg(resizedImage,thumbnailWidth,imgPath,name);

	}
 
	private String croppingTheCenterOfResultantImg(BufferedImage resizedImage, int thumbnailWidth, String imgPath, String name) throws IOException 
	{/*
		System.out.println("resizedImage.getWidth() "+resizedImage.getWidth());
		System.out.println("resizedImage.getHeight() "+resizedImage.getHeight());
		System.out.println("thumbnailWidth "+thumbnailWidth);*/
		int x = (resizedImage.getWidth() - thumbnailWidth) / 2;
		int y = (resizedImage.getHeight() - thumbnailWidth) / 2;
		if (x < 0 || y < 0) {
		    throw new IllegalArgumentException("Width of new thumbnail is bigger than original image");
		}
		else
		{
			System.out.println(x+" "+y+" "+thumbnailWidth);
			BufferedImage thumbnailBufferedImage = resizedImage.getSubimage(x, 0, thumbnailWidth, thumbnailWidth+y);
			System.out.println("imgPath  "+imgPath);
			String[] fileFrags = imgPath.split("\\.");
			String extension = fileFrags[fileFrags.length - 1];
			String filePath = fileFrags[0]+"-thumb"+"."+"jpg";
			ImageIO.write(thumbnailBufferedImage, "JPG", new File(filePath));
			System.out.println("final---------------------------- "+filePath);
			String[] names = name.split("\\.");
			//String extension1 = fileFrags[fileFrags.length - 1];
			return names[0]+"-thumb"+"."+"jpg";
		}
	}
 
}
