package com.moon.asm.sample.ticket;

/**
 * 售票服务接口
 * @author yujiangtao
 * @date 2018/1/17 15:25
 */
public interface TicketService {

    // 售票
    public void sellTicket();

    // 问询
    public void inquire();

    // 退票
    public void withdraw();
}
