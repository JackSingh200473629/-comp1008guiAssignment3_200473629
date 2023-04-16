package ca.georgiancollege.comp1008winter2023gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import static java.util.spi.ToolProvider.findFirst;

public class Assignment3Controller {

    @FXML
    private Button addStudent;

    @FXML
    private Rectangle b1;

    @FXML
    private Rectangle b2;

    @FXML
    private Rectangle b3;

    @FXML
    private Rectangle b4;

    @FXML
    private Rectangle b5;

    @FXML
    private Rectangle b6;

    @FXML
    private Rectangle b7;

    @FXML
    private Rectangle b8;

    @FXML
    private Rectangle b9;

    @FXML
    private Label error;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l9;

    @FXML
    private TextField sName;

    @FXML
    private ComboBox<Color> sColor;

    @FXML
    public void initialize() {
        sColor.getItems().addAll(Color.AQUA, Color.DODGERBLUE, Color.LAVENDER, Color.TOMATO, Color.SEAGREEN, Color.SALMON, Color.NAVY, Color.MAGENTA, Color.LAWNGREEN);
        sColor.setValue(null);
        error.setText("");
    }

    @FXML
    public void onAddStudentClick(ActionEvent event) {
        String studentName = sName.getText();
        Color selectedColor = sColor.getValue();

        ArrayList<Label> studentNameLabels = new ArrayList<Label>();
        ArrayList<Rectangle> studentDeskBoxes = new ArrayList<Rectangle>();

        Set<Color> assignedDesks = new HashSet<>();
        Set<Label> assignedNames = new HashSet<>();
        Set<Color> usedColors = new HashSet<>();

        studentNameLabels.add(l1);
        studentNameLabels.add(l2);
        studentNameLabels.add(l3);
        studentNameLabels.add(l4);
        studentNameLabels.add(l5);
        studentNameLabels.add(l6);
        studentNameLabels.add(l7);
        studentNameLabels.add(l8);
        studentNameLabels.add(l9);

        studentDeskBoxes.add(b1);
        studentDeskBoxes.add(b2);
        studentDeskBoxes.add(b3);
        studentDeskBoxes.add(b4);
        studentDeskBoxes.add(b5);
        studentDeskBoxes.add(b6);
        studentDeskBoxes.add(b7);
        studentDeskBoxes.add(b8);
        studentDeskBoxes.add(b9);

        Color checkIfNull = sColor.getValue();
        if(checkIfNull == null){
            error.setText("ERROR: Please choose a color");
            return;
        }

        if(sName.getText().trim().isEmpty()){
            error.setText("ERROR: Student name field cannot be blank");
            return;
        }


        for(int i = 0; i < studentNameLabels.size(); i++){
            error.setText("");
            if (studentNameLabels.get(i).getText().equals(studentName)) {
                error.setText("ERROR: Student name already exists.");
                return;
            }
        }

        for (int i = 0; i < studentNameLabels.size(); i++) {
            if(studentNameLabels.get(i).getText().isEmpty()){
                assignedNames.add(studentNameLabels.get(i));
                usedColors.add((Color)studentDeskBoxes.get(i).getFill());
            }else if(studentNameLabels.get(i).getText().equals(studentName)){
                return;
            }else{
                assignedDesks.add((Color)studentDeskBoxes.get(i).getFill());
                usedColors.add((Color)studentDeskBoxes.get(i).getFill());
            }
        }

        if(!assignedNames.isEmpty()){
            if(usedColors.contains(selectedColor)) {
                error.setText("ERROR: This color has already been assigned");
                return;
            }
            Random random = new Random();
            Label randomLabel = assignedNames.stream().skip(random.nextInt(assignedNames.size())).findFirst().orElse(null);
            if(randomLabel != null){
                randomLabel.setText(studentName);
                assignedNames.add(randomLabel);
            }
            int assignedLabelIndex = studentNameLabels.indexOf(randomLabel);
            Rectangle emptyDesk = studentDeskBoxes.get(assignedLabelIndex);
            emptyDesk.setFill(selectedColor);
            assignedDesks.add(selectedColor);
        }

        int numStudents = 0;
        for (int i = 0; i < studentNameLabels.size(); i++) {
            if (!studentNameLabels.get(i).getText().isEmpty()) {
                numStudents++;
            }
        }
        if (numStudents == 9) {
            error.setTextFill(Color.GREEN);
            error.setText("Congrats! The class is full!");
        } else if (numStudents > 9) {
            error.setText("Warning: Cannot add more than 9 students.");
            return;
        }

        sColor.setValue(null);
    }
}
