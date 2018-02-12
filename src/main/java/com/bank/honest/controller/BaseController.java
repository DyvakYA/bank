package com.bank.honest.controller;

import com.bank.honest.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 1/8/2018.
 */
public abstract class BaseController {

    static final int DEFAULT_GROUP_ID = -1;
    static final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected AccountService accountService;

    protected long getPageCount() {
        long totalCount = accountService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

//    protected long getPageCount(CustomUser customUser) {
//        long totalCount = accountService.countByUser(customUser);
//        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
//    }

}
