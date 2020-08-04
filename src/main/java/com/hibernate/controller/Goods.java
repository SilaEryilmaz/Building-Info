package com.hibernate.controller;

import com.hibernate.dto.GoodsDto;
import com.hibernate.exception.ObjNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Validated
public interface Goods {

    @GetMapping(value = "/getGoods/{id}", produces = MediaType.APPLICATION_JSON_VALUE, name = "getGoods")
    GoodsDto getGoods(@PathVariable UUID id) throws ObjNotFoundException;

    @GetMapping(value = "/getGoods", produces = MediaType.APPLICATION_JSON_VALUE, name = "getGoods")
    List<GoodsDto> getAllGoods();

    @PostMapping(value = "/save/goods", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            name = "saveGoods")
    ResponseEntity<GoodsDto> saveGoods(@Valid @RequestBody GoodsDto goodsDto) throws ObjNotFoundException;

    @DeleteMapping(value = "/delete/goods/{id}")
    ResponseEntity<GoodsDto> deleteGoods(@Positive @PathVariable UUID id);

}

