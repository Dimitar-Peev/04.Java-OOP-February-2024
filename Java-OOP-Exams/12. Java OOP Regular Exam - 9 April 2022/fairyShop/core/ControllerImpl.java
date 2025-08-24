package fairyShop.core;

import fairyShop.models.helper.Happy;
import fairyShop.models.helper.Helper;
import fairyShop.models.helper.Sleepy;
import fairyShop.models.instrument.Instrument;
import fairyShop.models.instrument.InstrumentImpl;
import fairyShop.models.present.Present;
import fairyShop.models.present.PresentImpl;
import fairyShop.models.shop.ShopImpl;
import fairyShop.repositories.*;

import java.util.List;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;

    private ShopImpl shop;
    private int craftedPresentsCount;
    int brokenInstrumentsCount;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop = new ShopImpl();
        this.craftedPresentsCount = 0;
        this.brokenInstrumentsCount = 0;
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper = null;

        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        this.helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);

        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> suitableHelpers = this.helperRepository.getModels()
                .stream()
                .filter(e -> e.getEnergy() > 50)
                .toList();

        if (suitableHelpers.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Present present = this.presentRepository.findByName(presentName);

        for (Helper helper : suitableHelpers) {
            this.shop.craft(present, helper);
            this.brokenInstrumentsCount = helper.getInstruments().stream()
                    .filter(Instrument::isBroken).toList().size();

            if (present.isDone()) {
                this.craftedPresentsCount++;
                break;
            }
        }

        return String.format(PRESENT_DONE + COUNT_BROKEN_INSTRUMENTS,
                presentName, String.format(present.isDone() ? "done" : "not done"), this.brokenInstrumentsCount);

    }

    @Override
    public String report() {
        StringBuilder text = new StringBuilder(String.format("%d presents are done!", this.craftedPresentsCount));

        text.append(System.lineSeparator()).append("Helpers info:");

        this.helperRepository.getModels().forEach(helper -> {
            text.append(System.lineSeparator()).append(String.format("Name: %s", helper.getName()));
            text.append(System.lineSeparator()).append(String.format("Energy: %d", helper.getEnergy()));
            text.append(System.lineSeparator()).append(String.format("Instruments: %d not broken left",
                    helper.getInstruments().stream().filter(e -> !e.isBroken()).toList().size()));
        });

        return text.toString();
    }
}
