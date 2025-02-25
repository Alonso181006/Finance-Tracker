package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import persistence.JsonWriter;

public class TestJsonWriter {
    JsonWriter testWriter1;
    JsonWriter testWriter2;
    UserFinancesList testFList;


    @BeforeEach
    void setup(){
        testWriter1 = new JsonWriter("./data/testWriterNoUser.json");
        testWriter2 = new JsonWriter("./data/testWriterAndrew.json");
        testFList = new UserFinancesList("Andrew");
        testFList.addFinances(new Asset("cash", 25.27));
        testFList.addFinances(new Liability("student loans", -120.0));
    }

    @Test
    public void noUser(){
        testWriter1.createWriter();
        testWriter1.write(testFList);
        testWriter2.closeWriter();
    }

    @Test
    public void onTopofUserData(){
        testFList.addFinances(new Asset("lottery", 1000.13));
        testFList.addFinances(new Liability("medical bills", -10330.21));
        testWriter2.createWriter();
        testWriter2.write(testFList);
        testWriter2.closeWriter();
        
        
    }
}
