package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.response.CityProvinceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityProvinceServiceImp {
    List<CityProvinceResponse> getAll();
}
