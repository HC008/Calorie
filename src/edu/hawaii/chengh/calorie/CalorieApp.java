package edu.hawaii.chengh.calorie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
                    viewWeek = new JMenuItem("Weekly Calories"),
                    viewFood = new JMenuItem("Food List"),
                    signIn = new JMenuItem("Sign-In"),
                    signOut = new JMenuItem("Sign-Out"),
                    delete = new JMenuItem("Delete Profile");
  
  private List<Avatar> users = new ArrayList<Avatar>();
  private List<String> meals = new ArrayList<String>();
  private List<String> userTitle = new ArrayList<String>();
  private List<Integer> weekCal =  new ArrayList<Integer>();
  private Processor process = new Processor();
  private String userId = "", passcode = "";
  private int userIndex = 0;
  //Signal to show a chart if user doesn't have data
  private int signal = 0;
  
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
    
    viewWeek.setPreferredSize(new Dimension(250, 20));
    viewWeek.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.ALT_MASK));
    
    signIn.setPreferredSize(new Dimension(250, 20));
    signIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
    
    signOut.setPreferredSize(new Dimension(250, 20));
    signOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
    
    delete.setPreferredSize(new Dimension(250, 20));
    delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
    
    menuFile.add(create);
    menuFile.add(edit);
    menuFile.add(food);
    menuFile.add(delete);
    menuView.add(viewChart);
    menuView.add(viewFood);
    menuView.add(viewWeek);
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
      if (!process.readInFile("Names.txt").isEmpty()) {
        userTitle = process.readInFile("Names.txt");
        
        for (int i = 0; i < userTitle.size(); i++) {
          Avatar people = process.deserialData(userTitle.get(i));
            
          users.add(people);
        } //end of for loop
      } //end of if statement
    }
    catch (IOException e2) {
      JOptionPane.showMessageDialog(null, "Object construction failure", 
                                      "Failure", JOptionPane.ERROR_MESSAGE);
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
          Calendar date = Calendar.getInstance(Locale.US);
          int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
          
          Avatar user = new Avatar(info[0], Integer.parseInt(info[1]), 
                                   Integer.parseInt(info[2]), info[3], password);
          
          userId = info[3];
          passcode = password;
          user.setDay(dayOfWeek);
          users.add(user);
          userIndex = users.size() - 1;
          
          for (int i = 0; i < 7; i++) {
            weekCal.add(i, 0);
          }
          
          user.setWeeklyCal(weekCal);
          
          try {
            process.serialData(user);
            process.writeNames(user);
          }
          catch (FileNotFoundException e1) {
            missingFile();
          }
          catch (IOException e1) {
            inputError();
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
              missingFile();
            }
            catch (IOException x) {
              inputError();
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
          Calendar date = Calendar.getInstance(Locale.US);
          int day = date.get(Calendar.DAY_OF_WEEK);
          String foodEaten = foodPanel.fieldInfo();
          meals.add(foodEaten);
          
          
          if (userId.equalsIgnoreCase(users.get(userIndex).getUserId()) &&
              passcode.equals(users.get(userIndex).getPassId())) {
 
            String[] splitted = foodPanel.fieldInfo().split("  ");
            //Add amount of calories the user consumed 
            users.get(userIndex).setInTake(Integer.parseInt(splitted[1]));
            weekCal.set(day - 1, weekCal.get(day - 1) + Integer.parseInt(splitted[1]));
            //Allows user to view their calories
            signal = 1;
            try {
              users.get(userIndex).setFoodEaten(meals);
              process.serialData(users.get(userIndex));
            }
            catch (IOException x) {
              inputError();
            }
          } //end of inner if statement
        } //end of outer if statement
      } //end of actionPerformed method
    });
    
    //View the pie data
    viewChart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFrame pieFrame = new JFrame();
        
        if (userId.equalsIgnoreCase(users.get(userIndex).getUserId()) &&
            passcode.equalsIgnoreCase(users.get(userIndex).getPassId()) &&
                                                             signal != 0) {
          
          JLabel limit = new JLabel("Overall calorie limit: " + 
                                          users.get(userIndex).getCalLimit());
          PieChart data = new PieChart();
          
          data.add(limit);
          pieFrame.setSize(300, 300);
          data.loadData(users.get(userIndex));
          pieFrame.add(data);
          pieFrame.setVisible(true);
        }
        else {
          PieChart chart = new PieChart();
    
          pieFrame.setSize(300, 300);
          pieFrame.add(chart);
          pieFrame.setVisible(true);
        }
      }
    });
    
    //View the amount of calories the user consumed during the week
    viewWeek.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFrame barFrame = new JFrame();
        
        if (userId.equalsIgnoreCase(users.get(userIndex).getUserId()) &&
            passcode.equalsIgnoreCase(users.get(userIndex).getPassId()) &&
                                                             signal != 0) {
          BarChart barData = new BarChart();
          
          barFrame.setSize(500,500);
          barData.loadBars(users.get(userIndex));
          barFrame.add(barData);
          barFrame.setVisible(true);
        }
        else {
          
          BarChart bar = new BarChart();
        
          barFrame.setSize(500, 500);
          barFrame.add(bar);
          barFrame.setVisible(true);
        }
      }
    });
    
    //View the list of food and its calories
    viewFood.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        area.setText("");
        
        for (int i = 0; i < meals.size(); i++) {
          String[] splitted = meals.get(i).split("  ");
          area.append("Food: " + splitted[0] + "\n" + "Calorie: " + splitted[1] + "\n\n");
        }
      }
    });
    
    //User logs in
    signIn.addActionListener(new ActionListener() {
      @SuppressWarnings("unused")
      public void actionPerformed(ActionEvent e) {
        
        int valueFour = JOptionPane.showConfirmDialog(null, logIn, "Logging In",
                                                         JOptionPane.OK_CANCEL_OPTION);
        
        if (valueFour == JOptionPane.OK_OPTION) {
          String idName = logIn.fieldInfo();
          String pass = new String(logIn.secureCode());
          
          for (int j = 0; j < users.size(); j++) {
            if (idName.equalsIgnoreCase(users.get(j).getUserId()) &&
                  pass.equalsIgnoreCase(users.get(j).getPassId())) {
              
              area.setText("");
              area.append("Welcome. Sign-in successful.\n");
              
              //Stores the necessary information to be used after user signed-in
              userId = idName;
              passcode = pass;
              userIndex = j;
              signal = 1;
              meals = users.get(userIndex).getFoodEaten();
              weekCal = users.get(userIndex).getWeeklyCal();
              
              checkDate();
              checkWeek();
              
              break;
            }
            else {
              JOptionPane.showMessageDialog(null, "Sign-in unsuccessful", "Sign-in Error", 
                                                                    JOptionPane.ERROR_MESSAGE);
              break;
            } //end of inner if-else statement
          } //end of for-loop
        } //end of outer if statement
      } //end of actionPerformed method
    });
    
    //User logs out of the current session
    signOut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        area.setText("");
        area.append("Signed-out");
        
        try {
          process.serialData(users.get(userIndex));
        }
        catch (FileNotFoundException e1) {
          missingFile();
        }
        catch (IOException e1) {
          inputError();
        }
        
        //Reset after user signs out
        userId = "";
        passcode = "";
        meals.clear();
        signal = 0;
        weekCal.clear();
      }
    });
    
    delete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
       int valueFive = JOptionPane.showConfirmDialog(null, "Delete profile. You sure?",
                                                "Delete Profile", JOptionPane.OK_CANCEL_OPTION);
       
       if (valueFive == JOptionPane.OK_OPTION) {
         try {
          process.deleteFile(users.get(userIndex).getName());
        }
        catch (IOException e1) {
          inputError();
        } //end of try-catch
       } //end of if statement
      } //end of method
    });
    
    //Saves data if user just closes application
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        
        try {
          process.serialData(users.get(userIndex));
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        catch (FileNotFoundException e1) {
          missingFile();
        }
        catch (IOException e1) {
          inputError();
        }
        
      }
    });
    
    this.add(mainPanel);
    this.setTitle("Calorie App");
    this.setSize(new Dimension(900, 600));
    this.setVisible(true);
  }
  
  /**
   * Brings up error message for missing file.
   */
  public void missingFile() {
    JOptionPane.showMessageDialog(null, "File not found", 
                                  "No File", JOptionPane.ERROR_MESSAGE);
  }
  
  /**
   * Brings up error message for input or output error.
   */
  public void inputError() {
    JOptionPane.showMessageDialog(null, "Error with input or output", 
                                  "Input, Output Error", JOptionPane.ERROR_MESSAGE);
  }
  
  /**
   * Checks the date to ensure user's daily intake of calories
   * will be set back to zero.
   */
  public void checkDate() {
    Calendar date = Calendar.getInstance(Locale.US);
    
    int day = 0;
    
    switch (date.get(Calendar.DAY_OF_WEEK)) {
    
      case 1:
        day = 1; //Sunday
        break;
      case 2:
        day = 2; //Monday
        break;
      case 3:
        day = 3; //Tuesday
        break;
      case 4:
        day = 4; //Wednesday
        break;
      case 5:
        day = 5; //Thursday
        break;
      case 6:
        day = 6; //Friday
        break;
      case 7:
        day = 7; //Saturday
        break;
      default:
        System.out.println("No such day");
        break;
    }
    
    //Set daily calories to zero
    if (users.get(userIndex).getDay() != day) {
      weekCal.set(users.get(userIndex).getDay() - 1, users.get(userIndex).getInTake());
      users.get(userIndex).setWeeklyCal(weekCal);
      users.get(userIndex).setInTake(0);
      users.get(userIndex).setDay(day);
    }
    
  }
  
  /**
   * Check the previous week the user logged in. 
   * Ensure that user will see new set of weekly calorie
   * intake. 
   */
  public void checkWeek() {
    Calendar date = Calendar.getInstance(Locale.US);
    
    if (users.get(userIndex).getWeek() != date.get(Calendar.WEEK_OF_MONTH)) {
      for (int i = 0; i < weekCal.size(); i++) {
        weekCal.set(i, 0);
      }
      
      users.get(userIndex).setWeek(date.get(Calendar.WEEK_OF_MONTH));
      users.get(userIndex).setWeeklyCal(weekCal);
    }
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
