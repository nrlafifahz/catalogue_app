package id.co.nds.catalogue.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.nds.catalogue.entities.SalesEntity;



@Repository
@Transactional
public interface SalesRepo  extends JpaRepository<SalesEntity, String>{
    
}
