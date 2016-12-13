package org.tewelle.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.tewelle.persistent.entity.Radiograph;
import org.tewelle.persistent.entity.Doctor;

import java.util.List;

/**
 * A DAO for the entity Radiograph is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 *
 * @author Tewelle
 */
@Transactional
public interface RadiographDao extends CrudRepository<Radiograph, Long> {


    public Radiograph findByPatient_Id(Long patientId);
    public List<Doctor> findByDoctor_Id(Long doctorId);
    public List<Doctor> findByDoctor_IdAndPatient_Id(Long doctorId, Long patientId);

}
