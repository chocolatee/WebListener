package com.weblistener.service;

import com.weblistener.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by windylee on 6/23/2016.
 */
@Service
public class TeacherService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UploadFileService uploadFileService;

    public List<Map<String, Object>> getTestForPage(int num) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM test LIMIT ?, 5", num);
        return result;
    }

    public int getTestCount() {
        return jdbcTemplate.queryForInt("select count(*) from test");
    }

    public boolean insertTest(String title) {
        if (StringUtils.isEmpty(title)) {
            return false;
        }
        jdbcTemplate.update("insert into test (test_topic, pub_time, teacher_name) VALUES (?, ?, ?)",
                title, new Date(), "root");
        return true;
    }

    public boolean insertQue(Question question, CommonsMultipartFile multipartFile,String path) {
        if (StringUtils.isEmpty(question.getQueTopic()) || StringUtils.isEmpty(question.getAnsA()) ||
                StringUtils.isEmpty(question.getAnsRight())) {
            return false;
        }
        String ansRight = question.getAnsRight();
        if (!(ansRight.equals("A") || ansRight.equals("B") || ansRight.equals("C") || ansRight.equals("D"))) {
            return false;
        }
        String recordName;
        try {
            recordName = uploadFileService.uploadFile(multipartFile, path+"/records/");
        } catch (IOException e) {
            return false;
        }
        jdbcTemplate.update("insert into question (que_topic, ans_a, ans_b, ans_c, ans_d, ans_right, path, test_id) VALUES (?,?,?,?,?,?,?,?)",
                question.getQueTopic(), question.getAnsA(), question.getAnsB(), question.getAnsC(), question.getAnsD(), question.getAnsRight(),
                recordName, question.getTestId());
        return true;
    }

    public List<Map<String, Object>> getQueByTest(int testId) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM question WHERE test_id = ?", testId);
        return result;
    }

}
