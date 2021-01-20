package by.realovka.web.dao.comparator;

import by.realovka.web.dao.model.Theme;

import java.util.Comparator;

public class ThemeComparator implements Comparator<Theme> {

    @Override
    public int compare(Theme o1, Theme o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
