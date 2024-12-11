package com.heima.article.service.impl;/**
 * @desription
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-11  16:22
 */
@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle>implements ApArticleService {
    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult load(Short loadtype, ArticleHomeDto dto) {
        //1.校验参数
        Integer size = dto.getSize();
        if( size == null || size ==0){
            size = 10;
        }
        size = Math.min(size, ArticleConstants.MAX_PAGE_SIZE);
        dto.setSize(size);
        //类型参数检验
        if( !ArticleConstants.LOADTYPE_LOAD_NEW.equals(loadtype) && !ArticleConstants.LOADTYPE_LOAD_MORE.equals(loadtype)){
            loadtype = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        if(dto.getTag() == null){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        if(dto.getMaxBehotTime() == null){
            dto.setMaxBehotTime(new Date());
        }
        if(dto.getMinBehotTime() == null){
            dto.setMinBehotTime(new Date());
        }
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadtype);
        ResponseResult responseResult = ResponseResult.okResult(apArticles);
        return responseResult;
    }
}
