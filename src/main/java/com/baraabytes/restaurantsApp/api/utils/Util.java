package com.baraabytes.restaurantsApp.api.utils;

import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public final class Util {

    public static <E> Page<E> paginate(List<E> list,Integer pageNum,Integer pageSize){
        int skipCount = (pageNum - 1) * pageSize;
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by("id").ascending());
        List<E> sublist =  list.stream().skip(skipCount).limit(pageSize).collect(Collectors.toList());
        final Page<E> pageList = new PageImpl<E>(sublist,pageable,list.size());
        return  pageList;
    }

}
