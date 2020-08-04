package com.hibernate.controller;

import com.hibernate.dto.BuildingDto;
import com.hibernate.dto.GoodsDto;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/goods")
public class GoodsControllerImpl implements Goods {

    public static final Logger LOGGER = LoggerFactory.getLogger(GoodsControllerImpl.class);


    @Autowired
    GoodsService goodsService;


    @Override
    public GoodsDto getGoods(@PathVariable UUID id) throws ObjNotFoundException {
        LOGGER.debug("The goods with id number" + id + " has been questioned.");

        return goodsService.getGoodsById(id);
    }

    @Override
    public List<GoodsDto> getAllGoods() {
        LOGGER.debug("All goods queried." );


        return goodsService.getAllGoods();
    }

    @Override
    public ResponseEntity<GoodsDto> saveGoods(@RequestBody GoodsDto goodsDto) throws ObjNotFoundException {
        GoodsDto savedNewDto = goodsService.saveGoods(goodsDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNewDto.getId())
                .toUri();

        LOGGER.debug("New goods has been added to the system." );

        return ResponseEntity.created(uri)
                .body(savedNewDto);
    }

    public ResponseEntity<GoodsDto> updateGoods(@Valid GoodsDto goodsDto) throws ObjNotFoundException {

        GoodsDto savedGoodsDto = goodsService.updateGoods(goodsDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGoodsDto)
                .toUri();
        LOGGER.debug("The goods updated.");
        return ResponseEntity.created(location).body(savedGoodsDto);
    }

    @Override
    public ResponseEntity<GoodsDto> deleteGoods(@Positive UUID id) {
        goodsService.deleteGoods(id);

        LOGGER.debug("The Goods deleted." );
        return ResponseEntity.noContent().build();
    }
}