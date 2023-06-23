package com.example.avitobybraincell.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.example.avitobybraincell.dto.AdsDto;
import com.example.avitobybraincell.dto.CreateAdsDto;
import com.example.avitobybraincell.dto.FullAdsDto;
import com.example.avitobybraincell.dto.ResponseWrapperAdsDto;
import com.example.avitobybraincell.model.Advert;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AdvertMapper {
    Advert createAdsDtoToAdvert(CreateAdsDto createAdsDto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", constant = "link to image")
    @Mapping(target = "author", source = "author.id")
    AdsDto advertToAdsDto(Advert advert);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", constant = "link to image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    FullAdsDto advertToFullAdsDto(Advert advert);

    List<AdsDto> advertListToAdsDtoList(List<Advert> adverts);

    void updateAdvert(CreateAdsDto createAdsDto, @MappingTarget Advert advert);

    default ResponseWrapperAdsDto listToRespWrapperAdsDto(List<Advert> adverts) {
        ResponseWrapperAdsDto result = new ResponseWrapperAdsDto();
        result.setCount(adverts.size());
        result.setResults(advertListToAdsDtoList(adverts));
        return result;
    }
}
