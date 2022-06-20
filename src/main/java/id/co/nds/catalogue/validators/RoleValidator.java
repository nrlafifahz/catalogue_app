package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstants;

public class RoleValidator {
    public void nullChekcRoleId(String id) throws ClientException{
        if(id==null){
            throw new ClientException( "Role id is required");

        }
    }
   
    public void notnullChekcRoleId(String id ) throws ClientException {
        if(id!=null){
            throw new ClientException( "Role id is auto generated, do not input id");

        }
    }
    
    public void nullChekcName(String name ) throws ClientException {
        if(name==null){
            throw new ClientException( "Role name is required");

        }
    }

    public void nullChekcObject( Object o) throws NotFoundException{
        if(o ==null){
            throw new NotFoundException( "Role id is not found");

        }
    }
    
    public void validateRoleId (String id) throws ClientException{
        if (id.length()!=5 || !id.startsWith("R")){
            throw new ClientException("User role id with id constain 6 digits and start with 'R'");
        }
    }
    
    public void validateName (String name) throws ClientException{
        if (name.trim().equalsIgnoreCase(" ")){
            throw new ClientException("Role name is required");
        }
    }
    
    
    public void validateRecStatus (String id, String recStatus) throws ClientException{
        if (recStatus.equalsIgnoreCase(GlobalConstants.REC_STATUS_NONACTIVE)){
            throw new ClientException("Role with id = " + id+"is already been deleted");
        }
    }
    
}
