package id.co.nds.catalogue.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.globals.GlobalConstants;

@Repository
@Transactional

public interface ProductRepo extends JpaRepository<ProductEntity,Integer>, JpaSpecificationExecutor<ProductEntity>{
    @Query(value = "SELECT COUNT (*) FROM ms_product WHERE rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + " 'AND LOWER(name) = LOWER (:name)",nativeQuery = true)
    long countByName(@Param("name")String name);

    @Query(value = "SELECT p.*, c.name AS category_name FROM ms_product AS p "+
    " JOIN ms_category AS c ON p.category_id=c.id "+
    " WHERE p.category_id=?1",nativeQuery = true)
    List<ProductEntity>findProductsByCategoryId(String categoryId);

    @Query(value = "SELECT * FROM ms_product WHERE rec_status = '" + GlobalConstants.REC_STATUS_ACTIVE
    + "' AND quantity < 5",nativeQuery = true)
    List<ProductEntity>CheckQuantityScheduler();


    // @Modifying
    // @Query(value = "UPDATE ms_product SET rec_status = '" +" ' , deleter_id = ?2, deleted_date = NOW("
    // +"WHERE id = ?1", nativeQuery = true)
    // Integer doDelete(Integer id, Integer daleterId);

   
}

