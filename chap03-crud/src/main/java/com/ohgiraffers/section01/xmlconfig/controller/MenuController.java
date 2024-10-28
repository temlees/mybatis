package com.ohgiraffers.section01.xmlconfig.controller;

import com.ohgiraffers.common.PrintResult;
import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.service.MenuService;

import java.util.List;
import java.util.Map;

public class MenuController {


    //view 대신 사용할 객체
    private final PrintResult printResult;

    //컨트롤러의 명령을 받을 객체
    private final MenuService menuService;

    //컨트롤러 생성될때 한번에 다 생성해줌
    public MenuController() {
        printResult = new PrintResult();
        menuService = new MenuService();
    }

    public void selectAllMenu() {

        List<MenuDTO> menuList = menuService.selectAllMenu();

        if (menuList != null){
            printResult.printMenuList(menuList);
        }else {
            printResult.printErrorMessage("selectList");
        }



    }

    public void selectMenuByCode(Map<String, String> stringStringMap) {
        MenuDTO menu = menuService.selectMenuByCode(stringStringMap);

        if (menu != null){
            printResult.printMenu(menu);
        }else {
            printResult.printErrorMessage("selectMenuByCode");
        }
    }

    public void registMenu(Map<String, String> parameter) {
        String name= parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int category = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(category);

        if (menuService.registMenu(menu)){
            printResult.printSucessMessage("insert");
        }else {
            printResult.printErrorMessage("insert");
        }
    }

    public void updateMenu(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));
        String name= parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int category = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setCode(code);
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(category);

        if (menuService.updateMenu(menu)){
            printResult.printSucessMessage("update");
        }else{
            printResult.printErrorMessage("update");
        }
    }

    public void deleteMenu(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));
       int result = menuService.deleteMenu(code);

       if (result >0){
           printResult.printSucessMessage("delete");
       }else {
           printResult.printErrorMessage("delete");

       }
    }
}
