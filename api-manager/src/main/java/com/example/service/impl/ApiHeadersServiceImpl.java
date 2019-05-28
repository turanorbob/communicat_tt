package com.example.service.impl;

import com.example.entity.ApiHeaders;
import com.example.repository.ApiHeadersRepository;
import com.example.service.IApiHeadersService;
import com.example.util.IdWorker;
import com.example.vo.AddApiHeadersVo;
import com.example.vo.ApiHeadersSearchVo;
import com.example.vo.UpdateApiHeadersVo;
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
public class ApiHeadersServiceImpl implements IApiHeadersService {

    @Autowired
    private ApiHeadersRepository apiHeadersRepository;


    @Override
    public boolean create(AddApiHeadersVo param) {
        ApiHeaders entity = new ApiHeaders();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiHeadersRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiHeadersVo param) {
        Optional<ApiHeaders> optionalApi = apiHeadersRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        ApiHeaders entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiHeadersRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiHeadersRepository.existsById(id)){
            return false;
        }
        apiHeadersRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiHeaders detail(String id) {
        return apiHeadersRepository.findById(id).get();
    }

    @Override
    public List<ApiHeaders> findAllByApiId(String apiId) {
        return apiHeadersRepository.findAllByApiId(apiId);
    }

    @Override
    public Page<ApiHeaders> page(ApiHeadersSearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiHeadersRepository.findAll(pageable);
    }
}
