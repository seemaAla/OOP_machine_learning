
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Font;

public class GUI extends JFrame implements ActionListener
{
	//creating variables
	private JLabel age_group;
    private JLabel health_status;
    private JLabel medical_access;
    private JLabel location_type;
    private JComboBox<String> age_menu;
    private JComboBox<String> health_menu;
    private JComboBox<String> medical_access_menu;
    private JComboBox<String> location_menu;
    private JButton predict_button;
    private JButton train_button;
    private JButton add_row_button;
    private JPanel panel_features;
    private JPanel panel_buttons;
    private String[] age_options;
    private String[] health_options;
    private String[] medical_access_options;
    private String[] location_options;

    private PersonIsVaccinated person;
	
	//constructor	
	public GUI(PersonIsVaccinated person)
	{
        this.person = person;

        //creating font design
        Font largeFont = new Font("Arial", Font.PLAIN, 18);

		//setting the frame
		setTitle("Person is Vaccinated prediction model");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		

		//creating panels
		panel_features = new JPanel(null);
		panel_features.setBackground(Color.LIGHT_GRAY);
		panel_features.setBounds(10, 10, 380, 150);

		panel_buttons = new JPanel(null);
		panel_buttons.setBackground(Color.BLUE);
		panel_buttons.setBounds(10, 170, 380, 35);


	
		//age group
		//creating labels and drop down menus for (age group, health status, medical access and location type) features
		age_group = new JLabel("age group:");
		age_group.setBounds(10, 10, 100, 20);
        age_group.setFont(largeFont);
		
		//drop down menu options
		age_options = new String[] {"adult", "child"};
		age_menu = new JComboBox<>(age_options);
		age_menu.setBounds(120, 10, 100, 25);
        age_menu.setFont(largeFont);
		age_menu.addActionListener(this);


		//health status
		health_status = new JLabel("Health status:");
		health_status.setBounds(10, 40, 130, 20);
        health_status.setFont(largeFont);
		
		health_options = new String[] {"healthy", "vulnerable"};
		health_menu = new JComboBox<>(health_options);
		health_menu.setBounds(150, 40, 130, 25);
        health_menu.setFont(largeFont);
		health_menu.addActionListener(this);


		//access to medical system 
		medical_access = new JLabel("Access to medical system:");
		medical_access.setBounds(10, 70, 210, 20);
        medical_access.setFont(largeFont);
		
		medical_access_options = new String[] {"high", "low"};
		medical_access_menu = new JComboBox<>(medical_access_options);
		medical_access_menu.setBounds(240, 70, 100, 25);
        medical_access_menu.setFont(largeFont);
		medical_access_menu.addActionListener(this);


		//location type 
		location_type = new JLabel("Location type:");
		location_type.setBounds(10, 100, 130, 20);
        location_type.setFont(largeFont);
		
		location_options = new String[] {"urban", "rural"};
		location_menu = new JComboBox<>(location_options);
		location_menu.setBounds(140, 100, 100, 25);
        location_menu.setFont(largeFont);
		location_menu.addActionListener(this);



		//button to start prediction
		predict_button = new JButton("Predict");
		predict_button.setBounds(10, 6, 100, 25);
        predict_button.setFont(largeFont);
		predict_button.addActionListener(this);

		//button for training
		train_button = new JButton("Train");
		train_button.setBounds(130, 6, 100, 25);
        train_button.setFont(largeFont);
		train_button.addActionListener(this);

		//button for adding new rows
		add_row_button = new JButton("Add Row");
		add_row_button.setToolTipText("add the features above to csv file");
		add_row_button.setBounds(250, 6, 120, 25);
        add_row_button.setFont(largeFont);
		add_row_button.addActionListener(this);


		//add labels and dropdown menus to panels
		panel_features.add(age_group);
		panel_features.add(age_menu);
		panel_features.add(health_status);
		panel_features.add(health_menu);
		panel_features.add(medical_access);
		panel_features.add(medical_access_menu);
		panel_features.add(location_type);
		panel_features.add(location_menu);

		panel_buttons.add(predict_button);
		panel_buttons.add(train_button);
		panel_buttons.add(add_row_button);

		//adding panels to frame
		add(panel_features);
		add(panel_buttons);
		
		
	
		setVisible(true);
    }


    //getter method for getting features selected by user
    public String getSelectedAge() 
    {
        return (String) age_menu.getSelectedItem();
    }

    public String getSelectedHealth() 
    {
        return (String) health_menu.getSelectedItem();
    }

    public String getSelectedMedical() 
    {
        return (String) medical_access_menu.getSelectedItem();
    }

    public String getSelectedLocation() 
    {
        return (String) location_menu.getSelectedItem();
    }

    //puts all chosen features into a string array
    public String[] getSelectedFeatures() 
    {
        return new String[] {getSelectedAge(), getSelectedHealth(), getSelectedMedical(), getSelectedLocation()};
    }

    
 	//action when one of the buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) 
    {
		//if predict button clicked
		if (e.getSource() == predict_button) 
		{
            handlePredictButton();
        } 
		else if (e.getSource() == train_button)
		{
            //calls method on object 'person' from class PersonIsVaccinated, showing composistion
			person.handleTrainButton();
            
		    JOptionPane.showMessageDialog(this, "Training complete, use predict button to get prediction");
		}
        else if (e.getSource() == add_row_button)
		{
            //calls method to get the selected features from user_input
            String[] features = getSelectedFeatures();	

            //passes user input to the handleAddRowButton method 
		    person.handleAddRowButton(features[0], features[1], features[2], features[3]);
            
		}
    }


    //method that is called when predict button pressed
	public void handlePredictButton()
	{
        String[] features = getSelectedFeatures();	

        //calls method predict on object person 
        String[] prediction_result = person.predict(features[0], features[1], features[2], features[3]);

        //if index 1 which is model_trained equals false, print prediction before training
        if(prediction_result[1] == "false")
        {
            JOptionPane.showMessageDialog(this, "Prediction: " + prediction_result[0]);
        }
        //else if model is trained print prediction after training
        else if(prediction_result[1] == "true")
        {
            JOptionPane.showMessageDialog(this, "Prediction after training: " + prediction_result[0]);
        }
        

	}

}
