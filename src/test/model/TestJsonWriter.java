package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistence.JsonReader;
import persistence.JsonWriter;

public class TestJsonWriter {
    JsonWriter testWriter;
    JsonReader testReader;
    UserFinancesList testFList;


    @BeforeEach
    void setup(){
        testFList = new UserFinancesList("Andrew");
        testFList.addFinances(new Asset("cash", 25.27));
        testFList.addFinances(new Liability("student loans", -120.0));
    }

    @Test
    void testWriterInvalidFile() {
        try {
            testWriter = new JsonWriter("./data/notValid.json");
            testWriter.createWriter();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void WritenoUserData(){
        try {
            testWriter = new JsonWriter("./data/testWriterNoUser.json");
            testReader = new JsonReader("./data/testWriterNoUser.json");
            testWriter.createWriter();
            testWriter.write(testFList);
            testWriter.closeWriter();

            UserFinancesList fList = testReader.read();
            assertEquals(testFList, fList);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }    
    }

    @Test
    public void WriteonTopofUserData(){
        try {
            testWriter = new JsonWriter("./data/testWriterAndrew.json");
            testReader = new JsonReader("./data/testWriterAndrew.json");
            testFList.addFinances(new Asset("lottery", 1000.13));
            testFList.addFinances(new Liability("medical bills", -10330.21));
            testWriter.createWriter();
            testWriter.write(testFList);
            testWriter.closeWriter();

            UserFinancesList fList = testReader.read();
            assertEquals(testFList, fList);
        } catch (IOException e) {
            fail("exception should not have been thrown");
        }        
    }
}
