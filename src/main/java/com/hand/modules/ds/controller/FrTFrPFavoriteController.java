package com.hand.modules.ds.controller;


import com.hand.modules.ds.service.FrTFrPFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("om/frtfrpfavorite")
public class FrTFrPFavoriteController {
    @Autowired
    private FrTFrPFavoriteService frTFrPFavoriteService;

}
