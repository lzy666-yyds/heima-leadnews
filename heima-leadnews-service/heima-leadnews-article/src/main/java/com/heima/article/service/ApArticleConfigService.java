package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.pojos.ApArticleConfig;

import java.util.Map;

/**
 * @Description:
 */
public interface ApArticleConfigService extends IService<ApArticleConfig> {
    /**
     * @Desc:修改文章配置方法
     **/
    void updateByMap(Map map);
}
