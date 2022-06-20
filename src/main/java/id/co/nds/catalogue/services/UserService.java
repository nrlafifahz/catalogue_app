package id.co.nds.catalogue.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstants;
import id.co.nds.catalogue.models.UserModel;
import id.co.nds.catalogue.repos.UserInfoRepo;
import id.co.nds.catalogue.repos.UserRepo;
import id.co.nds.catalogue.repos.specs.UserSpec;
import id.co.nds.catalogue.validators.RoleValidator;
import id.co.nds.catalogue.validators.UserValidator;


@Service
public class UserService implements Serializable{
    @Autowired
    private UserRepo userRepo;

    @ Autowired
    private UserInfoRepo userInfoRepo; 

    UserValidator userValidator = new UserValidator();
    RoleValidator roleValidator = new RoleValidator();

    public UserEntity add(UserModel userModel) throws ClientException{
        userValidator.notnullChekcUserId(userModel.getId());
        userValidator.nullChekcFullname(userModel.getFullname());
        userValidator.validateFullname(userModel.getFullname());
        userValidator.nullChekcCallNumber(userModel.getCallNumber());
        userValidator.validateCallNumber(userModel.getCallNumber());
        userValidator.nullChekcRoleId(userModel.getRoleId());
        userValidator.validateRoleId(userModel.getRoleId());
        
        Long count = userRepo.countByFullname((userModel.getFullname()));

        if(count>0){
            throw new ClientException("User name is already existed");
        }

        Long countCall = userRepo.countByCallNumber((userModel.getCallNumber()));
        if(countCall>0){
            throw new ClientException("call number is already existed");
        }
        UserEntity user =new UserEntity();
        user.setFullname(userModel.getFullname());
        user.setCallNumber(userModel.getCallNumber());
        user.setRoleId(userModel.getRoleId());
        user.setRecStatus(GlobalConstants.REC_STATUS_ACTIVE);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setCreatorId(userModel.getActorId()==null ?0: userModel.getActorId());
        

        return userRepo.save(user);
    } 
    
    public List<UserEntity> findAll(){
        List<UserEntity> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }
    
    public List<UserEntity> findAllByCriteria(UserModel userModel){
        List<UserEntity> users = new ArrayList<>();
        UserSpec userSpec = new UserSpec(userModel);
        userRepo.findAll(userSpec).forEach(users::add);
        return users;
    }
    
    public List<UserEntity> findAllByRole(String name)
    throws ClientException, NotFoundException{
        //VALIDATION
        userValidator.nullChekcFullname(name);
        userValidator.validateFullname(name);

        // PROCESS
        List<UserEntity> users = userInfoRepo.findAllByRole(name);
        userValidator.nullChekcObject(users);
        return users;
    }
      
    public List<UserEntity> findUserByRoleName(String name)
    throws ClientException, NotFoundException{
        //VALIDATION
        userValidator.nullChekcFullname(name);
        userValidator.validateFullname(name);

        // PROCESS
        List<UserEntity> users = userRepo.findUserByRoleName(name);
        userValidator.nullChekcObject(users);
        return users;
    }
   
    public UserEntity findById(Integer id) throws ClientException, NotFoundException{
        userValidator.nullChekcUserId((id));
        userValidator.validateUserId(id);

        UserEntity user = userRepo.findById(id).orElse(  null);
        userValidator.nullChekcObject(user);
        return user;
    }
    
    public UserEntity edit (UserModel userModel)
    throws ClientException,NotFoundException{
        //validation
        userValidator.nullChekcUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());

        if(!userRepo.existsById((userModel.getId()))){
            throw new NotFoundException( "Cannot find user with id: " +userModel.getId());
        }

        //proses
        UserEntity user = new UserEntity();
        user=findById(userModel.getId());

        if(userModel.getFullname() !=null){
            userValidator.validateFullname( (userModel.getFullname()));

            Long count = userRepo.countByFullname((userModel.getFullname()));
            if (count >0){
                throw new ClientException("User name is already existed ");

            }
            user.setFullname((userModel.getFullname()));
        }
        if (userModel.getCallNumber()!= null){
            userValidator.validateCallNumber((userModel.getCallNumber()));
            user.setCallNumber((userModel.getCallNumber()));
        }
        if (userModel.getRoleId() !=null){
            userValidator.validateRoleId(userModel.getRoleId());
            user.setRoleId(userModel.getRoleId());
        }
        user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        user.setUpdaterId(userModel.getActorId() == null ? 0 :userModel.getActorId());

        return userRepo.save(user);
    }

    public UserEntity delete (UserModel userModel) throws ClientException, NotFoundException{
        //validation
        userValidator.nullChekcUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());

        if(!userRepo.existsById((userModel.getId()))){
            throw new NotFoundException( "Cannot find user with id: " +userModel.getId());
        }

        //proses
        UserEntity user = new UserEntity();
        user=findById(userModel.getId());

        if(user.getRecStatus().equalsIgnoreCase(GlobalConstants.REC_STATUS_NONACTIVE)){
            throw new ClientException("User id (" + userModel.getId() +  ") is already been deleted ");
        }
        user.setRecStatus(GlobalConstants.REC_STATUS_NONACTIVE);
        user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        user.setDeleterId(userModel.getActorId() == null ? 0 :userModel.getActorId());

        return userRepo.save(user);
    }

    
    
}
