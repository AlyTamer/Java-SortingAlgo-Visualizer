import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    //class variables that other methods in this specific class can see
    //initializing an arraylist to be resizeable and having it work to count the steps of exectuion.
    ArrayList<int[]> steps = new ArrayList<>(); //an arraylist with forced data type of int
    int currentStep = 0;
    private String sortType;
    private final JLabel label;
    private final JFrame frame;
    private final JPanel panel;
    private final JButton button;
    private final JTextField arrtxt;
    private final JLabel dispArr;
    private final JComboBox<String> comboBox;
    private final JButton stepButton;
    BarPanel barPanel;

    // Constructor
    public GUI() {
        // Setting up the window
        frame = new JFrame("Sorting Visualizer");
        panel = new JPanel();
        //creating a new panel to organize UI elements
        panel.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        // Instructions text
        label = new JLabel("Enter numbers to sort (separate by commas, no spaces)");
        inputPanel.add(label);

        //user input field
        arrtxt = new JTextField(10);
        inputPanel.add(arrtxt);

        // drop down menu to select type of sort
        String[] options = {"Choose Sort Type", "Bubble sort", "InsertionSort", "Selection Sort"};
        comboBox = new JComboBox<>(options);
        comboBox.addActionListener(new ComboSort()); //creating a seperate event action listener for the dropdown menu
        comboBox.setEditable(false);
        inputPanel.add(comboBox);
        panel.add(inputPanel, BorderLayout.NORTH);

        // temp field that will later show the full sorted array
        dispArr = new JLabel("                ");
        inputPanel.add(dispArr);

        // Button to start sorting
        button = new JButton("Sort");
        button.addActionListener(new SortButton()); //seperate action listener event unaffected by the dropdown menu one
        panel.add(button, BorderLayout.SOUTH);

        barPanel = new BarPanel(); //importing the class that visualizes the data for me
        panel.add(barPanel, BorderLayout.CENTER);

        //button to make user walk through step-by-step
        stepButton = new JButton("Step");
        stepButton.addActionListener(new StepButton());
        inputPanel.add(stepButton, BorderLayout.SOUTH);

        //final window adjustments and default setups
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1200);
        frame.pack();
        frame.setVisible(true); // Make sure frame is visible
        frame.setLocationRelativeTo(null);


    }

    // drop box action listener that saves the type of sort to be used
    private class ComboSort implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //sets the sortype to the combobox option and forces it to be a string
            sortType = comboBox.getSelectedItem().toString();
        }
    }

    // Button action listener that starts the sort process
    private class SortButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //java attempts to do the following block of code, but does not break upon encoutnering a bug or error
            button.setVisible(false);
            try {
                // Split input text into an array of strings
                String[] sArr = arrtxt.getText().split(",");
                int[] numbers = new int[sArr.length];

                // Convert strings to integers
                for (int i = 0; i < sArr.length; i++) {
                    numbers[i] = Integer.parseInt(sArr[i]);
                }
                steps.clear();
                currentStep = 0;

                // Perform sorting based on the selected sort type
                switch (sortType) {
                    case "Bubble sort":
                        //state->steps.add(state.clone) is a function passed to the consumer as an argument
                        //it clones the current array and adds it to our arraylist steps
                        BubbleSort.sort(numbers, state -> steps.add(state.clone()), selectedIndex -> barPanel.setSelectedIndex(selectedIndex));
                        System.out.println("used Bubble sort");
                        break;
                    case "InsertionSort":
                        InsertionSort.sort(numbers, state -> steps.add(state.clone()), selectedIndex -> barPanel.setSelectedIndex(selectedIndex));
                        System.out.println("used InsertionSort");
                        break;
                    case "Selection Sort":
                        SelectionSort.sort(numbers, state -> steps.add(state.clone()), index -> barPanel.setSelectedIndex(index));                        System.out.println("used Selection sort");
                        break;
                    default:
                        dispArr.setText("Please select a valid sort type.");
                        return;
                }
                stepButton.doClick();
                //Convert sorted array to string
                //use string builder as it is more effecient for adding mutiple string elements to a string.
                //saves memory since normal strings are immutable and everytime we do a concatination a new memory block would have to be used.
                StringBuilder sorted = new StringBuilder();
                for (int i = 0; i < numbers.length; i++) {
                    sorted.append(numbers[i]);
                    if (i < numbers.length - 1) {
                        sorted.append(",");
                    }
                }

                // Display sorted array
                dispArr.setText("Sorted: " + sorted);

            }
            //if anypart of the above try fails. it will catch the error and do this code block instead
            //this specific error is for if a user enters a nun numerical character.
            catch (NumberFormatException ex) {
                dispArr.setText("Invalid input. Please enter integers.");
                button.setVisible(true);
            }

        }
    }

    //seperate action listener for the step button
    private class StepButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //checking if the steps are empty; usually this means an incorrect sort type was picked
            if (steps.isEmpty()) {
                dispArr.setText("Please select a valid sort type.");
                return;
            }
            //checks if the current step can even be done. if it returns false it means the sorting has finished or an index out of bounds error
            if (currentStep < steps.size()) {
                //set an array of numbers that is based on the current step
                int[] currentArray = steps.get(currentStep);
                barPanel.setNumbers(currentArray);//updating my barpanel visualizer with the current step
                barPanel.setSelectedIndex(currentStep);
                //creating a new cleaner string usng less memory by utilizing a stringbuilder
                StringBuilder stepDisplay = new StringBuilder();
                for (int i = 0; i < currentArray.length; i++) {
                    stepDisplay.append(currentArray[i]);
                    if (i < currentArray.length - 1) {
                        stepDisplay.append(", ");
                    }
                }

                // Updating the display component
                dispArr.setText("Step " + (currentStep + 1) + ": " + stepDisplay);

                // Move to the next step
                currentStep++;
            } else {
                dispArr.setText("Reached the end of sorting steps.");
            }
        }
    }

}
