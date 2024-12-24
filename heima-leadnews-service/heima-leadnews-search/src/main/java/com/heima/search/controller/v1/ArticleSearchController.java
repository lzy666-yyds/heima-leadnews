package com.heima.search.controller.v1;/**
 * @Description:
 */

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.UserSearchDto;
import com.heima.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *@Desc:app端文章搜索
 *@Author: lzy
 *@CreateTime: 2024-12-24  18:18
 */
@RestController
@RequestMapping("/api/v1/article/search")
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;
    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto dto) throws IOException {
        return articleSearchService.search(dto);
    }
}
