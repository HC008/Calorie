package edu.hawaii.chengh.calorie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import edu.hawaii.chengh.action.CreatePanel;
import edu.hawaii.chengh.action.EditPanel;
import edu.hawaii.chengh.action.FoodPanel;
import edu.hawaii.chengh.action.SignIn;
import edu.hawaii.chengh.processor.Processor;

/**
 * Interface of the calorie app to track amount of
 * calorie in-take of a user.
 * 
 * 
 * @author Hansen Cheng
 *
 */
public class CalorieApp extends JFrame {
  

  private static final long serialVersionUID = 1L;
  private JPanel mainPanel = new JPanel();
  private JTextArea area = new JTextArea();
  private CreatePanel conjurePanel = new CreatePanel();
  private EditPanel editInfo = new EditPanel();
  private FoodPanel foodPanel = new FoodPanel();
  private SignIn logIn = new SignIn();
  private JMenuBar menuBar = new JMenuBar();
  private JMenu menuFile = new JMenu("File"), menuView = new JMenu("View"), 
                access = new JMenu("Access");
  
  private JMenuItem create = new JMenuItem("Create Avatar"), 
                    edit = new JMenuItem("Edit Profile"),
                    food = new JMenuItem("Add Food"),
                    viewChart = new JMenuItem("Calorie Chart"),
                    viewFood = new JMenuItem("Food List"),
                    signIn = new JMenuItem("Sign-In"),
                    signOut = new JMenuItem("Sign-Out");
  
  private List<Avatar> users = new ArrayList<Avatar>();
  private List<String> meals = new ArrayList<String>();
  private Processor process = new Processor();
  private List<String> userTitle = new ArrayList<String>();
  private String userName = "", userId = "", passcode = "";
  private Avatar temp;
  private int userIndex = 0;
  
