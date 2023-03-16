package org.example.controller;

import org.example.domain.City;
import org.example.redis.CityCountry;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        List<City> cities = mainController.fetchData();
        List<CityCountry> preparedData = mainController.transformData(cities);
        mainController.pushToRedis(preparedData);

        //закроем текущую сессию, чтоб точно делать запрос к БД, а не вытянуть данные из кэша
        mainController.getSessionFactory().getCurrentSession().close();

        //выбираем случайных 10 id городов
        //так как мы не делали обработку невалидных ситуаций, используй существующие в БД id
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        mainController.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        mainController.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        mainController.shutdown();
    }
}
