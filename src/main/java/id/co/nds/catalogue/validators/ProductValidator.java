package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstants;

public class ProductValidator {
    public void nullChekcProductId(Integer id) throws ClientException{
        if(id==null){
            throw new ClientException( "Product id is required");

        }
    }
   
    public void notnullChekcProductId(Integer id ) throws ClientException {
        if(id!=null){
            throw new ClientException( "Product id is auto generated, do not input id");

        }
    }
    
    public void nullChekcName(String name ) throws ClientException {
        if(name==null){
            throw new ClientException( "Product name is required");

        }
    }
    
    public void nullChekcQuantity(Integer quantity) throws ClientException{
        if(quantity==null){
            throw new ClientException( "Product quantity is required");

        }
    }

    public void nullChekcCategoryId(String categoryId ) throws ClientException {
        if(categoryId==null){
            throw new ClientException( "Product category id is required");

        }
    }

    public void nullChekcObject( Object o) throws NotFoundException{
        if(o ==null){
            throw new NotFoundException( "Product id is not found");

        }
    }
    
    public void validateProductId (Integer id) throws ClientException{
        if (id <= 0){
            throw new ClientException("Product id input is invalid");
        }
    }
    
    public void validateName (String name) throws ClientException{
        if (name.trim().equalsIgnoreCase(" ")){
            throw new ClientException("Product name is required");
        }
    }
    
    public void validateQuantity (Integer quantity) throws ClientException{
        if (quantity<0){
            throw new ClientException("Product quantity must be positive integer number");
        }
    }
    
    public void validateCategoryId (String categoryId) throws ClientException{
        if (categoryId.length()!=6 || !categoryId.startsWith("PC")){
            throw new ClientException("Product category id with id constain six digits and start with 'PC'");
        }
    }
    
    public void validateRecStatus (String id, String recStatus) throws ClientException{
        if (recStatus.equalsIgnoreCase(GlobalConstants.REC_STATUS_NONACTIVE)){
            throw new ClientException("Product with id = " + id+"is already been deleted");
        }
    }
    
}
