package com.qtt.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.PassagewayMapper;
import com.qtt.sms.model.Passageway;
import com.qtt.sms.model.PassagewayExample;

@Service
public class PassagewayService {
	@Autowired
	PassagewayMapper passagewayMapper;
	
	public Passageway findById(int id) {
		return passagewayMapper.selectByPrimaryKey(id);
	}
	
	public int save(Passageway way) {
		return passagewayMapper.insert(way);
	}
	
	public int updateAll(Passageway way) {
		return passagewayMapper.updateByPrimaryKey(way);
	}
	
	public int updateNoNull(Passageway way) {
		return passagewayMapper.updateByPrimaryKeySelective(way);
	}
    
    public PageInfo<Passageway> getPager(PassagewayExample example, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	PageHelper.orderBy("id desc");
    	List<Passageway> list = passagewayMapper.selectByExample(example);
    	PageInfo<Passageway> page = new PageInfo<Passageway>(list);
    	return page;
    }
    
    public int updateByExampleNotNull(Passageway passageway, PassagewayExample example) {
    	return passagewayMapper.updateByExampleSelective(passageway, example);
    }
    
    public List<Passageway> listByOperators(int cmcc, int cucc, int ctcc) {
    	PassagewayExample example = new PassagewayExample();
    	PassagewayExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusEqualTo(1);
    	criteria.andCmccEqualTo(cmcc);
    	criteria.andCuccEqualTo(cucc);
    	criteria.andCtccEqualTo(ctcc);
    	List<Passageway> list = passagewayMapper.selectByExample(example);
    	return list;
    }
}
