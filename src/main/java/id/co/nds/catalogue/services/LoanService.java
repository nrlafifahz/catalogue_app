package id.co.nds.catalogue.services;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.LoanEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.models.LoanModel;
import id.co.nds.catalogue.repos.LoanRepo;
import id.co.nds.catalogue.validators.RoleValidator;
import id.co.nds.catalogue.validators.UserValidator;


@Service
public class LoanService {
    @Autowired
    public LoanRepo loanRepo;
    public UserValidator userValidator = new UserValidator();
    public RoleValidator roleValidator = new RoleValidator();

    @Transactional(propagation =Propagation.REQUIRES_NEW, rollbackFor =  {Exception.class})
    public LoanEntity doLoan(LoanModel loanModel)  throws Exception{
        // validation
        userValidator.nullChekcUserId(loanModel.getUserId());
        userValidator.validateUserId(loanModel.getUserId());;
        roleValidator.nullChekcRoleId(loanModel.getRoleId());
        roleValidator.validateRoleId(loanModel.getRoleId());

        if (loanModel.getLoanAmount() == null || loanModel.getLoanTerm() == null || loanModel.getInterestRate() == null || loanModel.getCustomerName() == null){
            throw new ClientException("loanAmount, loanTerm  or interestRate canot be null");
        }

        LoanEntity loan = new LoanEntity();

        loan.setUserId(loanModel.getUserId());
        loan.setRoleId(loanModel.getRoleId());
        loan.setLoanAmount(loanModel.getLoanAmount().doubleValue());
        loan.setLoanTerm(loanModel.getLoanTerm());
        loan.setInterestRate(loanModel.getInterestRate().doubleValue());
        loan.setTotalLoan(loanModel.getLoanAmount().doubleValue() / loanModel.getLoanTerm() * loanModel.getInterestRate().doubleValue() / 100 );
        loan.setCustomerName(loanModel.getCustomerName());
        loan.setStartDate(new Timestamp(System.currentTimeMillis()));
        return loanRepo.save(loan);
    }

    public List<LoanEntity> findAll(){
        List<LoanEntity> users = new ArrayList<>();
        loanRepo.findAll().forEach(users::add);
        return users;
    }
    
    
}
