package harpoonDiver.core;

import harpoonDiver.core.factory.DiverFactory;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Diver> diverRepository;
    private Repository<DivingSite> divingSiteRepository;
    private int count;

    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
        this.count = 0;
    }

    @Override
    public String addDiver(String kind, String diverName) {
        final Diver diver = DiverFactory.createDiver(kind, diverName);
        this.diverRepository.add(diver);
        return this.getOperationResponse(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        divingSite.getSeaCreatures().addAll(Arrays.asList(seaCreatures));
        this.divingSiteRepository.add(divingSite);
        return this.getOperationResponse(DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        final Diver diver = this.diverRepository.byName(diverName);
        if (diver == null) {
            throw new IllegalArgumentException(this.getOperationResponse(DIVER_DOES_NOT_EXIST, diverName));
        }
        this.diverRepository.remove(diver);
        return this.getOperationResponse(DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        final List<Diver> divers = this.diverRepository.getCollection().stream()
                .filter(d -> d.getOxygen() > 30)
                .collect(Collectors.toList());

        if (divers.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }

        final DivingSite divingSite = this.divingSiteRepository.byName(siteName);
        final Diving diving = new DivingImpl();
        diving.searching(divingSite, divers);
        long removed = divers.stream().filter(d -> d.getOxygen() == 0).count();
        this.count++;
        return this.getOperationResponse(SITE_DIVING, siteName, removed);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getOperationResponse(FINAL_DIVING_SITES,this.count)).append(System.lineSeparator());
        builder.append(FINAL_DIVERS_STATISTICS).append(System.lineSeparator());
        this.diverRepository.getCollection().forEach(d->builder.append(d).append(System.lineSeparator()));
        return builder.toString().trim();
    }

    private String getOperationResponse(String template, Object... parameters) {
        return String.format(template, parameters);
    }
}
