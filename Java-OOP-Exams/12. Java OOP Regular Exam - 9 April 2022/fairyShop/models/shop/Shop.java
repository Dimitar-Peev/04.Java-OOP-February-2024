package fairyShop.models.shop;

import fairyShop.models.helper.Helper;
import fairyShop.models.present.Present;

public interface Shop {
    void craft(Present present, Helper helper);
}
