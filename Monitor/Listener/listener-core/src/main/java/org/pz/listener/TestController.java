package org.pz.listener;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Controller
public class TestController {

    @Autowired
    private TestRepository testRepo;
    //TODO GeneratedValue
    static int i = 1; 
    
    public void saveNewTest(String name){
        Test t = new Test();
        t.setName(name);
        t.setId(i++);
        testRepo.save(t);
    }
    
    public List<Test> getAll(){
        List<Test> l = new ArrayList<>();
        for(Test i : testRepo.findAll()){
            l.add(i);
        }
        return l;
    }
}
