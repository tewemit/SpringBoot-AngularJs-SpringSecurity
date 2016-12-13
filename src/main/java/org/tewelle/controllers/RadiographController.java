package org.tewelle.controllers;

import com.google.gson.Gson;
import org.springframework.security.core.context.SecurityContextHolder;
import org.tewelle.persistent.entity.Doctor;
import org.tewelle.persistent.entity.Patient;
import org.tewelle.persistent.entity.Radiograph;
import org.tewelle.repository.PatientDao;
import org.tewelle.repository.RadiographDao;
import org.tewelle.services.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * A controller class to do interactions with a database using the RadiographDao class.
 *
 * @author netgloo
 */
@RestController
public class RadiographController {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);
  @Autowired
  private RadiographDao radiographDao;
  private PatientDao patientDao;

  private static Gson gson = new Gson();

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  

  /**
   * /delete  --> Delete the radiograph having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/deleteradiograph")
  @ResponseBody
  public String delete(long id) {
    Map<String,String> message =new HashMap<>();
    try {
      Radiograph radiograph = new Radiograph(id);
      radiographDao.delete(radiograph);
    }
    catch (Exception ex) {
      message.put("message","Faqs not found ");
      return gson.toJson(message);
    }
    message.put("message","user successfully deleted");
    return gson.toJson(message);
  }


  /**
   * List radiographs added by the logged in doctor for a specific patient
   * @param
   * @return
   */
  @RequestMapping("/radiographs")
  @ResponseBody
  public String getRadiographs(@RequestParam("patient_Id") Long patient_Id) {
    Iterable radiographs;
    Map<String, Object> radiograph = new HashMap<>();
    Patient patientInfo;

    Doctor loggedInUser = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = loggedInUser.getId(); //get logged in userId
    if(patient_Id!=null)
    {
      patientInfo = patientDao.findOne(patient_Id);
      radiograph.put("patient",patientInfo);
    }
    else radiograph.put("patient",null);

    radiograph.put("radiographs",radiograph);
    try {
      radiographs = radiographDao.findByDoctor_IdAndPatient_Id(userId,patient_Id);
    }
    catch (Exception ex) {
      Map<String,String> message =new HashMap<>();
      message.put("error","radiographs not found ");
      return gson.toJson(message);
    }
    log.info("Fetched radiographs successfully: " + radiographs.toString());
    return gson.toJson(radiographs);
  }



  /**
   *
   * @param
   * @return
   */
  @RequestMapping("/addradiograph")
  @ResponseBody
  public String addRadiograph(@RequestParam("pin") String PIN, @RequestParam("title") String Title, @RequestParam("description") String Description)
  {
    Radiograph radiograph = new Radiograph();
    Doctor loggedInUser = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = loggedInUser.getId(); //get logged in userId
    Patient pntinfo;
    pntinfo = patientDao.findByPin(PIN);
    radiograph.setPatient_Id(pntinfo.getId());
    radiograph.setDoctor_Id(userId);
    radiograph.setDescription(Description);
    radiograph.setTitle(Title);


    try {
    radiographDao.save(radiograph);
    }
    catch (Exception ex) {
      Map<String,String> message =new HashMap<>();
      message.put("error","Radiograph not added ");
      return gson.toJson(message);
    }
    log.info("Radiograph saved successfully: " + radiograph.toString());
    return gson.toJson(radiograph);
  }

}
