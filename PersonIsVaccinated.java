import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map; 


public class PersonIsVaccinated
{
	//variables for level 2 
	private Map<String, int[]> frequency_table = new HashMap<>();
	private String model_trained = "false";
	private String prediction;

	//creating an object 
	FileProcessor my_file = new FileProcessor("vaccination_dataset.csv"); 
	

	public String[] predict(String age, String health, String medical, String location)
	{
		//concatinating user input into one string
		String user_input = age + "-" + health + "-" + medical + "-" + location;
	
		//if model has not been trained yet use hardcoded predictions from level 1
		if (model_trained == "false")
		{
			//there are 16 combination of features 
			//takes user input and gives externally calculated predictions 
			switch (user_input) 
			{
				//feature combinations for if person is not vaccinated
				case "adult-healthy-high-rural":
				case "adult-healthy-high-urban":
				case "adult-healthy-low-urban":
				case "adult-vulnerable-low-urban":
				case "child-healthy-low-rural":
				case "child-healthy-low-urban":
				case "child-vulnerable-high-rural":
				case "child-vulnerable-high-urban":
					prediction = "person is NOT vaccinated";
					break;

				//feature combinations for if person is vaccinated
				case "adult-vulnerable-high-urban":
				case "adult-vulnerable-high-rural":
				case "adult-vulnerable-low-rural":
				case "adult-healthy-low-rural":
				case "child-vulnerable-low-urban":
				case "child-vulnerable-low-rural":
				case "child-healthy-high-urban":
				case "child-healthy-high-rural":
					prediction = "person is vaccinated";
					break;

				default:
					prediction = "feature combination is not recognized";
            		break;
			}

		}
		//else if train button has been pressed, then predictions are read from a csv file 
		else if(model_trained == "true")
		{
			//gets values for the user_input feature combination and saves it to counts_array variable
			int[] counts_array = frequency_table.get(user_input);

			//index 0 holds counts for number of vaccinated people for that feature combination
			int vaccinatedCount = counts_array[0];
			int notVaccinatedCount = counts_array[1];

			//if number of vaccinated people for that specific feature combination is more that the non vaccinated people
			//then the prediction is person is vaccinated
			if (vaccinatedCount > notVaccinatedCount) 
			{
				prediction = "person is vaccinated";
			} 
			else if (notVaccinatedCount > vaccinatedCount) 
			{
				prediction = "person is NOT vaccinated";
			}
			else 
			{
				//else if the non vaccinated and vaccinated probability is the same, prediction cannot be made 
				prediction = "unable to predict, there is a 50/50 chance person is vaccinated ";
			}
			
		}

		String[] result = {prediction, model_trained};

		return result;
	}



    
	public void handleTrainButton()
	{ 
		//open csv file and save content to file_data arraylist
        ArrayList<String[]> file_data = my_file.readFile();

		//clear previous training data
		frequency_table.clear();

		//iterates over each row in file
		for (String[] row : file_data)
		{
			//stores features and label from each row in a variable
			String age = row[0];
			String health = row[1];
			String medical = row[2];
			String location = row[3];
			String label = row[4]; 

			
			String feature_combination = age + "-" + health + "-" + medical + "-" + location;

			//start counts if first time seeing this feature combination while reading from file
			if (!frequency_table.containsKey(feature_combination)) 
			{
				//adds feature combination key to hashmap with values of {num_vaccinateed, num_not_vaccinated} as zero initially
				frequency_table.put(feature_combination, new int[]{0, 0}); 
			}

			//gets correspoding int values for this key feature_combination and saves it to counts array variable
			int[] counts_array = frequency_table.get(feature_combination);


			//if label in that row is vaccinated increase the number of vaccinated counts for that feature combination
			if (label.equals("yes")) 
			{
				counts_array[0]++; 
			}
			//otherwise if label in that row is not vaccinated increase the not vaccinated count
			else if (label.equals("no")) 
			{
				counts_array[1]++;
			}
		}

		//model is now trained 
		model_trained = "true"; 

	}


	public void handleAddRowButton(String age, String health, String medical, String location)
	{
		String label;
	
		//loop to keep asking for user input until a valid label given or user pressed cancel 
		while (true) 
		{
			label = JOptionPane.showInputDialog("Has the person been vaccinated (yes or no):");

			//checks if user presses cancel
			if (label == null) 
			{
				JOptionPane.showMessageDialog(null, "Failed to add row");
				break; 
			}
			//if input valid new row will be added
			else if (label.equals("yes") || label.equals("no")) 
			{
				my_file.addRow(age, health, medical, location, label);
				break; 
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "Invalid input please enter 'yes' or 'no'");
			}
		}
	}

}
	

