package com.money.website.controller;

import com.money.common.util.Base64Helper;
import com.money.common.util.JsonHelper;
import com.money.common.util.NamingUtil;
import com.money.service.RCodeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * [Description]
 * date: 2016/7/31
 *
 * @author maofagui
 * @version 1.0
 */

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/get_rcode.htm")
    public String getRcode(@RequestParam(value = "phone", defaultValue = "1") String phone) {
        System.out.println("getRcode:phone" + phone);
        String res = "";
        Map map = new HashMap<String, String>();
        String fileName = NamingUtil.getDateLongName() + ".jpg";
        RCodeServiceImpl.encode(phone, fileName);
        String data = Base64Helper.getImageStr(fileName);
        System.out.println(data);
        map.put("data", data);
        map.put("code", 100);
        try {
            res = JsonHelper.write(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

//    // post请求，处理添加用户请求，并重定向到用户管理页面
//    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
//    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
//        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
//        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象
//
//        // 数据库中添加一个用户，该步暂时不会刷新缓存
//        //userRepository.save(userEntity);
//        System.out.println(userEntity.getFirstName());
//        System.out.println(userEntity.getLastName());
//
//        // 数据库中添加一个用户，并立即刷新缓存
//        userRepository.saveAndFlush(userEntity);
//
//        // 重定向到用户管理页面，方法为 redirect:url
//        return "redirect:/admin/users";
//    }
}
