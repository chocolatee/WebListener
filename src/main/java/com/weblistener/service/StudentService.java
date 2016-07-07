package com.weblistener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by windylee on 6/23/2016.
 */
@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllTest(int stu_id) {
        String sql = "SELECT q.test_topic, q.test_id, s.is_complete FROM test q JOIN stu_test s ON s.test_id = q.test_id WHERE s.stu_id = ?";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, stu_id);
        List<Map<String, Object>> resultAll = jdbcTemplate.queryForList("SELECT q.test_topic, q.test_id FROM test q " +
                "WHERE q.test_id NOT IN (SELECT test_id FROM stu_test where stu_id = ?)", stu_id);
        for (Map<String, Object> item : resultAll) {
            result.add(item);
        }
        return result;
    }

    public List<Map<String, Object>> getQueByTestId(int testId) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM question WHERE test_id = ?", testId);
        return result;
    }

    public String checkQueAns(int stuId, int queId, String ans) {
        //检查用户是否已经做过该题目
        int count = jdbcTemplate.queryForInt("SELECT count(*) FROM stu_que WHERE stu_id = ? AND que_id = ?", stuId, queId);
        if (count != 0) {
            return null;
        }
        List<Map<String, Object>> questions = jdbcTemplate.queryForList("SELECT ans_right,right_count,wrong_count,stu_a,stu_b,stu_c,stu_d " +
                "FROM question WHERE  que_id = ?", queId);
        Map<String, Object> question = questions.get(0);
        Map<String, Object> stuQue = new HashMap<String, Object>();
        if (question.get("ans_right").equals(ans)) {
            jdbcTemplate.update("INSERT INTO stu_que (stu_id, que_id, right_ans, answer, is_right) VALUES (?,?,?,?,?)", stuId, queId,
                    question.get("ans_right"), ans, 1);
            question.put("right_count", (Long) question.get("right_count") + 1);
            updateStuAns(ans, question);
            jdbcTemplate.update("UPDATE question SET right_count = ?, ans_a = ?, ans_b=?,ans_c=?,ans_d=? WHERE que_id = ?",
                    question.get("right_count"), question.get("stu_a"), question.get("stu_b"), question.get("stu_c"), question.get("stu_d"),
                    queId);
            return null;
        }
        jdbcTemplate.update("INSERT INTO stu_que (stu_id, que_id, right_ans, answer, is_right) VALUES (?,?,?,?,?)", stuId, queId,
                question.get("ans_right"), ans, 0);
        question.put("wrong_count", (Long) question.get("wrong_count") + 1);
        updateStuAns(ans, question);
        System.out.println(question.toString());
        jdbcTemplate.update("UPDATE question SET wrong_count = ?, ans_a = ?, ans_b=?,ans_c=?,ans_d=? WHERE que_id = ?",
                question.get("wrong_count"), question.get("stu_a"), question.get("stu_b"), question.get("stu_c"), question.get("stu_d"),
                queId);
        return (String) question.get("ans_right");
    }

    private void updateStuAns(String ans, Map<String, Object> res) {
        if (ans.equals("A"))
            res.put("stu_a", (Integer) res.get("stu_a") + 1);
        else if (ans.equals("B"))
            res.put("stu_b", (Integer) res.get("stu_b") + 1);
        else if (ans.equals("C"))
            res.put("stu_c", (Integer) res.get("stu_c") + 1);
        else
            res.put("stu_d", (Integer) res.get("stu_d") + 1);
    }

    public boolean completeTest(int stuId, int testId) {
        int count = jdbcTemplate.queryForInt("SELECT count(*) FROM  stu_test WHERE stu_id = ? AND test_id = ?", stuId, testId);
        if (count == 0) {
            jdbcTemplate.update("INSERT INTO stu_test (stu_id, test_id, is_complete) VALUES (?,?,?)", stuId, testId, 1);
        }
        return true;
    }

}
