package kob.backend.controller.user.bot;


import kob.backend.pojo.Bot;
import kob.backend.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetListController {

    @Autowired
    private GetListService getListService;

    @GetMapping("/user/bot/getlist/")
    public List<Bot> getList(){
        return getListService.getList();
    }
}
