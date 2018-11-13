package com.codegym.cms.controller;

import com.codegym.cms.model.Province;
import com.codegym.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable<Province> provinces = provinceService.findAll();
        return new ModelAndView("/province/list", "provinces", provinces);
    }
}
