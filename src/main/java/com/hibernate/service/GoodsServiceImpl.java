package com.hibernate.service;


import com.hibernate.converter.GoodsConverter;
import com.hibernate.dto.GoodsDto;
import com.hibernate.entity.GoodsEntity;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.repository.GoodsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsConverter goodsConverter;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames = "goods")
    public GoodsDto saveGoods(@Valid GoodsDto goods) throws ObjNotFoundException {
        try {
            GoodsDto savedGoods = goodsConverter.GoodsEntityToGoodsDto(goodsRepository.save(goodsConverter.GoodsDtoToGoodsEntity(goods)));
            LOGGER.info("The goods saved");
            return savedGoods;

        } catch (Exception e) {
            LOGGER.error("Failed to create record !", e);
            throw new ObjNotFoundException("Failed to create record !");
        }
    }

    @Override
    @Cacheable(cacheNames = "goods", key = "#id")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    public GoodsDto getGoodsById(UUID id) throws ObjNotFoundException {

        Optional<GoodsEntity> optionalGoodsEntity = goodsRepository.findById(id);

        try {
            GoodsDto getGoods = goodsConverter.GoodsEntityToGoodsDto(optionalGoodsEntity.get());

            Thread.sleep(1000 * 5);
            LOGGER.info("Fetching goods from database");
            return getGoods;

        } catch (Exception e) {
            LOGGER.error("Could not find goods with this ID !");
            throw new ObjNotFoundException("There is no any data with this given ID !");

        }
    }

    @Override
    public List<GoodsDto> getAllGoods() {
        return goodsConverter.goodsEntityListToGoodsDtoList((List<GoodsEntity>) goodsRepository.findAll());
    }

    @Override
    @CacheEvict(cacheNames = "adress", key = "#id")
    public void deleteGoods(UUID id) {
        goodsRepository.deleteById(id);
        LOGGER.info("Goods is deleted successfully.");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames = "goods", key = "#goodsDto.id")
    public GoodsDto updateGoods(GoodsDto goodsDto) throws ObjNotFoundException {
        try {
            GoodsDto updatedGoods = goodsConverter.GoodsEntityToGoodsDto(goodsRepository.save(goodsConverter.GoodsDtoToGoodsEntity(goodsDto)));
            LOGGER.info("Goods is updated successfully!");
            return updatedGoods;

        } catch (Exception e) {
            LOGGER.error("Failed to update record !", e);
            throw new ObjNotFoundException("Failed to update record !");
        }
    }
}

