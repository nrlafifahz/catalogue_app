package id.co.nds.catalogue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.entities.LoanEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.LoanModel;
import id.co.nds.catalogue.services.LoanService;


@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    public LoanService loanService;

    @PostMapping ("/add")
    public ResponseEntity<ResponseModel> postLoan(@RequestBody LoanModel loansModel){
        try{
            LoanEntity loan = loanService.doLoan(loansModel);

            ResponseModel response = new ResponseModel();
            response.setMsg( "New Loan is succesfully added");
            response.setData(loan);
            return ResponseEntity.ok(response);

        } catch(ClientException e){
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        }catch(Exception e){
            ResponseModel response =new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
        
    }

    @GetMapping ("/get")
    public ResponseEntity<ResponseModel>getLoan(){
        try{
            List<LoanEntity> loan = loanService.findAll();

            ResponseModel response = new ResponseModel();
            response.setMsg( "New Loan is succesfully added");
            response.setData(loan);
            return ResponseEntity.ok(response);

        }catch(Exception e){
            ResponseModel response =new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
        
    }
    
}

