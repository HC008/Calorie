package edu.hawaii.chengh.calorie;

import java.util.ArrayList;
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
  List<Integer> weeklyCal = new ArrayList<Integer>();
  int day = 0;
  int week = 0;
  
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
    this.inTake = inTake;
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
   * The day the user signed on to use the program.
   * 
   * @return day - value from Calendar class indicating the day of week.
   */
  public int getDay() {
    return day;
  }
  
  /**
   * Current day when the user signs on.
   * 
   * @param day - value from Calendar class indicating the day of week.
   */
  public void setDay(int day) {
    this.day = day;
  }
  
  /**
   * Seven days of calorie intake.
   * 
   * @return weeklyCal - amount of calorie consumed per day.
   */
  public List<Integer> getWeeklyCal() {
    return weeklyCal;
  }
  
  /**
   * Add in another day of calorie.
   * 
   * @param weeklyCal - amount of calorie consumed per day
   */
  public void setWeeklyCal(List<Integer> weeklyCal) {
    this.weeklyCal = weeklyCal;
  }
  
  /**
   * The week the user logged on.
   * 
   * @return week - value of the calendar week.
   */
  public int getWeek() {
    return week;
  }
  
  /**
   * Changes week the user logged on. 
   * 
   * @param week - value of the calendar week.
   */
  public void setWeek(int week) {
    this.week = week;
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
