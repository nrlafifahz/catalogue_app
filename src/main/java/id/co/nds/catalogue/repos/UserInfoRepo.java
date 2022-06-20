
package id.co.nds.catalogue.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.globals.GlobalConstants;


@Repository
@Transactional
public interface UserInfoRepo extends JpaRepository<UserEntity,String>, JpaSpecificationExecutor<UserEntity>{
    // @Query(value = "SELECT u.*, r.name AS role_name FROM ms_user AS u "+
    // " JOIN ms_role AS r ON u.role_id=r.id "+
    // " WHERE u.role_name=?1",nativeQuery = true)
    // List<UserEntity> findAllByRole(String name);

    @Query(value = "SELECT u.*FROM ms_user u JOIN ms_role r ON r.id=u.role_id "+
    " WHERE u.rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + "' AND LOWER(r.name) = LOWER (:name)",nativeQuery = true)
    List<UserEntity>findAllByRole(@Param("name")String name);


}

