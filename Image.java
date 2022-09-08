
import java.util.Scanner;
import java.io.*;
import bridges.connect.Bridges;
import bridges.base.ColorGrid;
import bridges.base.Color;

/*
 *Public class to create an image object. This Image class hold the operations needed to read, create, and display an
 *image in the BRIDGES.
 **/
public class Image {
	
	// These are the class attributes needed to complete this lab
    // image dimensions
	// MaxVal is used for reading in the ppm file. for the default constructor, you can init the value as 0
	private int width, height, maxVal;

	// image array and processed array definitions
	private int[] image_array;// array holding the pixel values of the read in image ([r,g,b,r,g,b,...etc])
	private int[] processed_array;// array holding the pixel values of the grayscale image ([r,g,b,r,g,b,...etc])

	// constructors
	// create an Image constructor that initializes all the attribute to a default value
	public Image() {
		this.width = 0;
		this.height = 0;
		this.maxVal = 0;
		this.image_array = null;
		this.processed_array = null;
	}

    // creates an image object by reading the input image
	// Create an Image overloaded constructor that takes in an input file string and calls the read in image function
	public Image(String input_file){
		read(input_file);
	}

	// accessors
	// get width accessor
	public int getWidth() {
		return this.width;
	}

	// set width mutator
	public void setWidth(int w) {
		this.width = w;
	}

	// get height accessor
	public int getHeight() {
		return this.height;
	}
	
	// set height mutator
	public void setHeight(int h) {
		this.height = h;
	}

	// reads an image from the given input image file
	//This function is given to you as a help in understanding how the pixel data is read and initialized
	public void read(String file_name) {
		// open the file
		try {
			Scanner sc = new Scanner(new File(file_name));

			// read the header (assumes PPM text images)
			String magic;
			magic = sc.next();
			this.width = sc.nextInt();
			this.height = sc.nextInt();
			this.maxVal = sc.nextInt();

			System.out.println("Header:" + magic + " " + this.width + " " + this.height +
					" " + this.maxVal);
			
			//make sure the image arrays are null and then initialize them
			if (this.image_array == null)
				this.image_array = new int[this.width*this.height*3];

			if (this.processed_array == null)
				this.processed_array = new int[this.width*this.height*3];

			// read the pixels
			int k = 0;
			for (int i = 0; i < this.height*this.width*3; i++)
				this.image_array[i] = sc.nextInt();
		}
		catch (FileNotFoundException ex)  {
            System.out.println ("Input image file not found!" + ex);
			ex.printStackTrace();
        }
	}

	// displays image using the BRIDGES color grid
	//TODO: given the colorgrid object and if you want to display a grayscale or normal image,
	//populate the colorgrid objects with the pixel values from the read in image.
	//You can set a colorgrids pixel value by doing: cg.set(i, j, new Color(r, g, b);
	public void setColorGrid (ColorGrid cg, Boolean processed) {
		int r, g, b;
		for (int i = 0; i < this.image_array.length; i++) {
			cg.set(image_array[i], i, new Color());
		}
		System.out.println(cg.getHeight() + " " + cg.getWidth());
	}

	//Function to convert the pixel values from an image into grayscale.
	//TODO: Loop over the image array and convert the pixels using this grayscale formula and store them
	//in the processed array
	//(r * 0.299 + g * 0.587+ b * 0.114)
	public void toGrayscale () {
		final int GRAY_RGB = 128;
		float grayVal = 0.299f*GRAY_RGB + 0.587f*GRAY_RGB + 0.114f*GRAY_RGB;

		for (int i = 0; i < this.image_array.length; i++) {
			processed_array[i] = image_array[i];
			processed_array[i] = (int) grayVal;
		}

	}

};
