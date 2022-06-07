/**
 * 
 */
package com.arjun.codeseven.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arjun.codeseven.model.BillingDetails;

/**
 * @author arjun
 *
 */
public interface BillingDetailsRepository<T extends BillingDetails, ID> extends JpaRepository<T, ID> {
	
	List<T> findByOwner(String owner);

}
