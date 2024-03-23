package bank.core;

import bank.entities.bank.*;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.*;
import bank.repositories.LoanRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static bank.common.ConstantMessages.*;
import static bank.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private LoanRepository loans;
    private Map<String, Bank> banks;
    public ControllerImpl(){
        this.loans = new LoanRepository();
        this.banks = new LinkedHashMap<>();
    }

    @Override
    public String addBank(String type, String name) {
        Bank bank = null;

        if (type.equals("CentralBank")) {
            bank = new CentralBank(name);
        } else if (type.equals("BranchBank")) {
            bank = new BranchBank(name);
        } else {
            throw new IllegalArgumentException(INVALID_BANK_TYPE);
        }

        banks.put(bank.getName(), bank);

        return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String addLoan(String type) {
        Loan loan = null;

        if (type.equals("StudentLoan")) {
            loan = new StudentLoan();
        } else if (type.equals("MortgageLoan")) {
            loan = new MortgageLoan();
        } else {
            throw new IllegalArgumentException(INVALID_LOAN_TYPE);
        }

        loans.addLoan(loan);

        return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan loan = loans.findFirst(loanType);

        if (loan == null) {
            throw new IllegalArgumentException(String.format(NO_LOAN_FOUND, loanType));
        }

        loans.removeLoan(loan);

        banks.get(bankName).addLoan(loan);

        return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Client client = null;
        if (clientType.equals("Student")) {
            client = new Student(clientName, clientID, income);
        } else if (clientType.equals("Adult")) {
            client = new Adult(clientName, clientID, income);
        } else {
            throw new IllegalArgumentException(INVALID_CLIENT_TYPE);
        }

        Bank bank = banks.get(bankName);

        String output = UNSUITABLE_BANK;

        if (isClientCompatible(clientType, bank)) {
            output = String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);
            bank.addClient(client);
        }

        return output;
    }

    private boolean isClientCompatible(String clientType, Bank bank) {
        String bankType = bank.getClass().getSimpleName();
        return (clientType.equals("Student") && bankType.equals("BranchBank"))
                || (clientType.equals("Adult") && bankType.equals("CentralBank"));
    }

    @Override
    public String finalCalculation(String bankName) {
        Bank bank = banks.get(bankName);
        double loansSum = bank.getLoans().stream().mapToDouble(Loan::getAmount).sum();
        double clientsIncomeSum = bank.getClients().stream().mapToDouble(Client::getIncome).sum();
        double funds = loansSum + clientsIncomeSum;
        return String.format(FUNDS_BANK, bankName, funds);
    }

    @Override
    public String getStatistics() {
        return banks.values().stream().map(Bank::getStatistics).collect(Collectors.joining(System.lineSeparator()));
    }
}
