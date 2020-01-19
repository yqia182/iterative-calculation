package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.CsvWorksheet;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private void init() {
        if (this.csvWorksheet == null) {
            log.info("Initializing csv worksheet from CsvAccessor");
            csvWorksheet = dataAccessor.readCvsWorksheet();
        }
        if (this.sampleDataList == null || this.sampleDataList.size() <= 0) {
            this.initSampleDataList();
        }
    }

    private void initSampleDataList() {

        final String xName = this.csvWorksheet.getFirstIDRow().get(0);
        final List<Double> xValue = this.getOneValueColumnByIndex(0);

        for (int columnIndex=1; columnIndex < this.csvWorksheet.getFirstIDRow().size(); columnIndex++) {
            SampleData eachSampleData = new SampleData();
            eachSampleData.setXName(xName);
            eachSampleData.setXValueList(xValue);
            eachSampleData.setYName(this.csvWorksheet.getFirstIDRow().get(columnIndex));
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
    public List<String> readSampleNameList() {
        this.init();
        List<String> sampleNameList = new ArrayList<>();
        this.sampleDataList.forEach(each->sampleNameList.add(each.getYName()));

        return sampleNameList;
    }

    @Override
    public SampleData readOneSampleDataSeriesByName(String yName) {
        this.init();
        for (SampleData each : this.sampleDataList) {
            if (each.getYName().equals(yName)) {
                return each;
            }
        }

        return null;
    }
}
