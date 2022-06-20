package id.co.nds.catalogue.services;


import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.SalesEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.models.SalesModel;
import id.co.nds.catalogue.repos.SalesRepo;
import id.co.nds.catalogue.validators.ProductValidator;


@Service
public class SalesServices {
    @Autowired
    public SalesRepo salesRepo;
    public ProductValidator productValidator = new ProductValidator();
   
    @Transactional(propagation =Propagation.REQUIRES_NEW, rollbackFor =  {Exception.class})
    public SalesEntity doSale(SalesModel salesModel)  throws Exception{
        // validation
        productValidator.nullChekcProductId(salesModel.getProductId());

        if (salesModel.getPrice() == null || salesModel.getQuantity() == null){
            throw new ClientException("price or quantity canot be null");
        }

        SalesEntity sale = new SalesEntity();

        sale.setProductId(salesModel.getProductId());
        sale.setPrice(salesModel.getPrice().doubleValue());
        sale.setQuantity(salesModel.getQuantity());
        sale.setTotalPrice(salesModel.getPrice().doubleValue() * salesModel.getQuantity());
        sale.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return salesRepo.save(sale);
    }
    
    
    
}
