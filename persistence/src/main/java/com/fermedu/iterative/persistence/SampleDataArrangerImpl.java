package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.CsvWorksheet;
import com.fermedu.iterative.dao.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 16:27
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class SampleDataArrangerImpl implements SampleDataArranger {

    private CsvWorksheet csvWorksheet;

    private List<SampleData> sampleDataList = new ArrayList<>();

    @Autowired
    private DataAccessor dataAccessor;

    private void init(String csvFilePath) {
        if (this.csvWorksheet == null) {
            log.info("Initializing csv worksheet from CsvAccessor");
            csvWorksheet = dataAccessor.readCvsWorksheet(csvFilePath);
        }
        if (this.sampleDataList == null || this.sampleDataList.size() <= 0) {
            this.initSampleDataList();
        }
    }

    private void initSampleDataList() {

        final String xname = this.csvWorksheet.getFirstIDRow().get(0);
        final List<Double> xValue = this.getOneValueColumnByIndex(0);

        for (int columnIndex=1; columnIndex < this.csvWorksheet.getFirstIDRow().size(); columnIndex++) {
            SampleData eachSampleData = new SampleData();
            eachSampleData.setXname(xname);
            eachSampleData.setXValueList(xValue);
            eachSampleData.setYname(this.csvWorksheet.getFirstIDRow().get(columnIndex));
            eachSampleData.setYValueList(this.getOneValueColumnByIndex(columnIndex));

            this.sampleDataList.add(eachSampleData);
        }

    }

    private List<Double> getOneValueColumnByIndex(int columnIndex) {
        List<Double> columnValueList = new ArrayList<>();
        for (List<Double> eachValueRow : this.csvWorksheet.getValueRows()) {
            columnValueList.add(eachValueRow.get(columnIndex));
        }
        return columnValueList;
    }

    @Override
    public List<String> readSampleNameList(String observedDataCsvFilePath) {
        this.init(observedDataCsvFilePath);
        List<String> sampleNameList = new ArrayList<>();
        this.sampleDataList.forEach(each->sampleNameList.add(each.getYname()));

        return sampleNameList;
    }

    @Override
    public SampleData readOneSampleDataSeriesByName(String observedDataCsvFilePath, String yname) {
        this.init(observedDataCsvFilePath);
        for (SampleData each : this.sampleDataList) {
            if (each.getYname().equals(yname)) {
                return each;
            }
        }

        return null;
    }
}
