package com.moon.asm.sample.ticket;

/**
 * @author yujiangtao
 * @date 2018/1/17 15:33
 */
public class Station implements TicketService {

    public void sellTicket() {
        System.out.println("\n\t售票...\n");
    }

    public void inquire() {
        System.out.println("\n\t问询...\n");
    }

    public void withdraw() {
        System.out.println("\n\t退票...\n");
    }
}
