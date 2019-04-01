package com.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Demo;

/**
 * Created by sunxusen on 2018/7/23.
 */
@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {


}
