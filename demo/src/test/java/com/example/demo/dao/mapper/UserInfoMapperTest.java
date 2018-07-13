package com.example.demo.dao.mapper;

import com.example.demo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {
    @Resource
    private UserInfoMapper userMapper;

    public UserInfoMapperTest(UserInfoMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    public void test() {
        UserInfo record = new UserInfo();
        record.setUserName("test");
        record.setPassword("123");
        userMapper.insert(record);
        List<UserInfo> u = userMapper.selectByPrimaryKey(1L);

    }
}