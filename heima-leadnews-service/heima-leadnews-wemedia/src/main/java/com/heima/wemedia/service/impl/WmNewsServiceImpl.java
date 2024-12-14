package com.heima.wemedia.service.impl;/**
 * @desription
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.WemediaConstants;
import com.heima.common.exception.CustomException;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.model.wemedia.pojos.WmNewsMaterial;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.common.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.service.WmNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-14  16:20
 */
@Service
@Slf4j
@Transactional
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    @Autowired
    private WmNewsMaterialMapper wmNewsMaterialMapper;
    @Autowired
    private WmMaterialMapper wmMaterialMapper;

    /**
     * @Desc:查询文章
     **/
    @Override
    public ResponseResult findAll(WmNewsPageReqDto dto) {
        //1.检查参数
        if(dto==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //分页参数检查
        dto.checkParam();
        //获取当前登录人的信息
        WmUser user = WmThreadLocalUtil.getUser();
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        //2.分页条件查询
        Page page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper< WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if( dto.getStatus()!=null){
            lambdaQueryWrapper.eq(WmNews::getStatus,dto.getStatus());
        }
        if(dto.getChannelId()!=null){
            lambdaQueryWrapper.eq(WmNews::getChannelId,dto.getChannelId());
        }
        if(dto.getBeginPubDate()!=null&&dto.getEndPubDate()!=null){
            lambdaQueryWrapper.between(WmNews::getPublishTime,dto.getBeginPubDate(),dto.getEndPubDate());
        }
        //关键字模糊查询
        if(dto.getKeyword()!=null&& !dto.getKeyword().isEmpty()){
            lambdaQueryWrapper.like(WmNews::getTitle,dto.getKeyword());
        }
        lambdaQueryWrapper.eq(WmNews::getUserId,user.getId());
        lambdaQueryWrapper.orderByDesc(WmNews::getCreatedTime);
        page = page(page, lambdaQueryWrapper);
        //3.返回结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    /**
     * @Desc: 发布修改文章或保存为草稿
     **/
    @Override
    public ResponseResult submitNews(WmNewsDto dto) {
        //0.条件判断
        if(dto==null||dto.getContent()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //1.保存或修改文章
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(dto,wmNews);
        //封装图片，List转为String
        if(dto.getImages()!=null&& !dto.getImages().isEmpty()){
            String imageStr = StringUtils.join(dto.getImages(), ",");
            wmNews.setImages(imageStr);
        }
        //如果当前封面类型为自动 -1
        if(dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)){
            wmNews.setType(null);
        }
        saveOrUpdateWmNews(wmNews);

        //2.判断是否为草稿  如果为草稿结束当前方法
        if(dto.getStatus().equals(WmNews.Status.NORMAL.getCode())){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }

        //3.不是草稿，保存文章内容图片与素材的关系
        //获取到文章内容中的图片信息
        List<String> materials =  ectractUrlInfo(dto.getContent());
        saveRelativeInfoForContent(materials,wmNews.getId());

        //4.不是草稿，保存文章封面图片与素材的关系，如果当前布局是自动，需要匹配封面图片
        saveRelativeInfoForCover(dto,wmNews,materials);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * @Desc: 保存文章封面图片与素材的关系，如果当前布局是自动，需要匹配封面图片
     **/
    private void saveRelativeInfoForCover(WmNewsDto dto, WmNews wmNews, List<String> materials) {
        List<String> images = dto.getImages();

        //如果当前封面类型为自动，则设置封面类型的数据
        if(dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)){
            //多图
            if(materials.size() >=3){
                wmNews.setType(WemediaConstants.WM_NEWS_MANY_IMAGE);
                images = materials.stream().limit(3).collect(Collectors.toList());
            }else if(materials.size() >= 1&& materials.size() < 3){
                //单图
                wmNews.setType(WemediaConstants.WM_NEWS_SINGLE_IMAGE);
                images = materials.stream().limit(1).collect(Collectors.toList());
            }else{
                //无图
                wmNews.setType(WemediaConstants.WM_NEWS_NONE_IMAGE);
            }
            //修改文章
            if(images!=null&& !images.isEmpty()){
                wmNews.setImages(String.join(",",images));
            }
            updateById(wmNews);
        }
        if(images!=null && !images.isEmpty()){
            saveRelativeInfo(images,wmNews.getId(),WemediaConstants.WM_COVER_REFERENCE);
        }

    }

    /**
     * @Desc: 保存文章内容图片与素材的关系
     **/
    private void saveRelativeInfoForContent(List<String> materials, Integer newsId) {
        saveRelativeInfo(materials,newsId,WemediaConstants.WM_CONTENT_REFERENCE);
    }

    /**
     * @Desc: 保存图片与素材的关系
     **/
    private void saveRelativeInfo(List<String> materials, Integer newsId, Short type) {
        if(materials!=null&& !materials.isEmpty()){
            //通过图片的url查询素材的id
            List<WmMaterial> dbMaterials = wmMaterialMapper.selectList(Wrappers.<WmMaterial>lambdaQuery().in(WmMaterial::getUrl, materials));

            //判断素材是否有效
            if(dbMaterials==null|| dbMaterials.isEmpty()){
                throw new CustomException(AppHttpCodeEnum.MATERIASL_REFERENCE_FAIL);
            }

            List<Integer> idList = dbMaterials.stream().map(WmMaterial::getId).collect(Collectors.toList());

            wmNewsMaterialMapper.saveRelations(idList,newsId,type);
        }
    }

    /**
     * @Desc: 提取文章内容中的图片信息
     **/
    private List<String> ectractUrlInfo(String content) {
        List<String> materials = new ArrayList<>();
        List<Map> maps = JSON.parseArray(content, Map.class);
        for (Map map : maps) {
            if(map.get("type").equals("image")){
                materials.add(map.get("value").toString());
            }
        }
        return materials;
    }

    /**
     * @Desc: 保存或修改文章
     **/
    private void saveOrUpdateWmNews(WmNews wmNews) {
        //补全属性
        wmNews.setUserId(WmThreadLocalUtil.getUser().getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        wmNews.setEnable((short)1);

        if(wmNews.getId()==null){
            //保存
            save(wmNews);
        }else{
            //修改
            //删除文章图片与素材的关系
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId,wmNews.getId()));
            updateById(wmNews);
        }
    }
}
