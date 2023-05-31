package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AviaSoulsTest {

    @Test
    public void test1() {
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 15000, 12, 16);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 15000, 12, 16);

        Assertions.assertEquals(ticket, ticket1);

    }

    @Test
    public void test2() {
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 15000, 12, 16);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 25000, 12, 16);

        Assertions.assertTrue(ticket.compareTo(ticket1) < 0);

    }

    @Test
    public void test3() {
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 35000, 12, 16);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 25000, 12, 16);

        Assertions.assertTrue(ticket.compareTo(ticket1) > 0);

    }

    @Test
    public void test4() {
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 25000, 12, 16);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 25000, 12, 16);

        Assertions.assertTrue(ticket.compareTo(ticket1) == 0);
    }


    @Test
    public void test5() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 15000, 12, 16);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 25000, 12, 16);
        Ticket ticket2 = new Ticket("Казань", "Хабаровск", 35000, 12, 16);
        Ticket ticket3 = new Ticket("Саратов", "Белгород", 30000, 12, 16);


        manager.add(ticket);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] expected = {ticket, ticket1, ticket2, ticket3};
        Assertions.assertArrayEquals(expected, manager.findAll());
    }


    @Test
    public void test6() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 15000, 12, 16);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 25000, 12, 16);
        Ticket ticket2 = new Ticket("Казань", "Хабаровск", 35000, 12, 16);
        Ticket ticket3 = new Ticket("Саратов", "Белгород", 30000, 12, 16);


        manager.add(ticket);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] expected = {ticket, ticket1};
        Assertions.assertArrayEquals(manager.search("Нижний Новгород", "Сочи"), expected);
    }

    @Test
    public void test7() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 15000, 12, 17);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 25000, 11, 15);
        Ticket ticket2 = new Ticket("Казань", "Хабаровск", 35000, 19, 23);
        Ticket ticket3 = new Ticket("Саратов", "Белгород", 30000, 3, 4);


        manager.add(ticket);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] result = manager.searchAndSoftBy("Казань", "Хабаровск", new TicketTimeComparator());
        Assertions.assertEquals("Казань", result[0].getFrom());
        Assertions.assertEquals("Хабаровск", result[0].getTo());
        Assertions.assertEquals(19, result[0].getTimeFrom());
        Assertions.assertEquals(23, result[0].getTimeTo());
    }

    @Test
    public void test8() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 15000, 12, 17);
        Ticket ticket1 = new Ticket("Нижний Новгород", "Сочи", 25000, 11, 15);
        Ticket ticket2 = new Ticket("Казань", "Хабаровск", 35000, 19, 23);
        Ticket ticket3 = new Ticket("Саратов", "Белгород", 30000, 3, 4);


        manager.add(ticket);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.searchAndSoftBy("Саратов", "Москва", new TicketTimeComparator());

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void test9() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket = new Ticket("Нижний Новгород", "Сочи", 15000, 11, 19);//8
        Ticket ticket1 = new Ticket("Белгород", "Саратов", 25000, 11, 15);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Сочи", 35000, 11, 20);//9
        Ticket ticket3 = new Ticket("Нижний Новгород", "Сочи", 30000, 19, 20);//1


        manager.add(ticket);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] expected = new Ticket[]{ticket3, ticket, ticket2};
        Ticket[] actual = manager.searchAndSoftBy("Нижний Новгород", "Сочи", new TicketTimeComparator());

        Assertions.assertArrayEquals(expected, actual);
    }

}