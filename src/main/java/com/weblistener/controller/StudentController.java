package com.weblistener.controller;

import com.weblistener.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by windylee on 2016/5/19.
 */
@ControllerAdvice
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("allTest")
    @ResponseBody
    public Map<String, Object> getAllTest(@RequestParam("stu_id") int stuId) {
        List<Map<String, Object>> tests = studentService.getAllTest(stuId);
        Map<String, Object> result = new HashMap<String, Object>();
        if (tests.size() == 0) {
            result.put("message", false);
            return result;
        }
        result.put("message", true);
        result.put("result", tests);
        return result;
    }

    @RequestMapping("queById")
    @ResponseBody
    public Map<String, Object> getQueByTestId(@RequestParam("test_id") int testId) {
        List<Map<String, Object>> questions = studentService.getQueByTestId(testId);
        Map<String, Object> result = new HashMap<String, Object>();
        if (questions.size() == 0) {
            result.put("message", false);
            return result;
        }
        result.put("message", true);
        result.put("result", questions);
        return result;
    }

    @RequestMapping("ans")
    @ResponseBody
    public Map<String, Object> checkQueAns(@RequestParam("que_id") int queId,
                                           @RequestParam("ans") String ans, @RequestParam("stu_id") int stuId) {
        String ansRight = studentService.checkQueAns(stuId, queId, ans);
        Map<String, Object> result = new HashMap<String, Object>();
        if (ansRight != null) {
            result.put("message", true);
            result.put("ans_right", ansRight);
            return result;
        }
        result.put("message", false);
        result.put("ans_right", "unknown");
        return result;
    }

    @RequestMapping("completeTest")
    @ResponseBody
    public Map<String, Object> completeTest(@RequestParam("stu_id") int stuId, @RequestParam("test_id") int testId) {
        studentService.completeTest(stuId, testId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", true);
        return result;
    }

}
