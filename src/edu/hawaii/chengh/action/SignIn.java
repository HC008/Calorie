package edu.hawaii.chengh.action;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * User signs in to access their data.
 * 
 * @author Hansen Cheng
 *
 */
public class SignIn extends JPanel {
  

  private static final long serialVersionUID = 1L;
  private JLabel idLabel = new JLabel("Username: "), passLabel = new JLabel("Password: ");
  private JTextField userTitle = new JTextField(15);
  private JPasswordField pass = new JPasswordField(15);
  
  /**
   * Construct signin panel.
   */
  public SignIn() {
    this.setLayout(new GridLayout(2, 0));
    this.add(idLabel);
    this.add(userTitle);
    this.add(passLabel);
    this.add(pass);
  }
  
  /**
   * Converts password into a whole string.
   * 
   * @return code - a complete password.
   */
  public char[] secureCode() {
    return pass.getPassword();
  }
  

}
