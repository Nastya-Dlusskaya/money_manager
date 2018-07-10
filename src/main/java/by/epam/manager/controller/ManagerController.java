package by.epam.manager.controller;

import by.epam.manager.model.Transaction;
import by.epam.manager.model.User;
import by.epam.manager.service.TransactionService;
import by.epam.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@Controller()
public class ManagerController {

    private static final String MODE = "mode";
    private static final String INVALID_LOGIN_OR_PASSWORD = "Invalid login or password";
    private static final String MODE_REGISTER = "MODE_REGISTER";
    private static final String WELCOMEPAGE = "welcomepage";
    private static final String MODE_LOGIN = "MODE_LOGIN";
    private static final String AN_OBJECT = "+";
    private static final String MODE_ADD_EXPENSES = "MODE_ADD_EXPENSES";
    private static final String MODE_HOME = "MODE_HOME";
    private static final String TRANSACTIONS = "transactions";
    private static final String MAINPAGE = "mainpage";
    private static final String CAPITAL = "capital";
    private static final String USER = "user";
    private static final String MODE_ADD_INCOME = "MODE_ADD_INCOME";
    private static final String ERROR = "error";

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute(MODE, MODE_REGISTER);
        return WELCOMEPAGE;
    }

    @RequestMapping("main")
    public String home(@ModelAttribute User user, Model model, HttpSession session){
        User foundUser = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if(foundUser != null){
            model.addAttribute(MODE, MODE_HOME);
            session.setAttribute(USER, foundUser.getId());
            model.addAttribute(TRANSACTIONS, transactionService.findAllByUser(foundUser.getId()));
            return MAINPAGE;
        }
        model.addAttribute(ERROR, INVALID_LOGIN_OR_PASSWORD);
        model.addAttribute(MODE, MODE_LOGIN);
        return WELCOMEPAGE;
    }

    @RequestMapping("show-login")
    public String login(Model model){
        model.addAttribute(MODE, MODE_LOGIN);
        return WELCOMEPAGE;
    }

    @RequestMapping("show-add-income")
    public String showAddIncome(HttpSession session,  Model model){
        model.addAttribute(MODE, MODE_ADD_INCOME);
        int user = (int)session.getAttribute(USER);
        Transaction transaction = transactionService.findLastTransactionByUser(user);
        model.addAttribute(CAPITAL, getCurrentCapital(transaction));
        return MAINPAGE;
    }

    @RequestMapping("show-add-expenses")
    public String showAddExpenses(HttpSession session, Model model){
        model.addAttribute(MODE, MODE_ADD_EXPENSES);
        int user = (int)session.getAttribute(USER);
        Transaction transaction = transactionService.findLastTransactionByUser(user);
        model.addAttribute(CAPITAL, getCurrentCapital(transaction));
        return MAINPAGE;
    }

    @PostMapping("save-user")
    public String registerUser(@ModelAttribute@Valid User user, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute(ERROR, errors.getFieldErrors());
            model.addAttribute(MODE, MODE_REGISTER);
            return WELCOMEPAGE;
        }
        userService.saveUser(user);
        model.addAttribute(MODE, MODE_HOME);
        return MAINPAGE;
    }

    @PostMapping("add-income")
    public String addIncome(@ModelAttribute@Valid Transaction transaction, Errors errors,
                            Model model, HttpSession session){
        if(errors.hasErrors()){
            model.addAttribute(ERROR, errors.getFieldErrors());
            model.addAttribute(MODE, MODE_ADD_INCOME);
            int user = (int)session.getAttribute(USER);
            Transaction lastTransaction = transactionService.findLastTransactionByUser(user);
            model.addAttribute(CAPITAL, getCurrentCapital(lastTransaction));
            return MAINPAGE;
        }
        int user = (int)session.getAttribute(USER);
        transaction.setDate(new Date());
        transaction.setUser(user);
        transactionService.saveUser(transaction);
        model.addAttribute(TRANSACTIONS, transactionService.findAll());
        model.addAttribute(MODE, MODE_HOME);
        return MAINPAGE;
    }

    @PostMapping("add-expenses")
    public String addExpenses(@ModelAttribute@Valid Transaction transaction, Errors errors, Model model, HttpSession session){
        if(errors.hasErrors()){
            model.addAttribute(ERROR, errors.getFieldErrors());
            model.addAttribute(MODE, MODE_ADD_EXPENSES);
            int user = (int)session.getAttribute(USER);
            Transaction lastTransaction = transactionService.findLastTransactionByUser(user);
            model.addAttribute(CAPITAL, getCurrentCapital(lastTransaction));
            return MAINPAGE;
        }
        int user = (int)session.getAttribute(USER);
        transaction.setDate(new Date());
        transaction.setUser(user);
        transactionService.saveUser(transaction);
        model.addAttribute(TRANSACTIONS, transactionService.findAll());
        model.addAttribute(MODE, MODE_HOME);
        return MAINPAGE;
    }

    private BigDecimal getCurrentCapital(Transaction transaction){
        BigDecimal capital;
        if(transaction.getType().equals(AN_OBJECT)){
            capital = transaction.getCapital().add(transaction.getSum());
        } else{
            capital = transaction.getCapital().subtract(transaction.getSum());
        }
        return capital;
    }

    @RequestMapping("logout")
    private String logout(HttpSession session, Model model){
        session.removeAttribute(USER);
        model.addAttribute(MODE, MODE_LOGIN);
        return WELCOMEPAGE;
    }
}
