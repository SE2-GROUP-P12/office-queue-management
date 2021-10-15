package it.polito.ezqueue.service;

import org.junit.jupiter.api.Assertions;
import it.polito.ezqueue.entity.Desk;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class QueueManagerServiceTest {

    @Autowired
    private QueueManagerService queueManagerService;


    @Test
    void getterTest(){
        HashMap<Integer, Desk> tmp = queueManagerService.getDesks();
        Assertions.assertTrue(tmp.isEmpty(), "");
    }

}
