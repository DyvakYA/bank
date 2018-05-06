package com.bank.honest.model.service;

import com.bank.honest.model.dao.AccountRepository;
import com.bank.honest.model.dao.TransactionRepository;
import com.bank.honest.model.dto.TransactionDTO;
import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.Transaction;
import com.bank.honest.model.entity.enums.Currency;
import com.bank.honest.model.entity.enums.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/21/2018.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void createTransaction(Transaction transaction) {
        Account sourceAccount = accountRepository.findAccountByNumber(transaction.getSourceName());
        Long sourceAccountSum = sourceAccount.getAmount() - transaction.getSum();
        sourceAccount.setAmount(sourceAccountSum);
        accountRepository.save(sourceAccount);
        if(accountRepository.existsByAccountNumber(transaction.getDestinationName()) ){
            Account destinationAccount = accountRepository.findAccountByNumber(transaction.getDestinationName());
            Long destinationAccountSum = destinationAccount.getAmount() + transaction.getSum();
            destinationAccount.setAmount(destinationAccountSum);
            accountRepository.save(destinationAccount);
        }
        transactionRepository.save(transaction);
    }

    @Transactional
    public boolean createTransaction(String number, Long sum, Currency currency, TransactionStatus status) {
        if (transactionRepository.existsByNumber(number))
            return false;

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .number(number)
                .sum(sum)
                .currency(currency)
                .status(status)
                .build();
        transactionRepository.save(transaction);

        return true;
    }

    @Transactional
    public List<TransactionDTO> findAll(Pageable pageable) {
        List<Transaction> transactions = transactionRepository.findAll(pageable).getContent();
        List<TransactionDTO> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            result.add(transaction.toDTO());
        }
        return result;
    }

    @Transactional(readOnly = true)
    public TransactionDTO findTransaction(Long transaction_id) {
        Transaction transaction = transactionRepository.findOne(transaction_id);
        TransactionDTO result = transaction.toDTO();
        return result;
    }

    @Transactional
    public List<TransactionDTO> findTransaction(Date date) {
        List<TransactionDTO> result = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findByDate(date);
        for (Transaction transaction : transactions)
            result.add(transaction.toDTO());
        return result;
    }

    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.delete(id);
    }

    @Transactional
    public void deleteTransaction(Long[] toDelete) {
        for (long id : toDelete)
            transactionRepository.delete(id);
    }
}
