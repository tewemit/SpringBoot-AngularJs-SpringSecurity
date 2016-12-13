package org.tewelle.repository;

import org.springframework.data.repository.CrudRepository;
import org.tewelle.models.Token;


/**
 * Created by tewe on 12/5/2016.
 */
public interface TokenDao extends CrudRepository<Token, String> {
}
