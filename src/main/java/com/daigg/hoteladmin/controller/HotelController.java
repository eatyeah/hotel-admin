package com.daigg.hoteladmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daigg.hoteladmin.pojo.entity.Hotel;
import com.daigg.hoteladmin.pojo.vo.HotelVO;
import com.daigg.hoteladmin.service.IHotelService;
import com.daigg.hoteladmin.tools.Reply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 酒店Controller
 * @author daiyang
 */
@Api(tags="酒店相关接口")
@RestController
@RequestMapping("api/hotel")
public class HotelController {
    @Autowired
    IHotelService hotelService;

    @PostMapping("add")
    @ApiOperation("添加酒店")
    public Reply<HotelVO> add(@RequestBody HotelVO hotelVO){

        return Reply.success(hotelService.add(hotelVO));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据id获取酒店")
    public Reply<HotelVO> getById(@PathVariable("id") String id) {
        return Reply.success(hotelService.selectOneByIdReturnVO(id));
    }

    @ApiOperation("查询酒店列表(分页)-暂未实现")
    @PostMapping("page")
    public Reply<Page<HotelVO>> getPage(@RequestBody HotelVO hotelVO) {
        return Reply.success(hotelService.getPage(hotelVO));
    }

    @ApiOperation("查询酒店列表")
    @GetMapping("list")
    public Reply<List<Hotel>> list() {
        return Reply.success(hotelService.list());
    }


}
