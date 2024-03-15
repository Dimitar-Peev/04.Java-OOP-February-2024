package christmasPastryShop.core;

import christmasPastryShop.common.enums.BoothType;
import christmasPastryShop.common.enums.CocktailType;
import christmasPastryShop.common.enums.DelicacyType;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.OpenBooth;
import christmasPastryShop.entities.booths.PrivateBooth;
import christmasPastryShop.entities.cocktails.Hibernation;
import christmasPastryShop.entities.cocktails.MulledWine;
import christmasPastryShop.entities.delicacies.Gingerbread;
import christmasPastryShop.entities.delicacies.Stolen;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import static christmasPastryShop.common.ExceptionMessages.BOOTH_EXIST;
import static christmasPastryShop.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalIncome;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
        this.totalIncome = 0;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {

        Delicacy delicacy = this.delicacyRepository.getByName(name);

        if (delicacy != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
		
	    DelicacyType delicacyType = DelicacyType.valueOf(type);

        switch (delicacyType) {
            case Gingerbread:
                this.delicacyRepository.add(new Gingerbread(name, price));
                break;
            case Stolen:
                this.delicacyRepository.add(new Stolen(name, price));
                break;
        }

        return String.format(DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {

        Cocktail cocktail = this.cocktailRepository.getByName(name);

        if (cocktail != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        CocktailType cocktailType = CocktailType.valueOf(type);

        switch (cocktailType) {
            case MulledWine:
                this.cocktailRepository.add(new MulledWine(name, size, brand));
                break;
            case Hibernation:
                this.cocktailRepository.add(new Hibernation(name, size, brand));
                break;
        }

        return String.format(COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {

        Booth booth = this.boothRepository.getByNumber(boothNumber);

        if (booth != null) {
            throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
        }
		
        BoothType boothType = BoothType.valueOf(type);

        switch (boothType) {
            case OpenBooth:
                this.boothRepository.add(new OpenBooth(boothNumber, capacity));
                break;
            case PrivateBooth:
                this.boothRepository.add(new PrivateBooth(boothNumber, capacity));
                break;
        }

        return String.format(BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {

        Booth booth = this.boothRepository.getAll()
                .stream()
                .filter(e -> !e.isReserved() && e.getCapacity() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        if (booth == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        booth.reserve(numberOfPeople);

        return String.format(BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {

        Booth booth = this.boothRepository.getByNumber(boothNumber);

        double totalBill = booth.getBill();

        this.totalIncome += totalBill;

        booth.clear();

        return String.format(BILL, booth.getBoothNumber(), totalBill);
    }

    @Override
    public String getIncome() {
        return String.format(TOTAL_INCOME, totalIncome);
    }
}
