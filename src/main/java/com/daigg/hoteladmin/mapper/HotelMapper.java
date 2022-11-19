package com.daigg.hoteladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daigg.hoteladmin.pojo.entity.Hotel;
import com.daigg.hoteladmin.pojo.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 酒店Mapper
 * @author daiyang
 */
@Mapper
@Repository
public interface HotelMapper extends BaseMapper<Hotel> {

    /**
     * 分页查询酒店
     * @param page
     * @param vo
     * @return
     */
    public Page<HotelVO> getPageWithDistance(@Param("page") Page<HotelVO> page, HotelVO vo);

}
