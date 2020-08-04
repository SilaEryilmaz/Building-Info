package com.hibernate.service;


import com.hibernate.dto.GoodsDto;
import com.hibernate.exception.ObjNotFoundException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

public interface GoodsService {
    GoodsDto saveGoods(@Valid GoodsDto goods) throws ObjNotFoundException;
    GoodsDto getGoodsById(UUID id) throws ObjNotFoundException;
    List<GoodsDto> getAllGoods();
    void deleteGoods( UUID id);
    GoodsDto updateGoods(GoodsDto goodsDto) throws ObjNotFoundException;
}
