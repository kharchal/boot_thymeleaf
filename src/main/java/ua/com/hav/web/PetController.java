package ua.com.hav.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.hav.model.Pet;
import ua.com.hav.repo.PetRepo;

import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepo petRepo;


    @RequestMapping("/")
    public String list(Model model) {
        List<Pet> all = petRepo.findAll();
        all.stream().forEach(System.out::println);
        model.addAttribute("list", all);
        return "petlist";
    }
}
