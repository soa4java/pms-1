package com.zq.service.Impl.basic.opex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.dao.basic.opex.IBasOPEXRequirmentRepository;
import com.zq.service.basic.opex.IBasOPEXRequirmentService;

/**
 *
 * BasOPEXRequirment 表数据服务层接口实现类
 *
 */
@Service
public class BasOPEXRequirmentImpl implements IBasOPEXRequirmentService {

    @Autowired
    private IBasOPEXRequirmentRepository iBasOPEXRequirmentRepository;
    
   

}