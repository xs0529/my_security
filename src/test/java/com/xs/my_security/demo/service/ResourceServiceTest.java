package com.xs.my_security.demo.service;

import com.xs.my_security.demo.DemoApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-03
 */
public class ResourceServiceTest extends DemoApplicationTests {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void getResourceByUserIdTest(){
        System.out.println(resourceService.getResourceByUserId(1));
    }

}