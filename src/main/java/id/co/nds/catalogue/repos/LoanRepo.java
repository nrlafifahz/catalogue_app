
package id.co.nds.catalogue.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.LoanEntity;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
@Transactional
public interface LoanRepo  extends JpaRepository<LoanEntity, String>{
    
}

