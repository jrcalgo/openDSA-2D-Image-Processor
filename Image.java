
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
	// given the colorgrid object and if you want to display a grayscale or normal image,
	//populate the colorgrid objects with the pixel values from the read in image.
	//You can set a colorgrids pixel value by doing: cg.set(i, j, new Color(r, g, b);
	public void setColorGrid (ColorGrid cg, Boolean processed) {
		// branch for normal image
		if (!processed) {
			// creating 2D Color array to store image_array[index] rgb sets
			final Color[][] color = new Color[getHeight()][getWidth()];

			// outermost for loop for index variable and iterating through each array value based on array dimensions.
			// Use of 'next' loop variable for self-documentation.
			for (int i = 0, next = 1; i < this.height*this.width*3; ) {
				// following two inner for loops for row and column. Column iterates until end of row then row iterates,
				// repeating process. Color[row][col] stores new Color with corresponding r, g, and b values from
				// image_array[index]. ColorGrid variable 'cg' set to color[row][col] color at corresponding coordinate
				// in image. Iterates until no rows left.
				// Add 3 to outermost loop control variable 'i' to move to next rgb set.
				for (int row = 0; row < getHeight(); row++) {
					for (int col = 0; col < getWidth(); col++, i = i + 3) {

						color[row][col] = new Color(this.image_array[i], this.image_array[i+next],
								this.image_array[i+next+next]);
						cg.set(row, col, color[row][col]);


					}
				}
			}
		}
		// branch for processed/grayscale image
		else if (processed) {
			// outermost for loop for index variable and iterating through each array value based on array dimensions.
			// Use of 'next' loop variable for self-documentation.
			for (int i = 0, next = 1; i < this.height*this.width*3; ) {
				// following two inner for loops for row and column. Column iterates until end of row then row iterates,
				// repeating process. Declares 'rgb' integer variable, sets this variable to summation of 3 consecutive
				// rgb values in processed_array[]. ColorGrid variable 'cg' set to new Color using summed 'rgb' variable
				// as each r, g, b value at corresponding coordinate in image. Iterates until no rows left.
				// Add 3 to outermost loop control variable 'i' to move to next rgb set.
				for (int row = 0; row < getHeight(); row++) {
					for (int col = 0; col < getWidth(); col++, i = i + 3) {
						int rgb;

						rgb = this.processed_array[i] + this.processed_array[i+next]
								+ this.processed_array[i+next+next];
						cg.set(row, col, new Color(rgb, rgb, rgb));
					}
				}
			}
		}

	}


	//Function to convert the pixel values from an image into grayscale.
	// Loop over the image array and convert the pixels using this grayscale formula and store them
	//in the processed array
	//(r * 0.299 + g * 0.587+ b * 0.114)
	public void toGrayscale () {
		// constant rgb conversion values below
		final float R = .299f, G = 0.587f, B = 0.114f;

		// for loop iterates through each array value based on array dimensions, stores image_array[index] value in
		// processed_array[index], same index of processed_array then multiplied by float conversion constant value with
		// an integer data casting and stored in same index of processed_array. Repeat for each value in set of RGB.
		// Use of 'next' loop variable for self-documentation.
		// Add 3 to loop control variable 'i' to move to next rgb set.
		for (int i = 0, next = 1; i < this.height*this.width*3; i = i+3) {
			this.processed_array[i] = this.image_array[i];
			this.processed_array[i] = (int) (this.processed_array[i] * R);

			this.processed_array[i+next] = this.image_array[i+next];
			this.processed_array[i+next] = (int) (this.processed_array[i+next] * G);

			this.processed_array[i+next+next] = this.image_array[i+next+next];
			this.processed_array[i+next+next] = (int) (this.processed_array[i+next+next] * B);
		}
	}
}


