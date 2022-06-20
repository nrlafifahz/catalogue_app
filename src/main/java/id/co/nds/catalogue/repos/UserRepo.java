package id.co.nds.catalogue.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.globals.GlobalConstants;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    @Query(value = "SELECT COUNT (*) FROM ms_product WHERE rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + " 'AND LOWER(name) = LOWER (:name)",nativeQuery = true)
    long countByFullname(@Param("name")String name);

    @Query (value="SELECT COUNT (*) FROM ms_user WHERE rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + "' AND call_number = :call_number" , nativeQuery =  true)
    long countByCallNumber(@Param("call_number") String callNumber);

    @Query(value = "SELECT u.*FROM ms_user u JOIN ms_role r ON r.id=u.role_id "+
    " WHERE u.rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + "' AND LOWER(r.name) = LOWER (:name)",nativeQuery = true)
    List<UserEntity>findUserByRoleName(@Param("name")String name);


    
}
