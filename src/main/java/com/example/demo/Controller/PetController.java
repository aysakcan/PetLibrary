package com.example.demo.Controller;


import com.example.demo.model.Pets;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repo.PetRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@Controller
public class PetController {

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("second")
    public String second(@RequestParam(required = false, defaultValue = "") String name, String type, String email, Model model) {
        Iterable<Pets> pets = null;
        pets = petRepo.findAll();
        model.addAttribute("pets", pets);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Users user = userRepo.findByUserEmailIs(currentPrincipalName);
        model.addAttribute("user", user);

        return "second";
    }

    @PostMapping("/second")
    public String addPet(@RequestParam String petIsim,
                         @RequestParam String petTur,
                         @RequestParam String petCins,
                         @RequestParam String petAciklama,
                         @RequestParam String petYas,
                         @RequestParam String petUser,
                         Map<String, Object> model) {

        Pets temp = new Pets();
        temp.setIsim(petIsim);
        temp.setTur(petTur);
        temp.setCins(petCins);
        temp.setAciklama(petAciklama);
        temp.setYas(Integer.parseInt(petYas));
        temp.setUser(petUser);
        petRepo.save(temp);
        petIsim = null;
        petTur = null;
        petCins = null;
        petAciklama = null;
        petYas = null;
        petUser = null;

        return "redirect:/second";
    }

    @PostMapping("/deletePet")
    public String delete(@RequestParam(required = false, defaultValue = "") String petId,Map<String,Object> model){
        if (!petId.isEmpty()) {
            Pets pet = petRepo.findById(Integer.parseInt(petId));

            petRepo.delete(pet);
            model.put("message","Pet Deleted!");

        }else {
            model.put("message", "Pet isn't Deleted!");
        }
        return "second";
    }

    @PostMapping("/updatePet")
    public String updateName(@RequestParam String petIsim,
                             @RequestParam String petTur,
                             @RequestParam String petCins,
                             @RequestParam String petAciklama,
                             @RequestParam String petYas,
                             @RequestParam String petId,
                             Model model){
        Pets temp = null;
        if (!petId.isEmpty() ) {
            temp = petRepo.findById(Integer.parseInt(petId));
            temp.setIsim(petIsim);
            temp.setTur(petTur);
            temp.setCins(petCins);
            temp.setAciklama(petAciklama);
            temp.setYas(Integer.parseInt(petYas));

            petRepo.save(temp);
            model.addAttribute("message","Pet updated!");

        }else {
            model.addAttribute("message", "Pet isn't updated!");
        }
        return "redirect:/second";
    }

    @PostMapping("/updatePerson")
    public String updateUser(@RequestParam String userAddress,
                             @RequestParam String userPhone,
                             @RequestParam String userFname,
                             @RequestParam String userLname,
                             @RequestParam String userId,
                             Model model){
        Users temp = null;
        if (!userId.isEmpty() ) {
            temp = userRepo.findById(Integer.parseInt(userId));
            temp.setUserFirstName(userFname);
            temp.setUserLastName(userLname);
            temp.setUserPhone(userPhone);
            temp.setUserAddress1(userAddress);

            userRepo.save(temp);
            model.addAttribute("message","User updated!");

        }else {
            model.addAttribute("message", "User isn't updated!");
        }
        return "redirect:/second";
    }



}
