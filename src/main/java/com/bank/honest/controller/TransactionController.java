package com.bank.honest.controller;

import com.bank.honest.model.dto.TransactionDTO;
import com.bank.honest.model.entity.Currency;
import com.bank.honest.model.entity.Transaction;
import com.bank.honest.model.entity.TransactionStatus;
import com.bank.honest.model.entity.TransactionType;
import com.bank.honest.model.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/13/2018.
 */
@RestController
public class TransactionController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public List<TransactionDTO> transactions(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<TransactionDTO> result = transactionService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public TransactionDTO transaction(@PathVariable(value = "id") Long transaction_id) {
        TransactionDTO result = transactionService.findTransaction(transaction_id);
        return result;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestParam String number,
                                       @RequestParam TransactionStatus status,
                                       @RequestParam TransactionType type,
                                       @RequestParam Long amount,
                                       @RequestParam Currency currency) {

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .number(number)
                .status(status)
                .type(type)
                .amount(amount)
                .currency(currency)
                .build();
        transactionService.createTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id", required = false) Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/transactions/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id[]", required = false) Long[] ids) {
        transactionService.deleteTransaction(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestParam String number,
                                       @RequestParam TransactionStatus status,
                                       @RequestParam TransactionType type,
                                       @RequestParam Long amount,
                                       @RequestParam Currency currency) {

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .number(number)
                .status(status)
                .type(type)
                .amount(amount)
                .currency(currency)
                .build();
        transactionService.createTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/transactions/search/{date}", method = RequestMethod.GET)
    public List<TransactionDTO> transactionByPattern(@PathVariable(value = "date") String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("hh:mm:ss");
        List<TransactionDTO> result = transactionService.findTransaction(format.parse(date));
        return result;
    }
}
