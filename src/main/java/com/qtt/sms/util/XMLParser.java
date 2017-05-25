package com.qtt.sms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * User: calf
 */
public class XMLParser {
    
	/**
	 * dom4j解释xml
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
    public static Map<String, Object> getMapFromXML(String xml) throws DocumentException {    	
    	Document document = DocumentHelper.parseText(xml);
    	Element root = document.getRootElement();
    	//System.out.println(root.toString());
    	return XMLParser.getMapFromElement(root);
    }
    
    /**
     * 获取List
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static List<Map<String, Object>> getListFromXML(String xml) throws DocumentException {  	
    	Document document = DocumentHelper.parseText(xml);
    	Element root = document.getRootElement();
    	List<Element> elements = root.elements(); 	
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	boolean isMap = elements.get(0).selectNodes("//*").isEmpty();
    	if (isMap) {
    		list.add(XMLParser.getMapFromElement(root));
    	} else {
	    	for(Element element : elements) {
	        	list.add(XMLParser.getMapFromElement(element));  	
	    	}
    	}
    	return list;
    }
    
    /**
     * 获取Map
     * @param element
     * @return
     */
    public static Map<String, Object> getMapFromElement(Element element) {
    	List<Element> list = element.elements(); 	
    	Map<String, Object> map = new HashMap<String, Object>();
    	for(Element elem : list) {
        	map.put(elem.getName(), elem.getTextTrim());    	
    	}
    	return map;
    	
    }


}
