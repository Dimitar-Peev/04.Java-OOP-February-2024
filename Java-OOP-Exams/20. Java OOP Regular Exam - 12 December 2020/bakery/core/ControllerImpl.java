package bakery.core;

import bakery.common.enums.BakedFoodType;
import bakery.common.enums.DrinkType;
import bakery.common.enums.TableTYpe;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import static bakery.common.OutputMessages.*;
import static bakery.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final FoodRepository<BakedFood> foodRepository;
    private final DrinkRepository<Drink> drinkRepository;
    private final TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.totalIncome = 0;
    }

    @Override
    public String addFood(String type, String name, double price) {

        BakedFood bakedFood = this.foodRepository.getByName(name);

        if (bakedFood != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST,
                    bakedFood.getClass().getSimpleName(), name));
        }

        BakedFoodType foodType = BakedFoodType.valueOf(type); 

        bakedFood = foodType.equals(BakedFoodType.Bread)
                ? new Bread(name, price)
                : new Cake(name, price);

        this.foodRepository.add(bakedFood);

        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {

        Drink drink = this.drinkRepository.getByNameAndBrand(name, brand);

        if (drink != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST,
                    type, name));
        }

        DrinkType drinkType = DrinkType.valueOf(type); 

        drink = drinkType.equals(DrinkType.Tea)
                ? new Tea(name, portion, brand)
                : new Water(name, portion, brand);

        this.drinkRepository.add(drink);

        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {

        Table table = this.tableRepository.getByNumber(tableNumber);

        if (table != null) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }

        TableTYpe tableType = TableTYpe.valueOf(type);

        table = tableType.equals(TableTYpe.InsideTable)
                ? new InsideTable(tableNumber, capacity)
                : new OutsideTable(tableNumber, capacity);

        this.tableRepository.add(table);

        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
 
        for (Table table : this.tableRepository.getAll()) {
            if (!table.isReserved() && table.getCapacity() >= numberOfPeople) {
                table.reserve(numberOfPeople);
                return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {

        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        BakedFood bakedFood = this.foodRepository.getByName(foodName);
        if (bakedFood == null || !table.isReserved()) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }

        table.orderFood(bakedFood);

        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
       
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Drink drink = this.drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        table.orderDrink(drink);
        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
     
        Table table = this.tableRepository.getByNumber(tableNumber);

        double bill = table.getBill();
        table.clear();
        this.totalIncome += bill;

        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        
        StringBuilder text = new StringBuilder();
        for (Table table : this.tableRepository.getAll()) {
            if (!table.isReserved()) {
                text.append(table.getFreeTableInfo()).append(System.lineSeparator());
            }
        }
        return text.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, this.totalIncome);
    }
}
