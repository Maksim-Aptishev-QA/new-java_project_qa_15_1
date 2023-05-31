package ru.netology.javaqa;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        int fightTime1 = t1.getTimeTo() - t1.getTimeFrom();
        int fightTime2 = t2.getTimeTo() - t2.getTimeFrom();

        if (fightTime1 < fightTime2) {
            return -1;
        } else if (fightTime1 > fightTime2) {
            return 1;
        } else return 0;

    }

}
