package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import com.codegym.cms.service.CustomerService;
import com.codegym.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable<Province> provinces = provinceService.findAll();
        return new ModelAndView("/province/list", "provinces", provinces);
    }

    @GetMapping("/create-province")
    public ModelAndView createProvinceForm() {
        return new ModelAndView("/province/create", "province", new Province());
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);

        ModelAndView modelAndView = new ModelAndView("/province/create", "province", province);
        modelAndView.addObject("message", "New province created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView editProvinceForm(@PathVariable("id") Long id) {
        return new ModelAndView("/province/edit", "province", provinceService.findById(id));
    }

    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/edit", "message", "updated successfully");
        modelAndView.addObject("province", province);
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView deleteProvinceForm(@PathVariable("id") Long id) {
        return new ModelAndView("/province/delete", "province", provinceService.findById(id));
    }

    @PostMapping("/delete-province")
    public ModelAndView deleteProvince(@ModelAttribute("province") Province province) {
        provinceService.remove(province.getId());
        ModelAndView modelAndView = new ModelAndView("/province/list", "provinces",provinceService.findAll());
        modelAndView.addObject("message", "deleted successfully");
        return modelAndView;
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) {
        Province province = provinceService.findById(id);
        if (province == null) {
            return new ModelAndView("/error-404");
        }
        Iterable<Customer> customers = customerService.findAllByProvince(province);

        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province", province);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

}
