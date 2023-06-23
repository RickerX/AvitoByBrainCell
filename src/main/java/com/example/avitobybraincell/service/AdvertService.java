package com.example.avitobybraincell.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.avitobybraincell.dto.AdsDto;
import com.example.avitobybraincell.dto.CreateAdsDto;
import com.example.avitobybraincell.dto.FullAdsDto;
import com.example.avitobybraincell.dto.ResponseWrapperAdsDto;
import com.example.avitobybraincell.exception.AdvertNotFoundException;
import com.example.avitobybraincell.mapper.AdvertMapper;
import com.example.avitobybraincell.model.Advert;
import com.example.avitobybraincell.repository.AdvertRepository;

import java.util.List;

@Service
@Slf4j
public class AdvertService {

        private final AdvertRepository advertRepository;
        private final AdvertMapper advertMapper;

        public AdvertService(AdvertRepository advertRepository, AdvertMapper advertMapper) {
            this.advertRepository = advertRepository;
            this.advertMapper = advertMapper;
        }

        @Transactional
        public AdsDto create(CreateAdsDto properties) {
            log.info("Creat advert with properties: " + properties);
            Advert advert = advertMapper.createAdsDtoToAdvert(properties);
            return advertMapper.advertToAdsDto(advertRepository.save(advert));
        }

        @Transactional
        public void delete(int id) {
            log.info("Delete advert with id: " + id);
            Advert advert = advertRepository.findById(id)
                    .orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
            advertRepository.delete(advert);
        }

        @Transactional
        public AdsDto update(int id, CreateAdsDto properties) {
            log.info("Update advert with id: " + id);
            Advert advert = advertRepository.findById(id)
                    .orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
            advertMapper.updateAdvert(properties, advert);
            advertRepository.save(advert);
            return advertMapper.advertToAdsDto(advert);
        }

        public ResponseWrapperAdsDto findAll() {
            log.info("Find all adverts");
            List<Advert> adverts = advertRepository.findAll();
            return advertMapper.listToRespWrapperAdsDto(adverts);
        }

        public FullAdsDto findById(int id) {
            log.info("Find advert by id: " + id);
            Advert advert = advertRepository.findById(id)
                    .orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
            return advertMapper.advertToFullAdsDto(advert);
        }

        public ResponseWrapperAdsDto findAllByAuthUser() {
            log.info("Find adverts by user name");
            //пока возвращаем все объявления
            List<Advert> adverts = advertRepository.findAll();
            return advertMapper.listToRespWrapperAdsDto(adverts);
        }
    }

