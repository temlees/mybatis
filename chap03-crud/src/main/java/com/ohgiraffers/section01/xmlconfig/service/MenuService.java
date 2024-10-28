package com.ohgiraffers.section01.xmlconfig.service;

import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.model.MenuDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {

   private final MenuDAO menuDAO;

    public MenuService() {
        menuDAO = new MenuDAO();
    }

    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession();
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);
        sqlSession.close();

        return menuList;
    }

    public MenuDTO selectMenuByCode(Map<String, String> stringStringMap) {
        SqlSession sqlSession = getSqlSession();
        MenuDTO menu = menuDAO.selectMenuByCode(sqlSession,stringStringMap);
        sqlSession.close();

        return menu;

    }

    public boolean registMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.insertMenu(sqlSession,menu);

        if (result > 0) {
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0? true : false;
    }

    public boolean updateMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();
        int result = menuDAO.updateMenu(sqlSession,menu);
        if (result > 0) {
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0? true : false;
    }

    public int deleteMenu(int code) {
        SqlSession sqlSession = getSqlSession();
        int result = menuDAO.deleteMenu(sqlSession,code);
        if (result > 0) {
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        return result>0? result : 0;
    }
}
