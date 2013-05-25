package edu.hawaii.chengh.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import edu.hawaii.chengh.calorie.Avatar;

/**
 * Performs miscellaneous actions.
 * 
 * @author Hansen Cheng
 *
 */
public class Processor {
  
  /**
   * Construct the class.
   */
  public Processor() {
    //Serves in processing the action for the program.
  }
  /**
   * Transfer user's name to a file.
   * 
   * @param user - person utilizing the app.
   * @throws IOException - exception.
   */
  public void writeToFile(Avatar user) throws IOException {
    File names = new File("../calorie-ver-2/src/Names.txt");
    PrintWriter writer = new PrintWriter(new FileWriter(names, true));
  
    writer.println(user.getName().trim());
    writer.flush();
    writer.close();
  }
  
  /**
   * Reads in the filenames currently on directory.
   * 
   * @return fileNames - list of file names.
   * @throws IOException - exception.
   */
  public List<String> readInFile() throws IOException {
    
    List<String> fileNames = new ArrayList<String>();
    BufferedReader nameFile = new BufferedReader(new FileReader("C:/Users/HC0113/Documents/" + 
                                                                    "calorie-ver-2/src/Names.txt"));
    String title = "";
    
    while ((title = nameFile.readLine()) != null) {
      fileNames.add(title);
    }
    
    nameFile.close();
    return fileNames;
  }
  
  /**
   * Writes information about the object to file.
   * 
   * @param user - person using this app.
   * @throws FileNotFoundException - file is not on server.
   * @throws IOException - exception.
   */
  public void serialData(Avatar user) throws FileNotFoundException, IOException {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(user.getName() + ".ser"));
    
    out.writeObject(user);
    out.close();
  }
  
  
  /**
   * Decode the object's information.
   * 
   * @param name - the user's name.
   * @throws IOException - exception.
   * @return people - the information of the user.
   */
  public Avatar deserialData(String name) throws IOException {
    
    FileInputStream in = new FileInputStream(name + ".ser");
    ObjectInputStream inPerson = new ObjectInputStream(in);
    Avatar people = null;
    
    try {
     
      people = (Avatar) inPerson.readObject();
      
      return people;
    }
    catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    in.close();
    return null;
  }
  
  /**
   * Transfer information of what the user ate to a file.
   * 
   * @param name - user's name for the file.
   * @param foods - the list of the foods the user ate.
   * @throws IOException - exception.
   */
  public void writeFood(String name, List<String> foods) throws IOException {
    File foodFile = new File(name + "_Food.txt");
    PrintWriter printer = new PrintWriter(new FileWriter(foodFile, false));
    
    for (int i = 0; i < foods.size(); i++) {
      if (i == foods.size() - 1) {
        printer.print(foods.get(i));
      }
      else {
        printer.println(foods.get(i));
      }
    }
    
    printer.close();
  }
}
