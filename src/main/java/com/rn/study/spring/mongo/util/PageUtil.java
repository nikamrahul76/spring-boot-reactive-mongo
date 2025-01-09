package com.rn.study.spring.mongo.util;

import com.rn.study.spring.mongo.dto.PageDto;
import org.springframework.data.domain.Page;

public class PageUtil {
    private PageUtil() {

    }

    public static  <T> PageDto<T> map(Page<T> page) {
        PageDto<T> pageDto = new PageDto<>();
        pageDto.setContent(page.getContent());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setNumber(page.getNumber());
        pageDto.setSize(page.getSize());
        pageDto.setTotalElements(page.getTotalElements());
        return pageDto;
    }

}
