package it.polito.ezqueue.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polito.ezqueue.entity.Office;
import it.polito.ezqueue.entity.Serv;
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
        queueManagerService.resetConfig();
        queueManagerService.initConfig("src/test/java/it/polito/ezqueue/resources/config_test.yml");
        Assertions.assertFalse(queueManagerService.getDesks().isEmpty(),"Desks are empty");
        Assertions.assertFalse(queueManagerService.getServs().isEmpty(),"Servs are empty");
        Assertions.assertEquals(1,queueManagerService.getNextNumber(),"Next number is wrong after the initialization");


    }

    @Test
    void activeServeTest(){
        List<Serv> res;
        System.out.println("start testing");
        res = queueManagerService.activeServs();
        Assertions.assertNotNull(res, "null pointer returned");
        Assertions.assertFalse(res.isEmpty(), "empty service list returned");
    }

    @Test
    void toggleOpenDeskTest() {
        System.out.println("start testing");
        Desk myDesk2 = queueManagerService.getDesks().get(2);
        Assertions.assertTrue(myDesk2.getDeskOpen());
        try {
            queueManagerService.toggleOpenDesk("{\"deskId\" : \"2\"}");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(myDesk2.getDeskOpen());

        try {
           queueManagerService.toggleOpenDesk("{\"deskId\" : \"2\"}");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(myDesk2.getDeskOpen());
    }

    @Test
    void getNextNumberAndUpdateQueueTest (){
        System.out.println("start testing");
        try {
            queueManagerService.getEstimatedTimeAndTicketNumber("{\"serviceRequested\": \"SERVICE1\"}");
            queueManagerService.getEstimatedTimeAndTicketNumber("{\"serviceRequested\": \"SERVICE1\"}");
            queueManagerService.getEstimatedTimeAndTicketNumber("{\"serviceRequested\": \"SERVICE3\"}");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(2, queueManagerService.getServs().get("SERVICE1").getQueueSize());
        try {
            queueManagerService.getNextNumberAndUpdateQueue("{\"deskId\" : \"2\"}");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(1, queueManagerService.getDesks().get(2).getCurrentTicketServed());
        try {
            queueManagerService.getNextNumberAndUpdateQueue("{\"deskId\" : \"2\"}");
            queueManagerService.getNextNumberAndUpdateQueue("{\"deskId\" : \"2\"}");
            Assertions.assertThrows(NullPointerException.class, () -> {queueManagerService.getNextNumberAndUpdateQueue("{\"deskId\" : \"2\"}"); });

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(3, queueManagerService.getDesks().get(2).getCurrentTicketServed());

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
