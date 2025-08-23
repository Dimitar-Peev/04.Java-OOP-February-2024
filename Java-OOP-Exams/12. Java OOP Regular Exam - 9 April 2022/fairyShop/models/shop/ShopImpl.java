package fairyShop.models.shop;

import fairyShop.models.helper.Helper;
import fairyShop.models.instrument.Instrument;
import fairyShop.models.present.Present;

import java.util.Collection;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {

        Collection<Instrument> allInstruments = helper.getInstruments();

        for (Instrument instrument : allInstruments) {
            while (!present.isDone() && helper.canWork() && !instrument.isBroken()) {
                present.getCrafted();
                helper.work();
                instrument.use();
            }
        }
    }
}
