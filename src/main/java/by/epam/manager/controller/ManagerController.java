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

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute(MODE, "MODE_REGISTER");
        return "welcomepage";
    }

    @RequestMapping("main")
    public String home(@ModelAttribute User user, Model model, HttpSession session){
        User foundUser = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if(foundUser != null){
            model.addAttribute(MODE, "MODE_HOME");
            session.setAttribute("user", foundUser.getId());
            model.addAttribute("transactions", transactionService.findAllByUser(foundUser.getId()));
            return "mainpage";
        }
        model.addAttribute("error", "Invalid login or password");
        model.addAttribute(MODE, "MODE_LOGIN");
        return "welcomepage";
    }

    @RequestMapping("show-login")
    public String login(Model model){
        model.addAttribute(MODE, "MODE_LOGIN");
        return "welcomepage";
    }

    @RequestMapping("show-add-income")
    public String showAddIncome(HttpSession session,  Model model){
        model.addAttribute(MODE, "MODE_ADD_INCOME");
        int user = (int)session.getAttribute("user");
        Transaction transaction = transactionService.findLastTransactionByUser(user);
        model.addAttribute("capital", getCurrentCapital(transaction));
        return "mainpage";
    }

    @RequestMapping("show-add-expenses")
    public String showAddExpenses(HttpSession session, Model model){
        model.addAttribute(MODE, "MODE_ADD_EXPENSES");
        int user = (int)session.getAttribute("user");
        Transaction transaction = transactionService.findLastTransactionByUser(user);
        model.addAttribute("capital", getCurrentCapital(transaction));
        return "mainpage";
    }

    @PostMapping("save-user")
    public String registerUser(@ModelAttribute@Valid User user, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("error", errors.getFieldErrors());
            model.addAttribute(MODE, "MODE_REGISTER");
            return "welcomepage";
        }
        userService.saveUser(user);
        model.addAttribute(MODE, "MODE_HOME");
        return "mainpage";
    }

    @PostMapping("add-income")
    public String addIncome(@ModelAttribute@Valid Transaction transaction, Errors errors,
                            Model model, HttpSession session){
        if(errors.hasErrors()){
            model.addAttribute("error", errors.getFieldErrors());
            model.addAttribute(MODE, "MODE_ADD_INCOME");
            int user = (int)session.getAttribute("user");
            Transaction lastTransaction = transactionService.findLastTransactionByUser(user);
            model.addAttribute("capital", getCurrentCapital(lastTransaction));
            return "mainpage";
        }
        int user = (int)session.getAttribute("user");
        transaction.setDate(new Date());
        transaction.setUser(user);
        transactionService.saveUser(transaction);
        model.addAttribute("transactions", transactionService.findAll());
        model.addAttribute(MODE, "MODE_HOME");
        return "mainpage";
    }

    @PostMapping("add-expenses")
    public String addExpenses(@ModelAttribute@Valid Transaction transaction, Errors errors, Model model, HttpSession session){
        if(errors.hasErrors()){
            model.addAttribute("error", errors.getFieldErrors());
            model.addAttribute(MODE, "MODE_ADD_EXPENSES");
            int user = (int)session.getAttribute("user");
            Transaction lastTransaction = transactionService.findLastTransactionByUser(user);
            model.addAttribute("capital", getCurrentCapital(lastTransaction));
            return "mainpage";
        }
        int user = (int)session.getAttribute("user");
        transaction.setDate(new Date());
        transaction.setUser(user);
        transactionService.saveUser(transaction);
        model.addAttribute("transactions", transactionService.findAll());
        model.addAttribute(MODE, "MODE_HOME");
        return "mainpage";
    }

    private BigDecimal getCurrentCapital(Transaction transaction){
        BigDecimal capital;
        if(transaction.getType().equals("+")){
            capital = transaction.getCapital().add(transaction.getSum());
        } else{
            capital = transaction.getCapital().subtract(transaction.getSum());
        }
        return capital;
    }

}
