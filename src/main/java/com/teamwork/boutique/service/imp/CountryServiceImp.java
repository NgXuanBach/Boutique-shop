package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.response.CountryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryServiceImp {
    List<CountryResponse> getAll();
}
