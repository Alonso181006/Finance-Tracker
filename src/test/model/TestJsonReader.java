package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import persistence.JsonReader;

public class TestJsonReader extends TestJson{
    JsonReader testReader1;
    JsonReader testReader2;
    UserFinancesList testFList;


    @BeforeEach
    void setup(){
        testReader1 = new JsonReader("./data/testReaderNoUser.json");
        testReader2 = new JsonReader("./data/testReaderAndrew.json");
        testFList = new UserFinancesList("Andrew");
        testFList.addFinances(new Asset("cash", 25.27));
        testFList.addFinances(new Liability("student loans", -120.0));
    }

    @Test
    public void testNoUserDataReader(){
        try {
            UserFinancesList fList = testReader1.read();
            assertEquals("User 1", fList.getUser());
            assertEquals(0, fList.getFinances().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void onTopofUserData(){
        try {
            UserFinancesList fList = testReader2.read();
            assertEquals("Andrew", fList.getUser());
            assertEquals(2, fList.getFinances().size());
            checkFinance("cash", 25.27, fList.getFinances().get(0));
            checkFinance("student loans", -120.0, fList.getFinances().get(1));
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
