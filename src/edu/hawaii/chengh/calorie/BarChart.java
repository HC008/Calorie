package edu.hawaii.chengh.calorie;

import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * Presents each day's calorie intake.
 * 
 * 
 * @author Hansen Cheng
 *
 */
public class BarChart extends JPanel {
  
  private static final long serialVersionUID = 1L;
  private DefaultCategoryDataset data;
  private JFreeChart barDisplay;
  
  /**
   * Construct chart.
   */
  public BarChart() {
    data = new DefaultCategoryDataset();
    data.setValue(0, "", "");
    
    barDisplay = ChartFactory.createBarChart("Weekly calories", "", "", data, 
                                             PlotOrientation.VERTICAL, true, true, false);
    
    
    CategoryPlot plot = (CategoryPlot) barDisplay.getPlot();
    plot.setRangeGridlinePaint(Color.BLACK);
    
    this.add(new ChartPanel(barDisplay));
    
  }
  
  /**
   * Loads the user's calorie intake per day.
   * 
   * @param weightWatcher - user.
   */
  public void loadBars(Avatar weightWatcher) {
    List<Integer> weekData = weightWatcher.getWeeklyCal();
    
    data.clear();
    
    data.setValue(weekData.get(0), "", "Sun.");
    data.setValue(weekData.get(1), "", "Mon.");
    data.setValue(weekData.get(2), "", "Tue.");
    data.setValue(weekData.get(3), "", "Wed.");
    data.setValue(weekData.get(4), "", "Thur.");
    data.setValue(weekData.get(5), "", "Fri.");
    data.setValue(weekData.get(6), "", "Sat.");
    
    barDisplay = ChartFactory.createBarChart("Weekly calories", "Day of week", "", data, 
                                                     PlotOrientation.VERTICAL, false, false, false);
    
    CategoryPlot plot = (CategoryPlot) barDisplay.getPlot();
    plot.setRangeGridlinePaint(Color.BLACK);
    repaint();
    
  }

}
