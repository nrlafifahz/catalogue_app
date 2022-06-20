package id.co.nds.catalogue.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstants;
import id.co.nds.catalogue.models.ProductModel;
import id.co.nds.catalogue.repos.ProductInfoRepo;
import id.co.nds.catalogue.repos.ProductRepo;
import id.co.nds.catalogue.repos.specs.ProductSpec;
import id.co.nds.catalogue.validators.CategoryValidator;
import id.co.nds.catalogue.validators.ProductValidator;


@Service
public class ProductServices implements Serializable {
    @Autowired
    private ProductRepo productRepo;

    @ Autowired
    private ProductInfoRepo productInfoRepo; 

    ProductValidator productValidator = new ProductValidator();
    CategoryValidator categoryValidator = new CategoryValidator();

    public ProductEntity add(ProductModel productModel) throws ClientException{
        productValidator.notnullChekcProductId(productModel.getId());
        productValidator.nullChekcName(productModel.getName());
        productValidator.validateName(productModel.getName());
        productValidator.nullChekcQuantity(productModel.getQuantity());
        productValidator.validateQuantity(productModel.getQuantity());
        productValidator.nullChekcCategoryId(productModel.getCategoryId());
        productValidator.validateCategoryId(productModel.getCategoryId());
        
        Long count = productRepo.countByName((productModel.getName()));
        if(count>0){
            throw new ClientException("Product name is already existed");

           
        }
        ProductEntity product =new ProductEntity();
        product.setName(productModel.getName());
        product.setQuantity(productModel.getQuantity());
        product.setCategoryId(productModel.getCategoryId());
        product.setRecStatus(GlobalConstants.REC_STATUS_ACTIVE);
        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        product.setCreatorId(productModel.getActorId()==null ?0: productModel.getActorId());
        

        return productRepo.save(product);
    } 
    
    public List<ProductEntity> findAll(){
        List<ProductEntity> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);
        return products;
    }
    
    public List<ProductEntity> findAllByCriteria(ProductModel productModel){
        List<ProductEntity> products = new ArrayList<>();
        ProductSpec productSpec = new ProductSpec(productModel);
        productRepo.findAll(productSpec).forEach(products::add);
        return products;
    }
    
    public List<ProductEntity> findAllByCategory(String categoryId)
    throws ClientException, NotFoundException{
        //VALIDATION
        categoryValidator.nullChekcCategoryId(categoryId);
        categoryValidator.validateCategoryId(categoryId);

        // PROCESS
        List<ProductEntity> products = productInfoRepo.findAllByCategory(categoryId);
        productValidator.nullChekcObject(products);
        return products;
    }
   
    public List<ProductEntity> findProductsByCategoryId(String categoryId) throws ClientException, NotFoundException{
        //validation
        categoryValidator.nullChekcCategoryId(categoryId);
        categoryValidator.validateCategoryId(categoryId);

        //process
        List<ProductEntity> product = productRepo.findProductsByCategoryId(categoryId);
        productValidator.nullChekcObject(product);

        return product;
    }
   
    public ProductEntity findById(Integer id) throws ClientException, NotFoundException{
        productValidator.nullChekcProductId((id));
        productValidator.validateProductId(id);

        ProductEntity product = productRepo.findById(id).orElse(  null);
        productValidator.nullChekcObject(product);
        return product;
    }
    
    public ProductEntity edit (ProductModel productModel)
    throws ClientException,NotFoundException{
        //validation
        productValidator.nullChekcProductId(productModel.getId());
        productValidator.validateProductId(productModel.getId());

        if(!productRepo.existsById((productModel.getId()))){
            throw new NotFoundException( "Cannot find product with id: " +productModel.getId());
        }

        //proses
        ProductEntity product = new ProductEntity();
        product=findById(productModel.getId());

        if(productModel.getName() !=null){
            productValidator.validateName( (productModel.getName()));

            Long count = productRepo.countByName((productModel.getName()));
            if (count >0){
                throw new ClientException("Product name is already existed ");

            }
            product.setName((productModel.getName()));
        }
        if (productModel.getQuantity()!= null){
            productValidator.validateQuantity((productModel.getQuantity()));
            product.setQuantity((productModel.getQuantity()));
        }
        if (productModel.getCategoryId() !=null){
            productValidator.validateCategoryId(productModel.getCategoryId());
            product.setCategoryId(productModel.getCategoryId());
        }
        product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        product.setUpdaterId(productModel.getActorId() == null ? 0 :productModel.getActorId());

        return productRepo.save(product);
    }

    public ProductEntity delete (ProductModel productModel) throws ClientException, NotFoundException{
        //validation
        productValidator.nullChekcProductId(productModel.getId());
        productValidator.validateProductId(productModel.getId());

        if(!productRepo.existsById((productModel.getId()))){
            throw new NotFoundException( "Cannot find product with id: " +productModel.getId());
        }

        //proses
        ProductEntity product = new ProductEntity();
        product=findById(productModel.getId());

        if(product.getRecStatus().equalsIgnoreCase(GlobalConstants.REC_STATUS_NONACTIVE)){
            throw new ClientException("Product id (" + productModel.getId() +  ") is already been deleted ");
        }
        product.setRecStatus(GlobalConstants.REC_STATUS_NONACTIVE);
        product.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        product.setDeleterId(productModel.getActorId() == null ? 0 :productModel.getActorId());

        return productRepo.save(product);
    }

    
   

}
