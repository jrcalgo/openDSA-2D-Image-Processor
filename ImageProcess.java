
import bridges.connect.Bridges;
import bridges.base.ColorGrid;
import java.io.IOException;


public class ImageProcess {
	/*

	 * This is the Driver class for the Image Processing Lab.
	 * This Driver is given to you already completed. Your task is to complete the function calls and class creation
	 * in the ImageProcess.java file. This file creates the Image object and BRIDGES ColorGrid object and calls the 
	 * needed functions to read an image, display an image normally, and display an image in grayscale.
	 * */
	public static void main(String[] args) throws Exception {

		//creates a bridges object for visualizing the image
		Bridges bridges = new Bridges(2, "jredman15", "1191224536432");

		//create an image object and read in a ppm image data for the Image attributes (width, height, pixel data)
		Image img = new Image();
		//use your relative path in your project
		img.read("/home/king/Downloads/Lab 1/yosemite.ppm");

		// create a BRIDGES color grid object
		ColorGrid cg = new ColorGrid(img.getHeight(), img.getWidth());
	
		//display the original image
		img.setColorGrid(cg, false);
		bridges.setDataStructure(cg);
		bridges.visualize();

		// convert to grayscale and display
		img.toGrayscale();
		img.setColorGrid(cg, true);
		bridges.setDataStructure(cg);
		bridges.visualize();
	}

}
