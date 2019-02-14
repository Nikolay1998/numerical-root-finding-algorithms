package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    LineChart<Number,Number> funcChart =
            new LineChart<Number,Number>(xAxis,yAxis);
    @FXML
    TextField fromTextField;
    @FXML
    TextField toTextField;
    @FXML
    TextField epsilonTextField;
    @FXML
    TextField deltaTextField;
    @FXML
    TextField approximationTextField;
    @FXML
    TextArea logTextArea;

    private XYChart.Series graphSeria = new XYChart.Series();

    public void calculateClick(javafx.event.ActionEvent actionEvent) {
        Logger.initialization(logTextArea);
        double fromVal = Double.parseDouble(fromTextField.getText());
        double toVal = Double.parseDouble(toTextField.getText());
        double epsilon = Double.parseDouble(epsilonTextField.getText());
        double delta = Double.parseDouble(deltaTextField.getText());
        double approx = Double.parseDouble(approximationTextField.getText());

        draw(fromVal, toVal);
        MyFunction.dichotomy(fromVal,toVal, epsilon, delta);
        MyFunction.newton(approx, epsilon);
        Logger.logTextArea.appendText("\n\n");
    }

    public void draw(double fromVal, double toVal){
        funcChart.getData().clear();
        MyFunction.chartCalculate(fromVal,toVal);
        for(Point p : MyFunction.values){
            graphSeria.getData().add(new XYChart.Data(p.getX(), p.getY()));
        }
        funcChart.getData().add(graphSeria);
        funcChart.setCreateSymbols(false);
        funcChart.setLegendVisible(false);
        graphSeria = new XYChart.Series();
    }
}
