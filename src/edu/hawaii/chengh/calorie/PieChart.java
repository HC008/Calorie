package edu.hawaii.chengh.calorie;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import edu.hawaii.chengh.calorie.Avatar;

/**
 * Display users calorie intake and limit amount.
 * 
 * @author Hansen Cheng
 *
 */
public class PieChart extends JPanel {


  private static final long serialVersionUID = 1L;
  private DefaultPieDataset data;
  private JFreeChart pieDisplay;

  
  public PieChart() {
    data = new DefaultPieDataset();
    data.setValue("", 0);
    
    pieDisplay = ChartFactory.createPieChart("Amount of Calories", data, false, false, false);
    
    PiePlot plot = (PiePlot) pieDisplay.getPlot();
    plot.setCircular(true);
    
    this.add(new ChartPanel(pieDisplay));
  }
  
  
  
  /**
   * User views data of calorie limit and approximate intake.
   * 
   * @param weightWatcher - user.
   * @return 
   */
  public void loadData(Avatar weightWatcher) {
    int remaining = weightWatcher.getCalLimit() - weightWatcher.getInTake();
    data.clear();
    data.setValue("Intake " + weightWatcher.getInTake() , new Integer(weightWatcher.getInTake()));
    data.setValue("Remaining Limit " + remaining, new Integer(remaining));
    
    pieDisplay = ChartFactory.createPieChart("Amount of Calories", data, true, true, false);
    
    PiePlot plot = (PiePlot) pieDisplay.getPlot();
    plot.setCircular(true);
   
    repaint();
  } 
}
