package com.weblistener.controller;

import com.weblistener.entity.Question;
import com.weblistener.service.TeacherService;
import org.springframework.asm.Handle;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by windylee on 2016/5/19.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/home/{num}")
    public String getHomeView(@PathVariable("num") int num, Map<String, Object> handle) {
        List<Map<String, Object>> result = teacherService.getTestForPage(num);
        int count = teacherService.getTestCount();
        handle.put("result", result);
        handle.put("count", count);
        handle.put("index", num);
        return "teacher/home";
    }

    @RequestMapping("/pubTest")
    public String publishTestView() {
        return "teacher/pubTest";
    }

    @RequestMapping("/pubQue/{num}")
    public String publishQueView(@PathVariable("num") int num, Map<String, Object> handle) {
        handle.put("testId", num);
        return "teacher/pubQue";
    }

    @RequestMapping("/doPubTest")
    public String doPubTest(@RequestParam String title) {
        if (teacherService.insertTest(title)) {
            return "redirect:/teacher/home/0";
        }
        return "teacher/pubTest";
    }

    @RequestMapping("/doPubQue")
    public String doPubQue(Question question,
                           @RequestParam("path") CommonsMultipartFile multipartFile,
                           HttpServletRequest request,
                           Map<String, Object> handle) {
//        System.out.println(question.toString());
//        System.out.println(multipartFile.getName());
        handle.put("testId", question.getTestId());
        String path = request.getServletContext().getRealPath("/");
        if (teacherService.insertQue(question, multipartFile, path)) {
            return "teacher/pubSuccess";
        }
        return "teacher/pubError";
    }

    @RequestMapping("/detailTest/{num}")
    public String listQue(@PathVariable int num, Map<String, Object> handle ) {
        List<Map<String, Object>> result = teacherService.getQueByTest(num);
        handle.put("result", result);
        return "teacher/listQue";
    }

}
