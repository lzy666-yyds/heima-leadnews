package com.heima.article.service.impl;

import com.heima.article.service.HotArticleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

/**
 * @Description:
 */
@Component
@Slf4j
public class HotArticleServiceImplTest {
    @Autowired
    private HotArticleService hotArticleService;

    @Test
    public void computeHotArticle() {
    }
}