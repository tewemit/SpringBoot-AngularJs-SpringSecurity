package org.tewelle.controllers;

import com.google.gson.Gson;
import org.tewelle.persistent.entity.Doctor;
import org.tewelle.repository.DoctorDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * A controller class to do interactions with a database using the DoctorDao class.
 *
 * @author Tewelle
 */
@RestController
public class UserController {

  //declare gson for converting objects to/from Json
  private static Gson gson = new Gson();

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param name User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping("/createUser")
  @ResponseBody
  public String create(String email, String name,String password) {
    Doctor doctor = null;
    try {
      doctor = new Doctor(email, name,password);
      DoctorDao.save(doctor);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created! (id = " + doctor.getId() + ")";
  }
  
  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/deleteuser")
  @ResponseBody
  public String delete(long id) {
    try {
      Doctor doctor = new Doctor(id);
      DoctorDao.delete(doctor);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  


  /**
   * @Description The Currently Authenticated User
   * @param user
   * @return
   */
  @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

  /**
   *
   * @param id
   * @return
   */
  @RequestMapping("/userById")
  @ResponseBody
  public String getById(@RequestParam("id") Long id) {
    Doctor doctor;
    try {
      doctor = DoctorDao.findOne(id);
    }
    catch (Exception ex) {
      Map<String,String> error =new HashMap<>();
      error.put("error","User not found with Id:" +id);
      return gson.toJson(error);
    }
    return gson.toJson(doctor);
  }


  /**
   *
   *
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is successfully updated or not.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      Doctor doctor = DoctorDao.findOne(id);
      doctor.setEmail(email);
      doctor.setUsername(name);
      DoctorDao.save(doctor);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User successfully updated!";
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private DoctorDao DoctorDao;
  
} // class UserController
