# Lab-1-ITSC2214-091: Processing 2D Images
## BRIDGES Link: [2D Image Processing, by Jackson Redman](https://bridges-cs.herokuapp.com/assignments/2/jredman15)
### Goals
 1. Working with 2D images.
 2. Implementing simple operations on images, such as color manipulation, filtering.
 3. CS concepts: 2D array addressing, 1D array processing, working with variables, classes, and methods, processing.
### Description
Students are provided with a sample images in a simple text format (eg., PPM) that they read and display using the ColorGrid datatype (an abstraction for images). Students are asked to perform a a grayscale image conversion on the provided input image, such as the one given below.

Students are provided with a skeleton of an Image class that they will use to implement the required image processing operations listed below. The student must implement all functions maked with a TODO tag. These methods are a constructor for the image class, and overloaded constructor, accessors and mutators for the Image class attributes (getters and setters), creating the colorgrid image, and converting the image to grayscale. The image processing driver class is already given to you. This class is used to create an instance of the Image Class, read in the image, create a colorgrid object, call the image processing methods, and display the image.