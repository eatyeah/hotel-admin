package com.daigg.hoteladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daigg.hoteladmin.ex.CommonException;
import com.daigg.hoteladmin.ex.SavaException;
import com.daigg.hoteladmin.mapper.HotelMapper;
import com.daigg.hoteladmin.pojo.entity.Hotel;
import com.daigg.hoteladmin.pojo.vo.HotelVO;
import com.daigg.hoteladmin.service.IHotelService;
import com.daigg.hoteladmin.tools.ConvertUtils;
import com.daigg.hoteladmin.tools.WStringUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Math.PI;


/**
 * @author 文
 */

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class HotelServiceImpl implements IHotelService {
    @Autowired
    HotelMapper hotelMapper;

    @Override
    public HotelVO add(HotelVO hotelVO){
        if(hotelVO==null){
            throw new SavaException("插入用户失败:酒店实体为空");
        }
        if(!"".equals(hotelVO.getDeptId())&&hotelVO.getDeptId()!=null){
            QueryWrapper<Hotel> wrapper = new QueryWrapper<>();
            wrapper.eq("dept_id",hotelVO.getDeptId());
            List<Hotel> hotelPOS = hotelMapper.selectList(wrapper);

            if(!hotelPOS.isEmpty()){
                throw new SavaException("该部门已被绑定,绑定的酒店为: "+hotelPOS.get(0).getName());
            }
        }

        Hotel hotelP0=new Hotel();
        BeanUtils.copyProperties(hotelVO,hotelP0);
        int insert=hotelMapper.insert(hotelP0);

        Gson gson = new Gson();
//        hotelVO.setOtherPolicy(WStringUtils.removeHtml(hotelVO.getOtherPolicy()));
        log.debug("【添加】酒店信息",gson.toJson(hotelVO),1,1);

        if(insert>0){
            return hotelVO;
        }
        throw new SavaException("插入用户失败");
    }


    @Override
    public Hotel selectOneById(String id){
        Hotel Hotel =hotelMapper.selectById(id);
        return Hotel;
    }

    @Override
    public HotelVO selectOneByIdReturnVO(String id){
        if(id==null){
            throw new CommonException(501,"参数为空");
        }
        Hotel hotel=hotelMapper.selectById(id);
        HotelVO hotelVO=new HotelVO();
        if(hotel!=null){
            BeanUtils.copyProperties(hotel,hotelVO);
        }
        return hotelVO;
    }

    @Override
    public Page<HotelVO> getPage(HotelVO hotelVO) {
        Page<HotelVO> res = null;
        if (WStringUtils.isBlank(hotelVO.getLatitude()) && WStringUtils.isBlank(hotelVO.getLongitude())) {
            // 构建查询条件
            QueryWrapper<Hotel> wrapper = new QueryWrapper<>();
            if (!"".equals(hotelVO.getName()) && hotelVO.getName() != null) {
                wrapper.like("name",hotelVO.getName());
            }
            if (!"".equals(hotelVO.getAddress()) && hotelVO.getAddress() != null) {
                wrapper.like("address",hotelVO.getAddress());
            }
            if (hotelVO.getAllowIsolation() != null) {
                wrapper.eq("allow_isolation",hotelVO.getAllowIsolation());
            }
            if (!WStringUtils.isBlank(hotelVO.getProvince())) {
                wrapper.eq("province",hotelVO.getProvince());
            }
            if (hotelVO.getCollection() != null) {
                wrapper.in("id",hotelVO.getCollection());
            }
            wrapper.orderByAsc("sort").orderByAsc("id");
            Page<Hotel> page = new Page<>(hotelVO.getPage().getPage(), hotelVO.getPage().getSize());
            Page<Hotel> poiPage = (Page<Hotel>) hotelMapper.selectPage(page, wrapper);
            res = ConvertUtils.transferPage(poiPage,HotelVO.class);
        }else {
            hotelVO = countCoordinateRange(hotelVO);
            Page<HotelVO> page = new Page<>(hotelVO.getPage().getPage(), hotelVO.getPage().getSize());
            res = hotelMapper.getPageWithDistance(page, hotelVO);
        }
        return res;
    }

    /**
     * 查询酒店列表
     *
     * @return
     */
    @Override
    public List<Hotel> list(){
        log.debug("开始处理【查询】酒店列表业务");
        Hotel hotel = new Hotel();
        List<Hotel> list = hotelMapper.selectList(new QueryWrapper<>(hotel));
        log.debug("处理【查询】酒店列表业务结束");
        return list;
    }

    private HotelVO countCoordinateRange(HotelVO hotelVO) {
        Double longitude = Double.valueOf(hotelVO.getLongitude());
        Double latitude = Double.valueOf(hotelVO.getLatitude());
        Double radius = Double.valueOf(hotelVO.getDistance());
        if (longitude >= 180
                || longitude <= -180
                || latitude >= 90
                || latitude <= -90) {
            throw new CommonException("【HotelServiceImpl.countCoordinateRange】非法坐标值--longitude:" + longitude + ",latitude:" + latitude);
        }
        Double degree = (24901 * 1609) / 360.0;

        Double radiusLng = (1 / (degree * Math.cos(latitude * (PI / 180)))) * radius;
        hotelVO.setMinLongitude(String.valueOf(longitude-radiusLng));
        hotelVO.setMaxLongitude(String.valueOf(longitude+radiusLng));

        Double radiusLat = (1/degree)*radius;
        hotelVO.setMinLatitude(String.valueOf(latitude-radiusLat));
        hotelVO.setMaxLatitude(String.valueOf(latitude+radiusLat));

        return hotelVO;
    }

}
