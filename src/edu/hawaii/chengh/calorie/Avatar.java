package edu.hawaii.chengh.calorie;

import java.util.List;


/**
 * User profile for keeping track of their calories.
 * 
 * @author Hansen Cheng
 *
 */
public class Avatar implements java.io.Serializable {
  
  private static final long serialVersionUID = 1L;
  String name;
  int age;
  int calLimit;
  String userId;
  String passId;
  int inTake = 0;
  List<String> foodEaten;
  
  /**
   * Creating the profile.
   * 
   * @param name - user's name.
   * @param age - how old the user.
   * @param calLimit - amount of energy a user can take in on a daily basis.
   * @param userId - code name of profile.
   * @param passId - security feature.
   */
  public Avatar(String name, int age, int calLimit, String userId, String passId) {
    this.name = name;
    this.age = age;
    this.calLimit = calLimit;
    this.userId = userId;
    this.passId = passId;
    
  }

  /**
   * Retrieve's name of the user.
   * 
   * @return name - the name the user goes by.
   */
  public String getName() {
    return name;
  }
  
  /**
   * Replaces user's current name on system.
   * 
   * @param name - user's new name.
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Retrieves user's current age.
   * 
   * @return age - user's age.
   */
  public int getAge() {
    return age;
  }
  
  /**
   * Replace's user's age as user gets older.
   * 
   * @param age - user's new age.
   */
  public void setAge(int age) {
    this.age = age;
  }
  
  /**
   * Retrieves amount of calories the user should intake.
   * 
   * @return calLimit - amount of calories.
   */
  public int getCalLimit() {
    return calLimit;
  }
  
  /**
   * Increase or decrease of user's calorie limit.
   * 
   * @param calLimit - amount of calories.
   */
  public void setCalLimit(int calLimit) {
    this.calLimit = calLimit;
  }
  
  /**
   * Profile user id.
   * 
   * @return userId - user profile identification.
   */
  public String getUserId() {
    return userId;
  }
  
  /**
   * Replaces current user profile identification.
   * 
   * @param userId - user profile identification.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  /**
   * Retrieves user profile password.
   * 
   * @return passId - password.
   */
  public String getPassId() {
    return passId;
  }
  
  /**
   * Replaces current profile password.
   * 
   * @param passId - password.
   */
  public void setPassId(String passId) {
    this.passId = passId;
  }
  
  /**
   * Retrieve amount of calories the user consumed.
   * 
   * @return inTake - amount of calories.
   */
  public int getInTake() {
    return inTake;
  }
  
  
  /**
   * Add on newest amount of calories to previous amount.
   * 
   * @param inTake - additional amount of calories.
   */
  public void setInTake(int inTake) {
    this.inTake += inTake;
  }
  
  /**
   * Retrieve the list of foods.
   * 
   * @return foodEaten - list of foods the user ate.
   */
  public List<String> getFoodEaten() {
    return foodEaten;
  }
  
  /**
   * Add list of foods to user profile to be ready for serialization.
   * 
   * @param foodEaten - list of foods the user ate.
   */
  public void setFoodEaten(List<String> foodEaten) {
    this.foodEaten = foodEaten;
  }

  /**
   * All of the user's current information on system. 
   * 
   * @return infos - user's current information.
   */
  public String information() {
    String infos = getName() + " " + Integer.toString(getAge()) + " " + 
                   Double.toString(getCalLimit()) + " " + getUserId() + " " +
                   getPassId();
    
    return infos;
  }
  
}
