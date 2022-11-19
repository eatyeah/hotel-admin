package com.daigg.hoteladmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daigg.hoteladmin.pojo.entity.Hotel;
import com.daigg.hoteladmin.pojo.vo.HotelVO;

import java.util.List;

/**
 * 酒店Service
 * @author daiyang
 */
public interface IHotelService {
    Hotel selectOneById(String id);
    HotelVO add(HotelVO hotelVO);
    HotelVO selectOneByIdReturnVO(String id);

    Page<HotelVO> getPage(HotelVO hotelVO);

    List<Hotel> list();
}
