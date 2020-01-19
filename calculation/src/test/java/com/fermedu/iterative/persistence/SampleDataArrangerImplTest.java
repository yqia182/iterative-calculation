package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.SampleData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 17:11
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class SampleDataArrangerImplTest {

    @Autowired
    @Qualifier(value = "sampleDataArrangerImpl")
    private SampleDataArranger sampleDataArranger;

    @Test
    void readSampleNameList() {
        List<String> resultList = sampleDataArranger.readSampleNameList();
        System.out.println(resultList);
        System.out.println(resultList.size());
        Assert.assertEquals(80, resultList.size());
    }

    @Test
    void readOneSampleDataSeriesByName() {
        SampleData result = sampleDataArranger.readOneSampleDataSeriesByName("1");
        System.out.println(result);
        Assert.assertEquals(result.getXValueList().size(), result.getYValueList().size());

    }
}