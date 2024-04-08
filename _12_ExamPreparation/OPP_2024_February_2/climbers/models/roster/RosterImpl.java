package climbers.models.roster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class RosterImpl implements Roster {

    private Collection<String> peaks;

    public RosterImpl() {
        this.peaks = new LinkedList<>();
    }

    @Override
    public Collection<String> getPeaks() {
        return this.peaks;
    }
}
