# personIsVaccinated Frequency table

				
AGE_GROUP,   HEALTH_STATUS,   MEDICAL_SYSTEM_ACCESS,   LOCATION_TYPE,   TOTAL,   VACCINATED,   NOT VACCINATED	
adult, vulnerable, high, urban   16   10   6   
adult, vulnerable, high, rural   13   7    6   
adult, vulnerable, low, urban    16   7    9   
adult, vulnerable, low, rural    15   9    6   

adult, health, high, urban       11   5    6   
adult, health, high, rural       11   5    6   
adult, health, low, urban        14   6    8   
adult, health, low, rural        10   7    3   

child, vulnerable, high, urban   11   4    7   
child, vulnerable, high, rural   9    4    5   
child, vulnerable, low, urban    15   8    7   
child, vulnerable, low, rural    16   9    7   

child, health, high, urban       12   7    5   
child, health, high, rural       9    5    4   
child, health, low, urban        11   5    6   
child, health, low, rural        11   5    6  



GUI class
This class creates the graphical user interface that allows users to interact with the system. It includes dropdown menus for selecting a person's age, health status, medical access, and location, along with buttons to predict vaccination status, train the model with existing data, or add new data to the file. The class contains getter methods to retrieve the selected features, a handlePredictButton() method to perform predictions based on user input and an actionPerformed() method that manages what button is clicked 

PersonIsVaccinated class
This class handles the logic for making predictions and training the model. If the model hasn't been trained yet, it uses hardcoded rules calculated from a frequency table to make predictions based on the features provided. Once the model is trained it compares the frequency of "vaccinated" and "not vaccinated" labels for each feature combination and uses that to make more accurate predictions by choosing the label with higher frequency as the prediction. The class includes three main methods which are predict() which makes a prediction based on user input, handleTrainButton() which trains the model using data from a CSV file and handleAddRowButton(), which adds new data to the CSV file by taking user input from the drop down menu then asking them for label when add row button is pressed.

FileProcessor class
The FileProcessor class is responsible for reading from and writing to a file. It has two main methods, readFile() which reads data from a CSV file and returns it as a list of arrays, and addRow(), which adds a new row of data (such as age, health, medical access, location and label) to the CSV file. 


FUNCTIONALITY: 
 different class to meet oop standards,
 composition GUI class contains instance of PersonIsVaccinated,
 error checking in FileProcessor and gui label input
 encapsulating attributes and using getters to get their data



 WHAT WOULD I DO:
If I had more time, I would complete the Level 4 requirement by implementing functionality to calculate the accuracy of the predictor.  I would also break down the PersonIsVaccinated class into smaller, reusable methods to improve code readability and follow better object-oriented programming practices.
