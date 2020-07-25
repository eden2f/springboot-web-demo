package com.eden.springbootwebdemo.controller;

import com.eden.springbootwebdemo.dto.GenIdReqDTO;
import com.eden.springbootwebdemo.web.RetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

/**
 * This is Description
 *
 * @author Eden
 * @date 2020/07/19
 */
@RestController
@Slf4j
public class HelloWorldController {

    private final Random random = new Random();

    @PostMapping("genId")
    public RetResult<Integer> genId(@RequestBody @Valid GenIdReqDTO genIdReqDTO) {
        return RetResult.success(random.nextInt(Integer.MAX_VALUE));
    }

}
