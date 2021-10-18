package it.polito.ezqueue.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polito.ezqueue.entity.Office;
import org.junit.jupiter.api.Assertions;
import it.polito.ezqueue.entity.Desk;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class QueueManagerServiceTest {

    @Autowired
    private QueueManagerService queueManagerService;




    void serviceTest(){
        initializationTest();
        getEstimatedTimeAndTicketNumberTest();
    }

    /**
     * Testing the initialization of the service
     * the initialization must be performed in the queueManagerService
     * constructor, otherwise the test will fail
     */
    @BeforeEach
    void initializationTest(){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Assertions.assertFalse(queueManagerService.getDesks().isEmpty(),"Desks are empty");
        Assertions.assertFalse(queueManagerService.getServs().isEmpty(),"Servs are empty");
        Assertions.assertEquals(1,queueManagerService.getNextNumber(),"Next number is wrong after the initialization");


    }

    @Test
    void getEstimatedTimeAndTicketNumberTest(){
        Map<String, String> res;
        System.out.println("start testing");
        try {
            res = queueManagerService.getEstimatedTimeAndTicketNumber("{\"serviceRequested\": \"SERVICE1\"}");
            Assertions.assertEquals(1,Integer.parseInt(res.get("clientNumber")),"expected client number is wrong");
            Assertions.assertEquals(2,queueManagerService.getNextNumber(), "next number is wrong");
        } catch (JsonProcessingException e) {
            Assertions.fail("unexpected exception found" + e);
            e.printStackTrace();
        }

        try {
            queueManagerService.getNextNumberAndUpdateQueue("{\"deskId\": 1}");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
