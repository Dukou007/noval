package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Demo;
import com.respository.DemoRepository;
import com.util.StringUtils;

import javax.transaction.Transactional;

/**
 * Created by sunxusen on 2018/7/23.
 */
@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Transactional
    public Demo getOne(Integer id){
        return demoRepository.getOne(id);
    }

    @Transactional
    public void update(Demo demo){
        Demo one = demoRepository.getOne(demo.getId());
        if (StringUtils.isNotEmpty(demo.getName()))
            one.setName(demo.getName());
        if (demo.getAge() != 0){
            one.setAge(demo.getAge());
        }
        if (demo.getTime() != null){
            one.setTime(demo.getTime());
        }

        demoRepository.save(one);
    }

}
