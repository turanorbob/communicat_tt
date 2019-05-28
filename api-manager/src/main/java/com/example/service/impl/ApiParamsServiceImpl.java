package com.example.service.impl;

import com.example.entity.ApiParams;
import com.example.repository.ApiParamsRepository;
import com.example.service.IApiParamsService;
import com.example.util.IdWorker;
import com.example.vo.AddApiParamsVo;
import com.example.vo.ApiParamsSearchVo;
import com.example.vo.UpdateApiParamsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
@Service
public class ApiParamsServiceImpl implements IApiParamsService {

    @Autowired
    private ApiParamsRepository apiParamsRepository;


    @Override
    public boolean create(AddApiParamsVo param) {
        ApiParams entity = new ApiParams();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiParamsRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiParamsVo param) {
        Optional<ApiParams> optionalApi = apiParamsRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        ApiParams entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiParamsRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiParamsRepository.existsById(id)){
            return false;
        }
        apiParamsRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiParams detail(String id) {
        return apiParamsRepository.findById(id).get();
    }

    @Override
    public List<ApiParams> findAllByApiId(String apiId) {
        return apiParamsRepository.findAllByApiId(apiId);
    }

    @Override
    public Page<ApiParams> page(ApiParamsSearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiParamsRepository.findAll(pageable);
    }
}
