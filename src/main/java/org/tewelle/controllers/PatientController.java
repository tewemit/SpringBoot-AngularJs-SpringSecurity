package org.tewelle.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tewelle.persistent.entity.Doctor;
import org.tewelle.persistent.entity.Patient;
import org.tewelle.persistent.entity.Radiograph;
import org.tewelle.repository.PatientDao;
import org.tewelle.repository.RadiographDao;
import org.tewelle.services.UserDetailsService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tewe on 12/12/2016.
 */
public class PatientController {


    // ------------------------
    // PRIVATE FIELDS
    // ------------------------
    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);
    @Autowired

    private PatientDao patientDao;

    private static Gson gson = new Gson();


    /**
     *
     * @param
     * @return
     */
    @RequestMapping("/addpatient")
    @ResponseBody
    public String addPatient(@RequestParam("pin") String PIN, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName,
                             @RequestParam("gender") String gender) {
        Doctor loggedInUser = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long doctorId = loggedInUser.getId(); //get logged in userId
        Patient pnt = new Patient(PIN,firstName,lastName,gender);

        try {
            pnt = patientDao.save(pnt);
        }
        catch (Exception ex) {
            Map<String,String> message =new HashMap<>();
            message.put("error","Patient not added ");
            return gson.toJson(message);
        }
        log.info("Patient saved successfully: " + pnt.toString());
        return gson.toJson(pnt);
    }
    /**
     * List radiographs added by the logged in doctor for a specific patient
     * @param
     * @return
     */
    @RequestMapping("/patients")
    @ResponseBody
    public String getPatients() {
        Map<String,Object> patients = new HashMap<>();
        try {
            patients = (Map<String, Object>) patientDao.findAll();
            log.info("retrived patients: " + gson.toJson(patients));
        }
        catch (Exception ex) {
            Map<String,String> message =new HashMap<>();
            message.put("error","no patients found ");
            return gson.toJson(message);
        }
        log.info("Fetched patients successfully: " + patients.toString());
        return gson.toJson(patients);
    }
}
