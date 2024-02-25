package greedyTimes;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentTotalQuantity;
    private long gold;
    private Map<String, Long> gems;
    private Map<String, Long> cash;
    private boolean goldIsAdded;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.gems = new HashMap<>();
        this.cash = new HashMap<>();
        this.goldIsAdded = false;
    }

    public void addCash(String item, long quantity) {
        long totalGems = getTotalGems();
        long totalCash = getTotalCash();
        if (hasFreeCapacity(quantity) && totalGems >= totalCash + quantity) {

            if (!this.cash.containsKey(item)) {
                this.cash.put(item, quantity);
            } else {
                this.cash.put(item, this.cash.get(item) + quantity);
            }
            this.currentTotalQuantity += quantity;
        }

    }

    public void addGems(String item, long quantity) {
        long totalGems = getTotalGems();
        if (hasFreeCapacity(quantity) && totalGems + quantity <= this.gold) {

            if (!this.gems.containsKey(item)) {
                this.gems.put(item, quantity);
            } else {
                this.gems.put(item, this.gems.get(item) + quantity);
            }
            this.currentTotalQuantity += quantity;
        }
    }

    public void addGold(long quantity) {
        if (currentTotalQuantity + quantity <= capacity) {
            this.goldIsAdded = true;
            this.gold += quantity;
            this.currentTotalQuantity += quantity;
        }
    }

    private long getTotalCash() {
        return this.cash.values().stream().mapToLong(e -> e).sum();
    }

    private long getTotalGems() {
        return this.gems.values().stream().mapToLong(e -> e).sum();
    }

    private boolean hasFreeCapacity(long quantity) {
        return currentTotalQuantity + quantity <= capacity;
    }
    private Comparator<Map.Entry<String, Long>> getComparator(){
        return new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                int res = e2.getKey().compareTo(e1.getKey());
                if (res == 0) {
                    res = e1.getValue().compareTo(e2.getValue());
                }
                return res;
            }
        };
    }

    public void printContent() {
        StringBuilder sb = new StringBuilder();

        if (this.goldIsAdded) {
            sb.append(String.format("<Gold> $%d", this.gold)).append(System.lineSeparator());
            sb.append(String.format("##Gold - %d", this.gold)).append(System.lineSeparator());
        }

        if (!this.gems.isEmpty()) {
            sb.append(String.format("<Gem> $%d", getTotalGems())).append(System.lineSeparator());
            this.gems.entrySet().stream().sorted(getComparator()).forEach(entry -> sb.append(String.format("##%s - %d", entry.getKey(), entry.getValue()))
                    .append(System.lineSeparator()));
        }

        if (!this.cash.isEmpty()) {
            sb.append(String.format("<Cash> $%d", getTotalCash())).append(System.lineSeparator());
            this.cash.entrySet().stream().sorted(getComparator()).forEach(entry -> sb.append(String.format("##%s - %d", entry.getKey(), entry.getValue()))
                    .append(System.lineSeparator()));
        }

        System.out.print(sb);
    }
}