  /**
   * Construct the program.
   */
  public CalorieApp() {
    mainPanel.setBackground(Color.decode("#AFFEBD"));
    mainPanel.setLayout(null);
    
    //Building the menu and the keys associated with the action
    menuFile.setMnemonic('f');
    create.setPreferredSize(new Dimension(250, 20));
    create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
    
    edit.setPreferredSize(new Dimension(250, 20));
    edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
    
    food.setPreferredSize(new Dimension(250, 20));
    food.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
    
    viewChart.setPreferredSize(new Dimension(250, 20));
    viewChart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_MASK));
    
    viewFood.setPreferredSize(new Dimension(250, 20));
    viewFood.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.ALT_MASK));
    
    signIn.setPreferredSize(new Dimension(250, 20));
    signIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
    
    signOut.setPreferredSize(new Dimension(250, 20));
    signOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
    
    menuFile.add(create);
    menuFile.add(edit);
    menuFile.add(food);
    menuView.add(viewChart);
    menuView.add(viewFood);
    access.add(signIn);
    access.add(signOut);
    menuBar.add(menuFile);
    menuBar.add(menuView);
    menuBar.add(access);
    
    menuBar.setBackground(Color.decode("#EADDBF"));
    menuBar.setBounds(0, 0, 150, 25);
    area.setBounds(500, 50, 700, 300);
    mainPanel.add(menuBar);
    area.setEditable(false);
    mainPanel.add(area);
    
    /* If user used app before, then read in file of names to
     * read in information of all user profiles.
     */
    try {
      if (!process.readInFile().isEmpty()) {
        userTitle = process.readInFile();
        
        for (int i = 0; i < userTitle.size(); i++) {
          Avatar people = process.deserialData(userTitle.get(i));
            
          users.add(people);
        } //end of for loop
      } //end of if statement
    }
    catch (IOException e2) {
      JOptionPane.showMessageDialog(null, "Error with either input or output", 
                                      "Input, Output Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /*Listens to actions of each menu item*/
    
    //Create a new avatar profile
    create.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        int valueOne = JOptionPane.showConfirmDialog(null, conjurePanel, "Create your avatar", 
                                                         JOptionPane.OK_CANCEL_OPTION);
        if (valueOne == JOptionPane.OK_OPTION) {
          String[] info = conjurePanel.fieldInfo().split("  ");
          String password = new String(conjurePanel.secureCode());
          
          Avatar user = new Avatar(info[0], Integer.parseInt(info[1]), 
                                   Integer.parseInt(info[2]), info[3], password);
          userName = info[0];
          userId = info[3];
          passcode = password;
          users.add(user);
          userIndex = users.indexOf(userName);
          
          try {
            process.serialData(user);
            process.writeToFile(user);
          }
          catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          
          conjurePanel.clearField();
          area.append("Profile successfully created");
        }
      }
    });
    
    //Edit one's profile information
    edit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //Set the fields to display information currently on directory
        if (userId.equalsIgnoreCase(users.get(userIndex).getUserId()) &&
            passcode.equalsIgnoreCase(users.get(userIndex).getPassId())) {
          
          editInfo.getNameField().setText(users.get(userIndex).getName());
          editInfo.getAgeField().setText(Integer.toString(users.get(userIndex).getAge()));
          editInfo.getCalField().setText(Integer.toString(users.get(userIndex).getCalLimit()));
          editInfo.getUserField().setText(users.get(userIndex).getUserId());
          editInfo.getPassField().setText(users.get(userIndex).getPassId());
          
          int valueTwo = JOptionPane.showConfirmDialog(null, editInfo, "Edit profile",
                                                            JOptionPane.OK_CANCEL_OPTION);
          
          if (valueTwo == JOptionPane.OK_OPTION) {
            String[] splitted = editInfo.fieldInfo().split("  ");
            String pass = new String(editInfo.secureCode());
            
            users.get(userIndex).setName(splitted[0]);
            users.get(userIndex).setAge(Integer.parseInt(splitted[1]));
            users.get(userIndex).setCalLimit(Integer.parseInt(splitted[2]));
            users.get(userIndex).setUserId(splitted[3]);
            users.get(userIndex).setPassId(pass);
            
            try {
              process.serialData(users.get(userIndex));
            }
            catch (FileNotFoundException e1) {
              JOptionPane.showMessageDialog(null, "File not found", 
                                                  "No File", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException e1) {
              JOptionPane.showMessageDialog(null, "Error with either input or output", 
                                                    "Input, Output Error", JOptionPane.ERROR_MESSAGE);
            } //end of try-catch statement
          } //end of inner if statement
        } //outer if statement
        else {
          JOptionPane.showMessageDialog(null, "Sign-in first before editing info", 
                                                "Edit Error", JOptionPane.ERROR_MESSAGE);
        }
      } 
    });
    
    //Input the food and approximate amount of calories of the food
    food.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int valueThree = JOptionPane.showConfirmDialog(null, foodPanel, 
                                                     "Add the food and amount of calories",
                                                      JOptionPane.OK_CANCEL_OPTION);
        
        if (valueThree == JOptionPane.OK_OPTION) {
          String foodEaten = foodPanel.fieldInfo();
          meals.add(foodEaten);
          
          if (userId.equalsIgnoreCase(users.get(userIndex).getUserId()) &&
              passcode.equals(users.get(userIndex).getPassId())) {
            
            String[] splitted = foodPanel.fieldInfo().split("  ");
            //Add amount of calories the user consumed
            users.get(userIndex).setInTake(Integer.parseInt(splitted[1]));
            
            try {
              process.writeFood(users.get(userIndex).getName(), meals);
            }
            catch (IOException x) {
              JOptionPane.showMessageDialog(null, "File not found", "No File", 
                                                        JOptionPane.ERROR_MESSAGE);
            }
          }
    
          temp = users.get(userIndex);
      
        } //end of outer if statement
      } //end of actionPerformed method
    });
    
    //View the pie data
    viewChart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFrame pieFrame = new JFrame();
        
        if (userId.equalsIgnoreCase(users.get(userIndex).getUserId()) &&
            passcode.equalsIgnoreCase(users.get(userIndex).getPassId())) {
          
          JLabel limit = new JLabel("Overall calorie limit: " + 
                                          users.get(userIndex).getCalLimit());
          PieChart data = new PieChart();
          JFrame pieData = new JFrame();
          
          data.add(limit);
          pieData.setSize(300, 300);
          data.loadData(users.get(userIndex));
          pieData.add(data);
          pieData.setVisible(true);
        }
        else {
          PieChart chart = new PieChart();
    
          pieFrame.setSize(300, 300);
          pieFrame.add(chart);
          pieFrame.setVisible(true);
        }
      }
    });
    
    //View the list of food and its calories
    viewFood.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        area.setText("");
        
        for (int i = 0; i < meals.size(); i++) {
          String[] splitted = meals.get(i).split("  ");
          area.append("Food: " + splitted[0] + "\n" + "Calorie: " + splitted[1]);
        }
      }
    });
    
    //User logs in
    signIn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        int valueFour = JOptionPane.showConfirmDialog(null, logIn, "Logging In",
                                                         JOptionPane.OK_CANCEL_OPTION);
        
        if (valueFour == JOptionPane.OK_OPTION) {
          String idName = logIn.fieldInfo();
          String pass = new String(logIn.secureCode());
          
          for (int j = 0; j < users.size(); j++) {
            if (idName.equalsIgnoreCase(users.get(j).getUserId()) &&
                                    pass.equals(users.get(j).getPassId())) {
              
              area.setText("");
              area.append("Welcome. Sign-in successful.");
              
              //Stores the necessary information to be used after user signed-in
              userName = users.get(j).getName();
              userId = idName;
              passcode = pass;
              userIndex = j;
            }
            else {
              JOptionPane.showMessageDialog(null, "Sign-in unsuccessful", "Sign-in Error", 
                                                                    JOptionPane.ERROR_MESSAGE);
            } //end of inner if-else statement
          } //end of for-loop
        } //end of outer if statement
      } //end of actionPerformed method
    });
    
    //User logs out of the current session
    signOut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        try {
          process.serialData(temp);
        }
        catch (FileNotFoundException e1) {
          JOptionPane.showMessageDialog(null, "File not found", 
                                        "No File", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e1) {
          JOptionPane.showMessageDialog(null, "Error with either input or output", 
                                         "Input, Output Error", JOptionPane.ERROR_MESSAGE);
        }
        
        area.setText("");
        area.append("Signed-out");
        
        //Reset after user signs out
        userId = "";
        passcode = "";
      }
    });
    
    this.add(mainPanel);
    this.setTitle("Calorie App");
    this.setSize(new Dimension(900, 600));
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  /**
   * Main structure to execute the program.
   * 
   * @param args - arguments from console.
   */
  public static void main(String[] args) {
    new CalorieApp();
  }
}
