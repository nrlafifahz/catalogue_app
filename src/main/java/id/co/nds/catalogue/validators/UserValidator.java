package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstants;

public class UserValidator {
    public void nullChekcUserId(Integer id) throws ClientException{
        if(id==null){
            throw new ClientException( "User id is required");

        }
    }
   
    public void notnullChekcUserId(Integer id ) throws ClientException {
        if(id!=null){
            throw new ClientException( "User id is auto generated, do not input id");

        }
    }
    
    public void nullChekcFullname(String fullName ) throws ClientException {
        if(fullName==null){
            throw new ClientException( "User name is required");

        }
    }
    
    public void nullChekcCallNumber(String callNumber) throws ClientException{
        if(callNumber==null){
            throw new ClientException( "User call number is required");

        }
    }

    public void nullChekcRoleId(String roleId ) throws ClientException {
        if(roleId==null){
            throw new ClientException( "User role id is required");

        }
    }

    public void nullChekcObject( Object o) throws NotFoundException{
        if(o ==null){
            throw new NotFoundException( "User id is not found");

        }
    }
    
    public void validateUserId (Integer id) throws ClientException{
        if (id <= 0){
            throw new ClientException("User id input is invalid");
        }
    }
    
    public void validateFullname (String fullName) throws ClientException{
        if (fullName.trim().equalsIgnoreCase(" ")){
            throw new ClientException("User name is required");
        }
    }
    
    public void validateCallNumber (String callNumber) throws ClientException{
        if ((!callNumber.startsWith("0")|| !callNumber.startsWith("+62")) && (callNumber.length() >12 && callNumber.length() <9)){
            throw new ClientException("User call number with number constain 9-10 digits and start with 0 or +62");
        }
    }
    
    public void validateRoleId (String roleId) throws ClientException{
        if (roleId.length()!=5 || !roleId.startsWith("R")){
            throw new ClientException("User role id with id constain 6 digits and start with 'R'");
        }
    }
    
    public void validateRecStatus (String id, String recStatus) throws ClientException{
        if (recStatus.equalsIgnoreCase(GlobalConstants.REC_STATUS_NONACTIVE)){
            throw new ClientException("User with id = " + id+"is already been deleted");
        }
    }
    
}
