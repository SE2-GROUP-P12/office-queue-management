package it.polito.ezqueue.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polito.ezqueue.service.QueueManagerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest
class QueueManagerControllerTest {
    @Autowired
    private QueueManagerController queueManagerController;

    @BeforeEach
    void setup(){
        queueManagerController.getQmService().resetConfig();
        queueManagerController.getQmService().initConfig("src/test/java/it/polito/ezqueue/resources/config_test.yml");

    }

    @Test
    void ticketRequestTest(){

        ResponseEntity employeeRes = queueManagerController.queueUpdate("{\"deskId\": \"1\"}");
        Assertions.assertNotNull(employeeRes.getBody());
        Assertions.assertTrue(employeeRes.getBody().toString().contains("no more ticket to serve"), "message of no more ticket to serv is wrong");
        ResponseEntity res2 = queueManagerController.ticketRequest("{\"serviceRequested\": \"SERVICE1\"}");
        Assertions.assertNotNull(res2.getBody());
        Assertions.assertTrue(res2.toString().contains("1"),"next number is not 1");

        employeeRes = queueManagerController.queueUpdate("{\"deskId\": \"1\"}");
        System.out.println(employeeRes.getBody());
        Assertions.assertNotNull(employeeRes.getBody());
        Assertions.assertTrue(employeeRes.getBody().toString().contains("serve next ticket"), "message type must be serve next ticket");

        employeeRes = queueManagerController.queueUpdate("{\"deskId\": \"1\"}");
        System.out.println(employeeRes.getBody());
        Assertions.assertNotNull(employeeRes.getBody());
        Assertions.assertTrue(employeeRes.getBody().toString().contains("no more ticket to serve"), "message of no more ticket to serv is wrong");

    }




}
