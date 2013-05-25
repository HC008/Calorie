package edu.hawaii.chengh.action;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * User provides information to create an avatar.
 * 
 * @author Hansen Cheng
 *
 */
public class CreatePanel extends JPanel {
  
  private static final long serialVersionUID = 1L;

  private JLabel nameLabel = new JLabel("Name: "), ageLabel = new JLabel("Age: "),
                 calLabel = new JLabel("Calorie Limit: "), userLabel = new JLabel("Username: "), 
                 passCode = new JLabel("Password: ");
  
  private JTextField nameInfo = new JTextField(15), ageInfo = new JTextField(5),
                     calInfo = new JTextField(15), userInfo = new JTextField(15);
  
  private JPasswordField password = new JPasswordField(15);
                    
          
  /**
   * Construct panel and attributes.
   */
  public CreatePanel() {
    this.setLayout(new GridLayout(5, 0));
    this.add(nameLabel);
    this.add(nameInfo);
    this.add(ageLabel);
    this.add(ageInfo);
    this.add(calLabel);
    this.add(calInfo);
    this.add(userLabel);
    this.add(userInfo);
    this.add(passCode);
    this.add(password);
  }
  
  /**
   * Obtains information from the textfields that requires user to 
   * enter their information.
   * 
   * @return information from textfields.
   */
  public String fieldInfo() {
    return nameInfo.getText().trim() + "  " + ageInfo.getText().trim() + "  " + 
           calInfo.getText().trim() + "  " + userInfo.getText().trim();
  }
  
  /**
   * Converts password into a whole string.
   * 
   * @return a complete password.
   */
  public char[] secureCode() {
    return password.getPassword();
  }
  
  /**
   * Clears the textfields of previous input.
   */
  public void clearField() {
    nameInfo.setText("");
    ageInfo.setText("");
    calInfo.setText("");
    userInfo.setText("");
    password.setText("");
  }
}
