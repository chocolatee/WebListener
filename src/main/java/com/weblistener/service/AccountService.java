package com.weblistener.service;

import com.weblistener.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by windylee on 6/22/2016.
 */
@Service
public class AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long checkTeacher(String username, String password) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select teacher_id, password from teacher where username = ?", username);
        if (result.size() ==0) {
            return -1;
        }else if(!result.get(0).get("password").equals(password)){
            return -1;
        }
        return (Long) result.get(0).get("teacher_id");
    }

    public boolean regTeacher(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) {
            return false;
        }
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select teacher_id from teacher where username = ?", username);
        if (result.size() != 0) {
            return false;
        }
        jdbcTemplate.update("insert into teacher (username, password) VALUES (?, ?)", username, password);
        return true;
    }

    public long checkStudent(String stuNum, String password) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from student where stu_num = ?", stuNum);
        if (result.size() == 0 || !result.get(0).get("password").equals(password)) {
            return -1;
        }
        return (Long) result.get(0).get("stu_id");
    }

    public boolean checkStuNum(String stuNum) {
        int count = jdbcTemplate.queryForInt("SELECT count(*) FROM student WHERE stu_num = ?", stuNum);
        return count == 0 ? true : false;
    }

    public void insertStudent(Student student) {
        jdbcTemplate.update("INSERT INTO student (username, password, grade, class, real_name, stu_num) VALUES (?,?,?,?,?,?)",
                student.getUsername(), student.getPassword(), student.getGrade(), student.getmClass(), student.getRealName(),
                student.getStuNum());
    }

}
