package com.zpyu.springboot.dao;

import com.zpyu.springboot.dao.base.MyBatisBaseDao;
import com.zpyu.springboot.entity.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentDao extends MyBatisBaseDao<Attachment, Integer, AttachmentExample>{

}