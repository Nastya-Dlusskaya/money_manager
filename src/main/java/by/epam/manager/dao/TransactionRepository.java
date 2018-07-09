package by.epam.manager.dao;

import by.epam.manager.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAllByUser(int user);
}
