package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class QualityReportTest {

    Model model = Model.getInstance();

    @Before
    public void setUp() {
        //ObservableList<WaterQualityReport> list = FXCollections.observableArrayList();
        WaterQualityReport report = new WaterQualityReport(1234, "Faisal");
        WaterQualityReport report2 = new WaterQualityReport(5678, "Gedi");
        WaterSourceReport report3 = new WaterSourceReport(345, "Bob");
        //list.add(report);
        model.addReport(report);
        model.addReport(report2);
        model.addReport(report3);
    }

    @Test
    public void testGetQualityReport() {
        if (model.getQualityReports() == null || model.getQualityReports().size() == 0) {
            Assert.fail();
        }
        WaterQualityReport tempReport = model.getQualityReports().get(0);
        Assert.assertEquals(model.getQualityReports().size(), 2);
        Assert.assertEquals(tempReport.getReportID(), 1234);
        Assert.assertEquals(tempReport.getReporterUserName(), "Faisal");
    }
}
