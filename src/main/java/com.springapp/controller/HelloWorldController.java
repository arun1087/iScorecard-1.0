package com.springapp.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController
{
    @RequestMapping(value={"/player"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView student()
    {
        return new ModelAndView("student", "command", new Player());
    }

    @RequestMapping(value={"/username"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String printHello(ModelMap model, @RequestParam(value="name", defaultValue="") String name, @RequestParam(value="teamName", defaultValue="") String teamName)
    {
        char c = Character.toUpperCase(name.charAt(0));
        StringBuilder sb = new StringBuilder(name);
        sb.setCharAt(0, c);
        String msg = "Welcome " + sb.toString();
        model.addAttribute("message", msg);
        model.addAttribute("teamName", teamName);

        return "hello1";
    }

    @RequestMapping(value={"/displayscore"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String addData(@ModelAttribute("SpringWeb") Player player, ModelMap model)
    {
        System.out.println(player.getPlayer1());
        System.out.println(player.getScore1());
        StringBuilder query = new StringBuilder("insert into scores_per_match ");
        query.append("(player1,score1,player2,score2,player3,score3,player4,score4,player5,score5,player6,score6,player7,score7,player8,score8,player9,score9,player10,score10,player11,score11)");

        query.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/scorerdbo");
        dataSource.setUsername("root");
        dataSource.setPassword("sonyvaoi");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query.toString(), new Object[] { player.getPlayer1(), Integer.valueOf(Integer.parseInt(player.getScore1())), player.getPlayer2(), Integer.valueOf(Integer.parseInt(player.getScore2())), player.getPlayer3(), Integer.valueOf(Integer.parseInt(player.getScore3())), player.getPlayer4(), Integer.valueOf(Integer.parseInt(player.getScore4())), player.getPlayer5(), Integer.valueOf(Integer.parseInt(player.getScore5())), player.getPlayer6(), Integer.valueOf(Integer.parseInt(player.getScore6())), player.getPlayer7(), Integer.valueOf(Integer.parseInt(player.getScore7())), player.getPlayer8(), Integer.valueOf(Integer.parseInt(player.getScore8())), player.getPlayer9(), Integer.valueOf(Integer.parseInt(player.getScore9())), player.getPlayer10(), Integer.valueOf(Integer.parseInt(player.getScore10())), player.getPlayer11(), Integer.valueOf(Integer.parseInt(player.getScore11())) });
        return "datadisplay";
    }

    @RequestMapping(value={"/entry"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String addData1(ModelMap model, Player player)
    {
        return "dataentry";
    }
}
