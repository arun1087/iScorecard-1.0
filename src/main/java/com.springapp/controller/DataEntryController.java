package com.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataEntryController
{
    @RequestMapping(value={"/dataentry"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String printHello(ModelMap model)
    {
        return "datadisplay";
    }
}
