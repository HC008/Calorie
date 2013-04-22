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
  
  
  public JTextField getNameField() {
    return nameField;
  }


  public JTextField getAgeField() {
    return ageField;
  }


  public JTextField getCalField() {
    return calField;
  }


  public JTextField getUserField() {
    return userField;
  }


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
  public String secureCode() {
    char[] temp = passField.getPassword();
    
    String code = "";
    
    for (int i = 0; i < temp.length; i++) {
      code += temp[i];
    }
    
    return code;
  }
  
  public void clearField() {
    nameField.setText("");
    ageField.setText("");
    calField.setText("");
    userField.setText("");
    passField.setText("");
  }
}
