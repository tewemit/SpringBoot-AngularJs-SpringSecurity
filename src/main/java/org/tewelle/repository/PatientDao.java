package org.tewelle.repository;

import org.springframework.data.repository.CrudRepository;
import org.tewelle.persistent.entity.Patient;
import org.tewelle.persistent.entity.Patient;

import javax.transaction.Transactional;

/**
 * A DAO for the entity Patient is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author netgloo
 */
@Transactional
public interface PatientDao extends CrudRepository<Patient, Long> {

  public Patient findByPin(String PIN);

} 
