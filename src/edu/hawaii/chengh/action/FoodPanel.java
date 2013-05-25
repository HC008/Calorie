package edu.hawaii.chengh.action;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * User provides information to create an avatar.
 * 
 * @author Hansen Cheng
 *
 */
public class FoodPanel extends JPanel {
  
  private static final long serialVersionUID = 1L;

  private JLabel food = new JLabel("Food: "), kCal = new JLabel("Calorie: ");
  private JTextField foodName = new JTextField(15), foodCal = new JTextField(5);
  
     
  /**
   * Construct panel and attributes.
   */
  public FoodPanel() {
    this.setLayout(new GridLayout(2, 0));
    this.add(food);
    this.add(foodName);
    this.add(kCal);
    this.add(foodCal);
  }
  
  /**
   * Retrive the name of food the user ate.
   * 
   * @return foodName - type of food.
   */
  public JTextField getFoodName() {
    return foodName;
  }

  /**
   * Replace previous name of food with current.
   * 
   * @param foodName - type of food.
   */
  public void setFoodName(JTextField foodName) {
    this.foodName = foodName;
  }

  /**
   * Retrieve amount of calories consumed for particular food.
   * 
   * @return foodCal - amount of energy.
   */
  public JTextField getFoodCal() {
    return foodCal;
  }

  /**
   * Replace the previous amount of calories with current.
   * 
   * @param foodCal - amount of energy.
   */
  public void setFoodCal(JTextField foodCal) {
    this.foodCal = foodCal;
  }

  
  /**
   * A string of the information together.
   * 
   * @return information.
   */
  public String fieldInfo() {
    return getFoodName().getText().trim() + "  " + getFoodCal().getText().trim();
  }
  
  
}
