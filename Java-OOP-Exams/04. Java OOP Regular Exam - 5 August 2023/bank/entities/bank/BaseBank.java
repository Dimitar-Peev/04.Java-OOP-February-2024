package bank.entities.bank;

import bank.common.StringUtils;
import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static bank.common.ExceptionMessages.*;

public abstract class BaseBank implements Bank {
    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;

    protected BaseBank(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        if (StringUtils.nullOrEmpty(name)) {
            throw new IllegalArgumentException(BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Client> getClients() {
        return this.clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return this.loans;
    }

    @Override
    public void addClient(Client client) {
        if (capacity <= clients.size()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }
        clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        return loans.stream().mapToInt(Loan::getInterestRate).sum();
    }

    @Override
    public String getStatistics() {
        String clientsInfo = clients.isEmpty()
                ? "none"
                : clients.stream().map(Client::getName).collect(Collectors.joining(", "));

        return String.format("Name: %s, Type: %s%n" +
                        "Clients: %s%n" +
                        "Loans: %d, Sum of interest rates: %d",
                getName(), this.getClass().getSimpleName(),
                clientsInfo,
                loans.size(), sumOfInterestRates());
    }
}
