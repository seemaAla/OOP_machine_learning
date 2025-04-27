
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class PersonIsVaccinated extends JFrame implements ActionListener
{
	//creating variables
	JLabel age_group;
	JLabel health_status;
	JLabel medical_access;
	JLabel location_type;
	JComboBox<String> age_menu;
	JComboBox<String> health_menu;
	JComboBox<String> medical_access_menu;
	JComboBox<String> location_menu;
	JButton predict_button;
	JPanel panel_features;
	JPanel panel_buttons;

	
	String[] age_options;
	String[] health_options;
	String[] medical_access_options;
	String[] location_options;

	
	public PersonIsVaccinated() 
	{
		//setting frame
		setTitle("Person is Vaccinated prediction model");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		

		//creating panels
		panel_features = new JPanel(null);
		panel_features.setBackground(Color.LIGHT_GRAY);
		panel_features.setBounds(10, 10, 360, 150);

		panel_buttons = new JPanel(null);
		panel_buttons.setBackground(Color.BLUE);
		panel_buttons.setBounds(10, 170, 360, 80);


	
		//age group
		age_group = new JLabel("age group:");
		age_group.setBounds(10, 10, 100, 20);
		
		age_options = new String[] {"adult", "child"};
		age_menu = new JComboBox<>(age_options);
		age_menu.setBounds(120, 10, 100, 20);
		age_menu.addActionListener(this);


		//health status 
		health_status = new JLabel("Health status:");
		health_status.setBounds(10, 40, 100, 20);
		
		health_options = new String[] {"healthy", "vulnerable"};
		health_menu = new JComboBox<>(health_options);
		health_menu.setBounds(120, 40, 100, 20);
		health_menu.addActionListener(this);


		//access to medical system 
		medical_access = new JLabel("Access to medical system:");
		medical_access.setBounds(10, 70, 150, 20);
		
		medical_access_options = new String[] {"high", "low"};
		medical_access_menu = new JComboBox<>(medical_access_options);
		medical_access_menu.setBounds(170, 70, 100, 20);
		medical_access_menu.addActionListener(this);


		//location type 
		location_type = new JLabel("Location type:");
		location_type.setBounds(10, 100, 100, 20);
		
		location_options = new String[] {"urban", "rural"};
		location_menu = new JComboBox<>(location_options);
		location_menu.setBounds(120, 100, 100, 20);
		location_menu.addActionListener(this);



		//button to start prediction
		predict_button = new JButton("Predict");
		predict_button.setBounds(120, 20, 120, 30);
		predict_button.addActionListener(this);



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

		add(panel_features);
		add(panel_buttons);
		
		
		
		setVisible(true);
	}


 	//action when one of the buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) 
    {
		if (e.getSource() == predict_button) 
		{
            handlePredictButton();
        } 
    }

	private void handlePredictButton()
	{
		String age = (String) age_menu.getSelectedItem();
		String health = (String) health_menu.getSelectedItem();
		String medical = (String) medical_access_menu.getSelectedItem();
		String location = (String) location_menu.getSelectedItem();
		
		//concatinating user input into one string
		String user_input = age + "-" + health + "-" + medical + "-" + location;

		String prediction;

		switch (user_input) 
		{
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
				prediction = "error: no prediction was found";
				break;
    		}

    		JOptionPane.showMessageDialog(PersonIsVaccinated.this, "Prediction: " + prediction);
	}

}

