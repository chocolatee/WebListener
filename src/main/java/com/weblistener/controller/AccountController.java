package com.weblistener.controller;

import com.weblistener.entity.Student;
import com.weblistener.service.AccountService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by windylee on 2016/5/19.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/login")
    public String getLoginView() {
        return "teacher/login";
    }

    @RequestMapping("/reg")
    public String getRegView() {
        return "teacher/register";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("teacher_id");
        return "teacher/login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam String username, @RequestParam String password,
                          HttpSession session, Map<String, Object> handle) {
        long teacher_id = accountService.checkTeacher(username, password);
        if (teacher_id == -1) {
            handle.put("error", true);
            return "teacher/login";
        }
        session.setAttribute("teacher_id", teacher_id);
        return "redirect:/teacher/home/0";
    }

    @RequestMapping("/doReg")
    public String doReg(@RequestParam String username, @RequestParam String password,
                        @RequestParam("userpwdok") String rePassword) {
        if (accountService.regTeacher(username, password, rePassword)) {
            return "teacher/login";
        }
        return "teacher/register";
    }

    @RequestMapping("/appLogin")
    @ResponseBody
    public Map<String, Object> appDoLogin(@RequestParam("stu_num") String stuNum,
                                          @RequestParam("password") String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        long stu_id = accountService.checkStudent(stuNum, password);
        if (stu_id == -1) {
            result.put("message", false);
        } else {
            result.put("message", true);
            result.put("stuId", stu_id);
        }
        return result;
    }
    @RequestMapping("/appReg")
    @ResponseBody
    public Map<String, Object> appDoReg(Student student) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (!accountService.checkStuNum(student.getStuNum())) {
            result.put("message", "double");
            return result;
        }
        accountService.insertStudent(student);
        result.put("message", "success");
        return result;
    }

}
