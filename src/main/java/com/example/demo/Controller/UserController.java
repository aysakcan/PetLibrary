package com.example.demo.Controller;


import com.example.demo.model.Pets;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repo.PetRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PetRepo petRepo;



    @GetMapping("main")
    public String main(@RequestParam(required = false,defaultValue = "") String name,String type, Model model) {
        Iterable<Users> users;

        if(type != null && type.equals("first") && name != null && !name.isEmpty()){
            users = userRepo.findByUserFirstName(name);
        }
        else if (type != null && type.equals("last") && name != null && !name.isEmpty()) {
            users = userRepo.findByUserLastName(name);
        }else{
            users = userRepo.findAll();
        }

        Iterable<Pets> pets = null;
        pets = petRepo.findAll();
        model.addAttribute("pets", pets);

        model.addAttribute("users", users);
        return "main";
    }


    @PostMapping("/addUser")
    public String addUser(@RequestParam String userAddress,
                      @RequestParam String userEmail,
                      @RequestParam String userPhone,
                      @RequestParam String userFname,
                      @RequestParam String userLname,
                      @RequestParam String userRole,
                      Model model){

        Users userrr = new Users();
        if (!userFname.isEmpty() && !userLname.isEmpty() && !userEmail.isEmpty() && !userAddress.isEmpty()) {
            userrr.setUserEmail(userEmail);
            userrr.setUserPhone(userPhone);
            userrr.setUserFirstName(userFname);
            userrr.setUserLastName(userLname);
            userrr.setUserAddress1(userAddress);
            userrr.setActive(false);
            if (userRole.equals("Admin")){userrr.setRoles(Collections.singleton(Role.ADMIN));}else {userrr.setRoles(Collections.singleton(Role.USER));}

            userRepo.save(userrr);
        }
        userFname = null;
        userLname = null;
        userEmail = null;
        userAddress = null;

        return "redirect:/main";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam String userId,Map<String,Object> model){
        if (!userId.isEmpty()) {
            Users user = userRepo.findById(Integer.parseInt(userId));

            userRepo.delete(user);
            model.put("message","User Deleted!");

        }else {
            model.put("message", "User isn't Deleted!");
        }
        return "main";
    }

    @PostMapping("/updateUser")
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
        return "redirect:/main";
    }

    @PostMapping("/addPet")
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

        return "redirect:/main";
    }

    @PostMapping("/deletePetfromAdmin")
    public String deletePet(@RequestParam(required = false, defaultValue = "") String petId,Map<String,Object> model){
        if (!petId.isEmpty()) {
            Pets pet = petRepo.findById(Integer.parseInt(petId));

            petRepo.delete(pet);
            model.put("message","Pet Deleted!");

        }else {
            model.put("message", "Pet isn't Deleted!");
        }
        return "main";
    }

    @PostMapping("/updatePetfromAdmin")
    public String updatePet(@RequestParam String petIsim,
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
        return "redirect:/main";
    }


}
