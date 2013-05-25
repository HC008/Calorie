package edu.hawaii.chengh.action;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * User provides current profile information.
 * 
 * @author Hansen Cheng
 *
 */
public class EditPanel extends JPanel {
  
  private static final long serialVersionUID = 1L;

  private JLabel nameLabel = new JLabel("Name: "), ageLabel = new JLabel("Age: "),
                 calLabel = new JLabel("Calorie Limit: "), userName = new JLabel("Username: "), 
                 passCode = new JLabel("Password: ");
  
  private JTextField nameField = new JTextField(15), ageField = new JTextField(5),
                     calField = new JTextField(15), userField = new JTextField(15);
  
  private JPasswordField passField = new JPasswordField(15);
  
          
  /**
   * Construct panel and attributes.
   */
  public EditPanel() {
    this.setLayout(new GridLayout(5, 0));
    this.add(nameLabel);
    this.add(nameField);
    this.add(ageLabel);
    this.add(ageField);
    this.add(calLabel);
    this.add(calField);
    this.add(userName);
    this.add(userField);
    this.add(passCode);
    this.add(passField);
  }
  
  /**
   * Retrieve user's actual name.
   * 
   * @return nameField - name of user.
   */
  public JTextField getNameField() {
    return nameField;
  }

  
  /**
   * Retrieve the user's age of the user.
   * 
   * @return ageField - user's age.
   */
  public JTextField getAgeField() {
    return ageField;
  }

  
  /**
   * Retrieve the amount of calorie limit.
   * 
   * @return calField - calorie amount.
   */
  public JTextField getCalField() {
    return calField;
  }

  
  /**
   * Retrieve the username of the user.
   * 
   * @return userField - username.
   */
  public JTextField getUserField() {
    return userField;
  }

  
  /**
   * Retrieve the security password of the user's account.
   * 
   * @return passField - password.
   */
  public JPasswordField getPassField() {
    return passField;
  }


  /**
   * Obtains information from the textfields that requires user to 
   * enter their information.
   * 
   * @return information from textfields.
   */
  public String fieldInfo() {
    return nameField.getText().trim() + "  " + ageField.getText().trim() + "  " + 
           calField.getText().trim() + "  " + userField.getText().trim();
  }
  
  /**
   * Converts password into a whole string.
   * 
   * @return a complete password.
   */
  public char[] secureCode() {
    return passField.getPassword();
  }
  
  /**
   * Clear the textfields of any texts.
   */
  public void clearField() {
    nameField.setText("");
    ageField.setText("");
    calField.setText("");
    userField.setText("");
    passField.setText("");
  }
}
