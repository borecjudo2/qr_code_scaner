package by.judoka.qr.charts;

import by.judoka.qr.Analyzer;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Named
@RequestScoped
public class ChartJsView implements Serializable {

    @Inject
    private Analyzer analyzer;

    private DonutChartModel donutModelData;

    private DonutChartModel donutModelDataRotate;

    private DonutChartModel donutModelDataGaussian;

    private DonutChartModel donutModelDataScale;

    private DonutChartModel donutModelDataBarrelInvert;

    private DonutChartModel donutModelDataBarrel;

    @PostConstruct
    public void init() {
        DonutModelCreator donutModelCreator = new DonutModelCreator(analyzer);
        initDonutModelData(donutModelCreator);
        initDonutModelDataRotate(donutModelCreator);
        initDonutModelDataGaussian(donutModelCreator);
        initDonutModelDataScale(donutModelCreator);
        initDonutModelDataBarrelInvert(donutModelCreator);
        initDonutModelDataBarrel(donutModelCreator);
    }

    private void initDonutModelData(DonutModelCreator creator){
        Map<String, Integer> map = creator.getValueForDonut();
        donutModelData = createDonutModel(map.get(DonutModelCreator.KEY_READ), map.get(DonutModelCreator.KEY_NOT_READ));
    }

    private void initDonutModelDataRotate(DonutModelCreator creator){
        Map<String, Integer> map = creator.getValueForDonutRotate();
        donutModelDataRotate = createDonutModel(map.get(DonutModelCreator.KEY_READ), map.get(DonutModelCreator.KEY_NOT_READ));
    }

    private void initDonutModelDataGaussian(DonutModelCreator creator){
        Map<String, Integer> map = creator.getValueForDonutGaussian();
        donutModelDataGaussian = createDonutModel(map.get(DonutModelCreator.KEY_READ), map.get(DonutModelCreator.KEY_NOT_READ));
    }

    private void initDonutModelDataScale(DonutModelCreator creator){
        Map<String, Integer> map = creator.getValueForDonutScale();
        donutModelDataScale = createDonutModel(map.get(DonutModelCreator.KEY_READ), map.get(DonutModelCreator.KEY_NOT_READ));
    }

    private void initDonutModelDataBarrelInvert(DonutModelCreator creator){
        Map<String, Integer> map = creator.getValueForDonutBarrelInvert();
        donutModelDataBarrelInvert = createDonutModel(map.get(DonutModelCreator.KEY_READ), map.get(DonutModelCreator.KEY_NOT_READ));
    }

    private void initDonutModelDataBarrel(DonutModelCreator creator){
        Map<String, Integer> map = creator.getValueForDonutBarrel();
        donutModelDataBarrel = createDonutModel(map.get(DonutModelCreator.KEY_READ), map.get(DonutModelCreator.KEY_NOT_READ));
    }

    private DonutChartModel createDonutModel(Integer read, Integer notRead) {
        DonutChartModel donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(read);
        values.add(notRead);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Read");
        labels.add("Not read");
        data.setLabels(labels);
        donutModel.setData(data);

        return donutModel;
    }

}