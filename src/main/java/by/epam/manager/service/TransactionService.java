package by.epam.manager.service;

import by.epam.manager.dao.TransactionRepository;
import by.epam.manager.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findAll(){
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction: transactionRepository.findAll()){
            transactions.add(transaction);
        }
        return transactions;
    }

    public void saveUser(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public List<Transaction> findAllByUser(int user){
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction: transactionRepository.findAllByUser(user)){
            transactions.add(transaction);
        }
        return transactions;
    }

    public Transaction findLastTransactionByUser(int user){
        List<Transaction> transactions = findAllByUser(user);
        return transactions.get(transactions.size() - 1);
    }
}
