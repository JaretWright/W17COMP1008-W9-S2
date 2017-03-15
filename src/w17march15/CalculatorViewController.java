package w17march15;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class CalculatorViewController implements Initializable {

    @FXML private Button oneButton;
    @FXML private Button twoButton;
    @FXML private Button threeButton;
    @FXML private Button fourButton;
    @FXML private Button fiveButton;
    @FXML private Button sixButton;
    @FXML private Button sevenButton;
    @FXML private Button eightButton;
    @FXML private Button nineButton;
    @FXML private Button zeroButton;
    @FXML private Button decimalButton;
    @FXML private Button multiplyButton;
    @FXML private Button divideButton;
    @FXML private Button addButton;
    @FXML private Button subtractButton;
    @FXML private Button equalButton;
    @FXML private TextField display;
    @FXML private Label numberStackLabel;
            
    private ArrayList<String> numberStack;
    private boolean overWriteNumberInDisplay;
    
    
    /**
     * When the number buttons are pushed, this method will update the 
     * text display
     */
    public void numberButtonPushed(ActionEvent event)
    {
       String buttonValue = ((Button)event.getSource()).getText();      
       
       //check if 1 decimal is already in the number
       if (buttonValue.equals(".") && display.getText().contains("."))
            {}//do nothing
       else if (overWriteNumberInDisplay)
       {
           display.setText(buttonValue);
           overWriteNumberInDisplay=false;
       }           
       else
           display.setText(display.getText() + buttonValue);
    }
    
    /**
     * This method will check if it is the first number in an operation.  If it is,
     * it will set the firstNumber instance variable and the operator
     * 
     */
    public void operatorButtonPushed(ActionEvent event)
    {
        String operator = ((Button)event.getSource()).getText();
        
        numberStack.add(display.getText()); //push the number on the stack
        numberStack.add(operator);          //push the operator on the stack
        numberStackLabel.setText(formatNumberStack());
        double result = calculateStack();                
        display.setText(Double.toString(result));
        overWriteNumberInDisplay = true;    
        
        if (operator.equals("="))
        {
            numberStack = new ArrayList<>();
            numberStackLabel.setText("");
        }
            
    }
    
    
    
    public double calculateStack()
    {        
        double result = 0;
        double num1=0;
        
        //get the first number in the stack
        if (!numberStack.isEmpty())
            num1 = Double.parseDouble(numberStack.get(0));
        
        double num2;
        String operator = "=";
        
        for(int i=1; i<numberStack.size(); i++)
        {
            String element = numberStack.get(i);
            
            //check if it is a number or an operator
            if (element.matches("[0-9]"))
            {
                num2 = Double.parseDouble(element);                
                result = calculate(num1, operator, num2);
                num1 = result;
            }                
            else
                operator = element;
        }
        return result;
    }       
    
    
    public double calculate(double num1, String operator, double num2)
    {
    if (operator.equals("+"))
        return num1+num2;
    else if (operator.equals("-"))
        return num1-num2;
    else if (operator.equals("*"))
        return num1*num2;
    else if (operator.equals("/"))
        return num1/num2;
    else
        return 0;
}
    
    /**
     * This method will return the elements of an
     * ArrayList as a formatted String
     */
    public String formatNumberStack()
    {
        String result = "";
        
        for (String element:numberStack)
        {
            result += String.format("%s ",element);            
        }
        return result;
        
    }
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numberStack = new ArrayList<>();
        display.setText("0");
        overWriteNumberInDisplay = true;
        numberStackLabel.setText("");
    }    
    
}
