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
                    signIn = new JMenuItem("Sign-In"),
                    signOut = new JMenuItem("Sign-Out");
  
  private List<Avatar> users = new ArrayList<Avatar>();
  private List<String> meals = new ArrayList<String>();
  private Processor process = new Processor();
  private List<String> userTitle = new ArrayList<String>();
  private String userName = "", passcode = "";
  private int userIndex = -1;
  
  /**
   * Construct the program.
   */
  public CalorieApp() {
    mainPanel.setBackground(Color.decode("#AFFEBD"));
    mainPanel.setLayout(null);
    
    //Menu
    menuFile.setMnemonic('f');
    create.setPreferredSize(new Dimension(250, 20));
    create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
    edit.setPreferredSize(new Dimension(250, 20));
    edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
    food.setPreferredSize(new Dimension(250, 20));
    food.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
    viewChart.setPreferredSize(new Dimension(250, 20));
    viewChart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_MASK));
    signIn.setPreferredSize(new Dimension(250, 20));
    signIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
    signOut.setPreferredSize(new Dimension(250, 20));
    signOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
    menuFile.add(create);
    menuFile.add(edit);
    menuFile.add(food);
    menuView.add(viewChart);
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
        }
      }
      
    }
    catch (IOException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
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
          passcode = password;
          users.add(user);
          
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
        for (int i = 0; i < users.size(); i++) {
          if (userName.equalsIgnoreCase(users.get(i).getUserId()) &&
              passcode.equalsIgnoreCase(users.get(i).getPassId())) {
            
            editInfo.getNameField().setText(users.get(i).getName());
            editInfo.getAgeField().setText(Integer.toString(users.get(i).getAge()));
            editInfo.getCalField().setText(Integer.toString(users.get(i).getCalLimit()));
            editInfo.getUserField().setText(users.get(i).getUserId());
            editInfo.getPassField().setText(users.get(i).getPassId());
          
            break;
          }
          else {
            JOptionPane.showMessageDialog(null, "Sign-in first before editing info", 
                                                  "Edit Error", JOptionPane.ERROR_MESSAGE);
            
            break;
          }
        }
        
        int valueTwo = JOptionPane.showConfirmDialog(null, editInfo, "Edit profile",
                                                            JOptionPane.OK_CANCEL_OPTION);
        
        if (valueTwo == JOptionPane.OK_OPTION) {
          System.out.println("2");
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
          System.out.println(foodEaten);
          meals.add(foodEaten);
          
          for (int j = 0; j < users.size(); j++) {
            if (userName.equalsIgnoreCase(users.get(j).getUserId()) &&
                passcode.equals(users.get(j).getPassId())) {
              
              String[] splitted = foodPanel.fieldInfo().split("  ");
              users.get(j).setInTake(Integer.parseInt(splitted[1]));
              
              try {
                process.writeFood(users.get(j).getName(), meals);
              }
              catch (IOException x) {
                JOptionPane.showMessageDialog(null, "File not found", "No File", 
                                                          JOptionPane.ERROR_MESSAGE);
              }
              break;
              
            }
          }
     
          area.setText(" ");
          for (int i = 0; i < meals.size(); i++) {
            String[] splits = meals.get(i).split("  ");
            area.append("Food: " + splits[0] + "\n" + "Calorie: " + splits[1] + "\n\n");
          } //end of for loop
        } //end of outter if statement
      }
    });
    
    //View the pie data
    viewChart.addActionListener(new ActionListener() {
      @SuppressWarnings("unused")
      public void actionPerformed(ActionEvent e) {
        JFrame pieFrame = new JFrame();
        
        for (int i = 0; i < users.size(); i++) {
          
          System.out.println(userName);
          if (userName.equalsIgnoreCase(users.get(i).getUserId())) {
            JLabel limit = new JLabel("Overall calorie limit: " + users.get(i).getCalLimit());
            PieChart data = new PieChart();
            JFrame pieData = new JFrame();
            data.add(limit);
            pieData.setSize(300, 300);
            data.loadData(users.get(i));
            pieData.add(data);
            pieData.setVisible(true);
            break;
          }
          else {
            PieChart chart = new PieChart();
            
            pieFrame.setSize(300, 300);
            pieFrame.add(chart);
            pieFrame.setVisible(true);
            break;
          }
          
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
              
              userName = idName;
              passcode = pass;
              userIndex = j;
            }
            else {
              JOptionPane.showMessageDialog(null, "Sign-in unsuccessful", "Sign-in Error", 
                                                                    JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      }
    });
    //User logs out of the current session
    signOut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        userName = "";
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
