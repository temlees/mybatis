package com.ohgiraffers.common;


import com.ohgiraffers.dto.MenuDTO;

import java.util.List;

//결과값을 출력해줄 클래스 - html 대체
public class PrintResult {

    public void printMenuList(List<MenuDTO> menuList) {
        for (MenuDTO menu : menuList) {
            System.out.println(menu);
        }
    }

    public void printErrorMessage(String code) {
        String message = null;

        switch (code) {
            case "selectList" : message="메뉴 전체 조회 실패"; break;
            case "selectMenuByCode"  : message="상세 메뉴 조회 실패"; break;
            case "insert"  : message="메뉴 등록  실패"; break;
            case "update"  : message="메뉴 수정  실패"; break;
            case "delete"  : message="메뉴 삭제  실패"; break;

            default:message="잘못된 처리"; break;
        }
        System.out.println(message);
    }

    public void printMenu(MenuDTO menu) {
        System.out.println(menu);
    }

    public void printSucessMessage(String code) {
        String message = null;
        switch (code) {
            case "insert": message="메뉴 등록 성공";break;
            case "update": message="메뉴 수정 성공";break;
            case "delete": message="메뉴 삭제 성공";break;
            default:message="잘못된 처리"; break;
        }
        System.out.println(message);
    }
}
