import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController{
//    @GetMapping
//    public List<Person> getAllPeople() {
//        return personRepository.findAll();
//    }
    @GetMapping
    public String testeEndPoint() {
        return " estou funcionando ";
    }
}