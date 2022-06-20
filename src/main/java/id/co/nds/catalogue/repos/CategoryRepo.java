package id.co.nds.catalogue.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.CategoryEntity;
import id.co.nds.catalogue.globals.GlobalConstants;


@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<CategoryEntity,String> {
    @Query(value = "SELECT COUNT (*) FROM ms_category WHERE rec_status = '"  + GlobalConstants.REC_STATUS_ACTIVE
    + "' AND LOWER(name) = LOWER (:name)",nativeQuery = true)
    long countByName(@Param("name")String name);

    
    
}
