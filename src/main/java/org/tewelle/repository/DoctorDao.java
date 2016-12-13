package org.tewelle.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.tewelle.persistent.entity.Doctor;

/**
 * A DAO for the entity Doctor is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author netgloo
 */
@Transactional
public interface DoctorDao extends CrudRepository<Doctor, Long> {


  public Doctor findByEmail(String email);
  public Doctor findByUsername(String username);
  public Doctor findByUsernameAndPassword(String username, String password);

}
