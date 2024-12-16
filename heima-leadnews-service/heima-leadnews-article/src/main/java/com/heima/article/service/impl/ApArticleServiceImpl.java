package com.heima.article.service.impl;/**
 * @desription
 */


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleConfigMapper;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleConfig;
import com.heima.model.article.pojos.ApArticleContent;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
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
    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;
    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

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

    /**
     * @Desc:文章保存接口
     **/
    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        //1.检查参数
        if(dto==null || dto.getContent()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApArticle apArticle = new ApArticle();
        BeanUtils.copyProperties(dto,apArticle);
        //2.判断是否存在id
        if(dto.getId()==null){
            //保存文章
            save(apArticle);
            //保存配置
            ApArticleConfig apArticleConfig = new ApArticleConfig(apArticle.getId());
            apArticleConfigMapper.insert(apArticleConfig);
            //保存文章内容
            ApArticleContent apArticleContent = new ApArticleContent();
            apArticleContent.setArticleId(apArticle.getId());
            apArticleContent.setContent(dto.getContent());
            apArticleContentMapper.insert(apArticleContent);
        }else{
            //修改  文章
            updateById(apArticle);
            //修改文章内容
            ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, dto.getId()));
            apArticleContentMapper.updateById(apArticleContent);
        }
        return ResponseResult.okResult(apArticle.getId());
    }
}
