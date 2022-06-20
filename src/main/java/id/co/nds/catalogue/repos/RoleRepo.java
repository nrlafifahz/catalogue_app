package id.co.nds.catalogue.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import id.co.nds.catalogue.entities.RoleEntity;
import id.co.nds.catalogue.globals.GlobalConstants;

@Repository
@Transactional

public interface RoleRepo extends JpaRepository<RoleEntity,String>, JpaSpecificationExecutor<RoleEntity>{
    @Query(value = "SELECT COUNT (*) FROM ms_user WHERE rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + " 'AND LOWER(fullname) = LOWER (:name)",nativeQuery = true)
    long countByName(@Param("name")String name);

    @Query(value = "SELECT u.*FROM ms_user u JOIN ms_role r ON r.id=u.role_id "+
    " WHERE u.rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + "' AND LOWER(r.name) = LOWER (:name)",nativeQuery = true)
    List<RoleEntity>findUserByRoleName(@Param("name") String name);

    @Query(value = "SELECT u.*FROM ms_user u JOIN ms_role r ON r.id=u.role_id "+
    " WHERE u.rec_status = '" + GlobalConstants.REC_STATUS_NONACTIVE
    + "' AND LOWER(r.name) = LOWER (:name)",nativeQuery = true)
    List<RoleEntity>findUserByRoleNameWhereNoActive(@Param("name") String name);

}

