package kob.backend.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import kob.backend.mapper.BotMapper;
import kob.backend.pojo.Bot;
import kob.backend.pojo.User;
import kob.backend.service.impl.utils.UserDetailsImpl;
import kob.backend.service.user.bot.AddService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String title = data.get("title");
        String description = data.get(("description"));
        String content = data.get("content");

        Map<String, String> map = new HashMap<>();

        if (title == null || title.length() == 0) {
            map.put("error_message", "Title cannot be empty");
            return map;
        }

        if (title.length() > 100) {
            map.put("error_message", "Title length cannot exceed 100 characters");
            return map;
        }

        if (description == null || description.length() == 0) {
            description = "This user is lazy and hasn't left anything~";
        }

        if (description.length() > 300) {
            map.put("error_message", "Bot description length cannot exceed 300 characters");
            return map;
        }

        if (content == null || content.length() == 0) {
            map.put("error_message", "Code cannot be empty");
            return map;
        }

        if (content.length() > 10000) {
            map.put("error_message", "Code length cannot exceed 10000 characters");
            return map;
        }

        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        if (botMapper.selectCount(queryWrapper) >= 10) {
            map.put("error_message", "Each user can create a maximum of 10 Bots!");
            return map;
        }


        Date now = new Date();
        Bot bot = new Bot(null, user.getId(), title, description, content, now, now);


        botMapper.insert(bot);
        map.put("error_message", "success");

        return map;
    }
}
