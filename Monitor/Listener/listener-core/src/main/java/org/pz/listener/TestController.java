package org.pz.listener;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
    
    public void saveNewTest(String name){
        Test t = new Test();
        t.setName(name);
        testRepo.save(t);
    }
    
    public List<Test> getAll(){
        return StreamSupport.stream(
                testRepo
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }
}
