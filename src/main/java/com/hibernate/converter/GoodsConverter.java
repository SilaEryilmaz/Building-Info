package com.hibernate.converter;


import com.hibernate.dto.GoodsDto;
import com.hibernate.entity.GoodsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses= {BuildingConverter.class, PersonConverter.class})
public interface GoodsConverter {

    @Mapping(source="buildingEntity", target="buildingEntity")
    @Mapping(source="personEntity", target="personEntity")
    public GoodsEntity GoodsDtoToGoodsEntity(GoodsDto goodsDto);

    public GoodsDto GoodsEntityToGoodsDto(GoodsEntity personEntity);

    public List<GoodsEntity> goodsDtoListToGoodsEntityList(List<GoodsDto> goodsDtoList);

    public List<GoodsDto> goodsEntityListToGoodsDtoList(List<GoodsEntity> goodsEntityList);
}
