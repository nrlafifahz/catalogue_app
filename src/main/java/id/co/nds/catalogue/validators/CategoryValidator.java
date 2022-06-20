package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstants;

public class CategoryValidator {
    public void nullChekcCategoryId(String id) throws ClientException{
        if(id==null){
            throw new ClientException( "Category id is required");

        }
    }
   
    public void notnullChekcCategoryId(String id ) throws ClientException {
        if(id!=null){
            throw new ClientException( "Category id is auto generated, do not input id");

        }
    }
    
    public void nullChekcName(String name ) throws ClientException {
        if(name==null){
            throw new ClientException( "Category name is required");

        }
    }

    public void nullChekcObject( Object o) throws NotFoundException{
        if(o ==null){
            throw new NotFoundException( "Category id is not found");

        }
    }
    
    public void validateCategoryId (String id) throws ClientException{
        if (id.length()!=6 || !id.startsWith("PC")){
            throw new ClientException("Product category id with id constain 6 digits and start with 'PC'");
        }
    }
    
    public void validateName (String name) throws ClientException{
        if (name.trim().equalsIgnoreCase(" ")){
            throw new ClientException("Category name is required");
        }
    }
    
    
    public void validateRecStatus (String id, String recStatus) throws ClientException{
        if (recStatus.equalsIgnoreCase(GlobalConstants.REC_STATUS_NONACTIVE)){
            throw new ClientException("Category with id = " + id+"is already been deleted");
        }
    }
    
}
