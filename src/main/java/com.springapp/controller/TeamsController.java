package com.springapp.controller;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Controller
public class TeamsController
{
    @RequestMapping(value={"/team"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
    @ResponseBody
    public Map<String, Object> hello(@RequestParam(value="name", defaultValue="") String name)
    {
        HashMap<String, Object> response = new HashMap();
        String query = "Select team_id, team_name from team_names where team_name= ?";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/scorerdbo");
        dataSource.setUsername("root");
        dataSource.setPassword("sonyvaoi");
        String[] values = new String[1];
        values[0] = name;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> results = jdbcTemplate.query(query, new ColumnMapRowMapper(), values);
        List<Map<String, Object>> finalResults = new ArrayList();
        for (Map<String, Object> result : results)
        {
            Map<String, Object> finalResult = new HashMap();
            for (Entry<String, Object> resultEntry : result.entrySet())
            {
                String key = ((String)resultEntry.getKey()).toLowerCase();
                Object value = resultEntry.getValue();
                if ("team_id".equals(key)) {
                    finalResult.put(key, value.toString());
                } else {
                    finalResult.put(key, value == null ? null : value.toString());
                }
            }
            finalResults.add(finalResult);
        }
        response.put("teams", finalResults);

        return response;
    }

    @RequestMapping(value={"/scorecard"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
    @ResponseBody
    public Map<String, Object> getScorecard(@RequestParam(value="match_id", defaultValue="") String match_id, Model model)
    {
        HashMap<String, Object> response = new HashMap();
        String query = "Select player1, score1,  player2, score2, player3, score3, player4, score4, player5, score5, player6, score6, player7, score7, player8, score8, player9, score9, player10, score10, player11, score11 from scores_per_match where match_id= ?";



        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/scorerdbo");
        dataSource.setUsername("root");
        dataSource.setPassword("sonyvaoi");
        Integer[] values = new Integer[1];
        values[0] = Integer.valueOf(Integer.parseInt(match_id));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> results = jdbcTemplate.query(query, new ColumnMapRowMapper(), values);
        List<Map<String, Object>> finalResults = new ArrayList();
        for (Map<String, Object> result : results)
        {
            Map<String, Object> finalResult = new HashMap();
            for (Entry<String, Object> resultEntry : result.entrySet())
            {
                String key = ((String)resultEntry.getKey()).toLowerCase();
                String num = key.replaceAll("[^0-9]", "");
                Object value = resultEntry.getValue();
                ArrayList<String> temp = new ArrayList();
                String checkKey = key.replaceAll("[^a-zA-Z]+", "") + "Num" + num;
                if (finalResult.get("playerNum" + num) != null)
                {
                    temp = (ArrayList)finalResult.get("playerNum" + num);
                    temp.add(value.toString());
                    finalResult.put("playerNum" + num, temp);
                }
                else
                {
                    temp.add(value.toString());
                    finalResult.put("playerNum" + num, temp);
                }
            }
            finalResults.add(finalResult);
        }
        response.put("scorecard", finalResults);
        model.addAttribute("match_id", match_id);

        return response;
    }

    @RequestMapping(value={"/viewscorecard"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String printHello(ModelMap model, @RequestParam(value="match_id", defaultValue="") String match_id)
    {
        model.addAttribute("match_id", match_id);
        return "hello";
    }
}
