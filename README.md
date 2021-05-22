# geotools
##### About
This program is designed to facilitate several petroleum geology workflows. It currently contains a utility class for calculating
reservoir volumetrics (either in _bbls oil in place_ or _mcf of gas in place_), a command line tool for calculating water saturation for a 
given reservoir, and a tool to sort workover candidates in a secondary recovery field based on geologic factors.

More tools will be added as this project evolves. A recently added tool is a command line program that takes in each input parameter to the original-oil-in-place (OOIP)
equation as a range (as there is typically some uncertainty associated with each value) and returns a user-specified number of OOIP outputs with the parameters
sampled from within the respective ranges at random. It's a method of running a Monte Carlo simulation. 

##### Get started
After compiling, the tools can be run from **PrioritizeWorkovers**, **WaterSaturation**, or **RunVolumes** depending on which one you want to use.

##### Important note:
The csv file from which parameters are loaded into the _RunVolumes_ program (parameters.csv) must contain the inputs as follows:

  A (low),A (high),H (low),H (high),So (low), So(high),phi (low),phi (high),vf (low),vf (high)
  
  **A**: Area
  
  **H**: Height
  
  **So**: Oil saturation
  
  **phi**: Porosity
  
  **vf**: Formation volume factor
