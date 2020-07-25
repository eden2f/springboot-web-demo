package com.eden.springbootwebdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * This is Description
 *
 * @author Eden
 * @date 2020/07/19
 */
@Data
public class GenIdReqDTO {

    @NotBlank
    private String appId;

}
