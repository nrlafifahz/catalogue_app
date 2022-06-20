package id.co.nds.catalogue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.entities.SalesEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.SalesModel;
import id.co.nds.catalogue.services.SalesServices;


@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    public SalesServices salesService;

    @PostMapping ("/add")
    public ResponseEntity<ResponseModel> postSale(@RequestBody SalesModel salesModel){
        try{
            SalesEntity sale = salesService.doSale(salesModel);

            ResponseModel response = new ResponseModel();
            response.setMsg( "New Sale is succesfully added");
            response.setData(sale);
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
    
}
