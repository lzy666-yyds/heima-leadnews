package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @desription
 */
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    //查询内容
    public List<ApArticle> loadArticleList(@Param("dto")ArticleHomeDto dto,@Param("type") Short type);
}
