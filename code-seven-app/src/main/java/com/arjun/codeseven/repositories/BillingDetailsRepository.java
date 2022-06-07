/**
 * 
 */
package com.arjun.codeseven.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.arjun.codeseven.model.BillingDetails;

/**
 * @author arjun
 *
 */
@NoRepositoryBean
public interface BillingDetailsRepository<T extends BillingDetails, ID> extends JpaRepository<T, ID> {
	
	List<T> findByOwner(String owner);

}
