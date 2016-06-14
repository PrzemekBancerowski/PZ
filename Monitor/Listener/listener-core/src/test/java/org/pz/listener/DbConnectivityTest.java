package org.pz.listener;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pz.listener.core.Application;
import org.pz.listener.core.DatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class DbConnectivityTest {
    
    @Autowired
    DatabaseController controller; 
    
    @Test
    @Ignore
    public void shouldAddRowToTestEntity() {
        
//        //given 
//        int initialSize = controller.getAll().size();
//        
//        //when
//        controller.saveNewTest("test");
//        
//        //then
//        Assert.assertEquals(initialSize+1,controller.getAll().size()); 
    }
}
