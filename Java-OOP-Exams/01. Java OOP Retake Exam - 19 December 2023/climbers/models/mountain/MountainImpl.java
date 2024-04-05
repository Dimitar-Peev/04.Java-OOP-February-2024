package climbers.models.mountain;

import java.util.ArrayList;
import java.util.Collection;

public class MountainImpl implements Mountain {
    private String name;
    private Collection<String> peaksList;

    public MountainImpl(String name) {
        this.name = name;
        this.peaksList = new ArrayList<>();
    }

    @Override
    public Collection<String> getPeaksList() {
        return this.peaksList;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
