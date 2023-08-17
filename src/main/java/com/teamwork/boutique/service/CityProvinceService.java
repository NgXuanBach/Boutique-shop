package com.teamwork.boutique.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamwork.boutique.entity.CityProvinceEntity;
import com.teamwork.boutique.payload.response.CityProvinceResponse;
import com.teamwork.boutique.payload.response.CountryResponse;
import com.teamwork.boutique.repository.CityProvinceRepository;
import com.teamwork.boutique.service.imp.CityProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityProvinceService implements CityProvinceServiceImp {
    @Autowired
    private CityProvinceRepository repository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<CityProvinceResponse> getAll() {
        List<CityProvinceResponse> cityProvinceResponses = new ArrayList<>();
        if (redisTemplate.hasKey("listCityProvince")) {
            System.out.println("co gia tri tren redis");
            String data = redisTemplate.opsForValue().get("listCityProvince").toString();
            Type listType = new TypeToken<ArrayList<CityProvinceResponse>>() {
            }.getType();
            cityProvinceResponses = new Gson().fromJson(data, listType);
        } else {
            for (CityProvinceEntity item : repository.findAll()) {
                CityProvinceResponse response = new CityProvinceResponse();
                response.setName(item.getName());
                response.setId(item.getId());
                cityProvinceResponses.add(response);
            }
        }
        Gson gson = new Gson();
        String data = gson.toJson(cityProvinceResponses);
        redisTemplate.opsForValue().set("listCityProvince", data);
        return cityProvinceResponses;
    }
}
