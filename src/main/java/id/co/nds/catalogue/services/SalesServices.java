package id.co.nds.catalogue.services;


import java.sql.Timestamp;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.entities.SalesEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.models.SalesModel;
import id.co.nds.catalogue.repos.ProductRepo;
import id.co.nds.catalogue.repos.SalesRepo;
import id.co.nds.catalogue.validators.ProductValidator;


@Service
public class SalesServices {
    @Autowired
    public SalesRepo salesRepo;
    public ProductRepo productRepo;
    public ProductValidator productValidator = new ProductValidator();
   
    @Transactional(propagation =Propagation.REQUIRES_NEW, rollbackFor =  {Exception.class})
    public SalesEntity doSale(SalesModel salesModel)  throws Exception{
        // validation
        productValidator.nullChekcProductId(salesModel.getProductId());

        if (salesModel.getPrice() == null || salesModel.getQuantity() == null){
            throw new ClientException("price or quantity canot be null");
        }

        ProductEntity product = productRepo.findById(salesModel.getProductId()).orElse(null);

        if (product == null){
            throw new NotFoundException("product is not found");
        }

        Integer qty = product.getQuantity();

        if(qty < salesModel.getQuantity()){
            throw new ClientException("product quantity is not enough, only have: " + qty);
        }
        product.setQuantity(qty-salesModel.getQuantity());
        productRepo.save(product);

        SalesEntity sale = new SalesEntity();

        sale.setProductId(salesModel.getProductId());
        sale.setPrice(salesModel.getPrice().doubleValue());
        sale.setQuantity(salesModel.getQuantity());
        sale.setTotalPrice(salesModel.getPrice().doubleValue() * salesModel.getQuantity());
        sale.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return salesRepo.save(sale);
    }
    
    
    
}
