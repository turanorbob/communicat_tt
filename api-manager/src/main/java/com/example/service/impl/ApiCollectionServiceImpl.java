package com.example.service.impl;

import com.example.entity.ApiCollection;
import com.example.repository.ApiCollectionRepository;
import com.example.service.IApiCollectionService;
import com.example.util.IdWorker;
import com.example.vo.AddApiCollectionVo;
import com.example.vo.ApiCollectionSearchVo;
import com.example.vo.UpdateApiCollectionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
@Service
public class ApiCollectionServiceImpl implements IApiCollectionService {

    @Autowired
    private ApiCollectionRepository apiCollectionRepository;


    @Override
    public boolean create(AddApiCollectionVo param) {
        ApiCollection entity = new ApiCollection();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiCollectionRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiCollectionVo param) {
        Optional<ApiCollection> optionalApi = apiCollectionRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        ApiCollection entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiCollectionRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiCollectionRepository.existsById(id)){
            return false;
        }
        apiCollectionRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiCollection detail(String id) {
        return apiCollectionRepository.findById(id).get();
    }

    @Override
    public Page<ApiCollection> page(ApiCollectionSearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiCollectionRepository.findAll(pageable);
    }
}
