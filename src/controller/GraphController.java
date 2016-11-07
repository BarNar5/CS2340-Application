package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

import model.Model;
import model.WaterQualityReport;


/**
 * The controller for the graph view
 */
public class GraphController {

    /**
     * a link back to the main application class
     */
    private MainApplication mainApplication;


    /** references to the widgets in the fxml file */
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private AnchorPane anchorPane;


    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Set the options used to display the graph.
     *
     * @param year the year of the reports to use in the graph
     * @param virus true if virus PPM is to be displayed in the graph
     * @param contaminant true if contaminant PPM is to be displayed in the graph
     */
    public void setGraphOptions(int year, boolean virus, boolean contaminant) {

        ObservableList<XYChart.Series<String, Number>> lineChartData = FXCollections.observableArrayList();

        lineChart.setTitle("Water Quality Graph, " + year);

        List<WaterQualityReport> reports = Model.getInstance().getQualityReportsByYear(year);

        if (virus) {
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Virus PPM");

            double[] monthPPMs = new double[12];
            int[] count = new int[12];

            for (WaterQualityReport report : reports) {
                monthPPMs[report.getDateMonth() - 1] += report.getVirusPPM();
                count[report.getDateMonth() - 1] ++;
            }
            for (int i = 0; i < 12; ++i) {
                if (count[i] == 0) {
                    count[i]++;
                }
                monthPPMs[i] /= count[i];
            }

            series1.getData().add(new XYChart.Data("Jan", monthPPMs[0]));
            series1.getData().add(new XYChart.Data("Feb", monthPPMs[1]));
            series1.getData().add(new XYChart.Data("Mar", monthPPMs[2]));
            series1.getData().add(new XYChart.Data("Apr", monthPPMs[3]));
            series1.getData().add(new XYChart.Data("May", monthPPMs[4]));
            series1.getData().add(new XYChart.Data("Jun", monthPPMs[5]));
            series1.getData().add(new XYChart.Data("Jul", monthPPMs[6]));
            series1.getData().add(new XYChart.Data("Aug", monthPPMs[7]));
            series1.getData().add(new XYChart.Data("Sep", monthPPMs[8]));
            series1.getData().add(new XYChart.Data("Oct", monthPPMs[9]));
            series1.getData().add(new XYChart.Data("Nov", monthPPMs[10]));
            series1.getData().add(new XYChart.Data("Dec", monthPPMs[11]));

            lineChartData.addAll(series1);
        }

        if (contaminant) {
            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Contaminant PPM");

            double[] monthPPMs = new double[12];
            int[] count = new int[12];

            for (WaterQualityReport report : reports) {
                monthPPMs[report.getDateMonth() - 1] += report.getContaminantPPM();
                count[report.getDateMonth() - 1] ++;
            }
            for (int i = 0; i < 12; ++i) {
                if (count[i] == 0) {
                    count[i]++;
                }
                monthPPMs[i] /= count[i];
            }

            series2.getData().add(new XYChart.Data("Jan", monthPPMs[0]));
            series2.getData().add(new XYChart.Data("Feb", monthPPMs[1]));
            series2.getData().add(new XYChart.Data("Mar", monthPPMs[2]));
            series2.getData().add(new XYChart.Data("Apr", monthPPMs[3]));
            series2.getData().add(new XYChart.Data("May", monthPPMs[4]));
            series2.getData().add(new XYChart.Data("Jun", monthPPMs[5]));
            series2.getData().add(new XYChart.Data("Jul", monthPPMs[6]));
            series2.getData().add(new XYChart.Data("Aug", monthPPMs[7]));
            series2.getData().add(new XYChart.Data("Sep", monthPPMs[8]));
            series2.getData().add(new XYChart.Data("Oct", monthPPMs[9]));
            series2.getData().add(new XYChart.Data("Nov", monthPPMs[10]));
            series2.getData().add(new XYChart.Data("Dec", monthPPMs[11]));

            lineChartData.addAll(series2);
        }

        lineChart.setData(lineChartData);
    }

    /**
     * Setup the main application link.
     *
     * @param mainApplication  a reference (link) to our main class
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }


    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     * Called when the user clicks back.
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.showApplicationScreen();
    }
}