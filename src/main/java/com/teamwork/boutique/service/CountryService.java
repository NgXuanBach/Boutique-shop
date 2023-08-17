package com.teamwork.boutique.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamwork.boutique.entity.CountryEntity;
import com.teamwork.boutique.payload.response.CountryResponse;
import com.teamwork.boutique.repository.CountryRepository;
import com.teamwork.boutique.service.imp.CountryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService implements CountryServiceImp {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<CountryResponse> getAll() {
        List<CountryResponse> countryResponses = new ArrayList<>();
        if (redisTemplate.hasKey("listCountry")) {
            System.out.println("co gia tri tren redis");
            String data = redisTemplate.opsForValue().get("listCountry").toString();
            Type listType = new TypeToken<ArrayList<CountryResponse>>() {
            }.getType();
            countryResponses = new Gson().fromJson(data, listType);
        } else {
            System.out.println("khong co gia tri tren redis");
            for (CountryEntity item : countryRepository.findAll()) {
                CountryResponse response = new CountryResponse();
                response.setName(item.getName());
                response.setId(item.getId());
                countryResponses.add(response);
            }
        }
        Gson gson = new Gson();
        String data = gson.toJson(countryResponses);
        redisTemplate.opsForValue().set("listCountry", data);
        return countryResponses;
    }
}
