import javax.swing.*;
import java.awt.*;
//extends jpanel so barpanel acts as a subclass that inherits all the properties of jpanel
public class BarPanel extends JPanel {
    private int[] numbers; //holds the current array
    private int selectedIndex = -1;
    private int comparedindex=-1;// index of the currently selected bar

    public BarPanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.pink);//sizing dimensions
    }
    //setter that changes the value of numbers and then redraws the entire window
    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
        repaint();
    }
    public void setComparedIndex(int index){
    this.comparedindex=index;
    repaint();
    }

    // Method to set the selected index
    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
        repaint();
    }

    @Override
    //method that draws the actual bars/graphs
    //is called when repaint(); is used
    //has an argument of a Graphics object, a java swing component
    protected void paintComponent(Graphics g) {
        //super invokes this method from the parent JPanel class. not the child BarPanel
        super.paintComponent(g); // ensures the panel is clear before adding the bars again.
        //checks if the numbers are initialized and has elements
        if (numbers != null && numbers.length > 0) {
            int panelHeight = getHeight();
            int panelWidth = getWidth();
            int barWidth = (int)(((double) panelWidth / numbers.length)*0.7999912); // width of each bar

            // find the maximum number to scale heights
            int maxNumber = 0;
            //enhanced for loop, python like syntax, for each num in numbers[]
            for (int num : numbers) {
                if (num > maxNumber) {
                    maxNumber = num;
                }
            }

            // Draw the bars
            for (int i = 0; i < numbers.length; i++) {
                //getting height based on value
                int height = (int) (((double) numbers[i] / maxNumber * panelHeight)*0.821); // scaling
                int x = i * barWidth; // x position
                int y = panelHeight - height; // y position assuming (0,0) is in the corner

                // changing color for the selected bar
                if (i == selectedIndex) {
                    g.setColor(Color.RED);
                } else if(i==comparedindex) {
                    g.setColor(Color.blue);
                }
                else{
                    g.setColor(Color.gray);
                }

                // filling in the bar
                g.fillRect(x, y, barWidth - 1, height); // draws a rectangle for each selected index
                g.setColor(Color.BLACK); // makes the color black
                g.drawString(String.valueOf(numbers[i]), x + barWidth / 4, y - 5); // adds the numerical value above the bar
            }
        }
    }
}
