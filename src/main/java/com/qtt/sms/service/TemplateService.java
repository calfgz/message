package com.qtt.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.TemplateMapper;
import com.qtt.sms.model.Template;
import com.qtt.sms.model.TemplateExample;

@Service
public class TemplateService {
	@Autowired
	TemplateMapper templateMapper;
	
	public int save(Template record) {
		return templateMapper.insert(record);
	}
	
	public Template findById(int id) {
		return templateMapper.selectByPrimaryKey(id);
	}
	
	public Template findByTemplateId(String templateId) {
		TemplateExample example = new TemplateExample();
		example.createCriteria().andTemplateIdEqualTo(templateId);
		List<Template> list = templateMapper.selectByExample(example);
		Template record = null;
		if (!list.isEmpty()) {
			record = list.get(0);
		}
		return record;
	}
	
	public int updateAll(Template record) {
		return templateMapper.updateByPrimaryKey(record);
	}
	
	public int updateNoNull(Template record) {
		return templateMapper.updateByPrimaryKeySelective(record);
	}
	
	public int delete(int id) {
		return templateMapper.deleteByPrimaryKey(id);
	}
	
	public PageInfo<Template> getPager(TemplateExample example, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("create_time desc");
		List<Template> list = templateMapper.selectByExample(example);
		PageInfo<Template> pager = new PageInfo<Template>(list);
		return pager;
	}
	
	public List<Template> getList(TemplateExample example, int top, String orderBy) {
		if(top > 0) {
			PageHelper.startPage(1, top);
		}
		;
		if(orderBy != null) {
			PageHelper.orderBy(orderBy);
		}
		List<Template> list = templateMapper.selectByExample(example);
		return list;
	}
    
    /**
     * 批量更新
     * @param record
     * @param example
     * @return
     */
    public int updateByExampleNoNull(Template record, TemplateExample example) {
    	return templateMapper.updateByExampleSelective(record, example);
    }
}
