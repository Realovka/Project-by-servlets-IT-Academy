package by.realovka.web.dao.comparator;

import by.realovka.web.dao.model.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getUserName().compareTo(o2.getUserName());
    }
}
