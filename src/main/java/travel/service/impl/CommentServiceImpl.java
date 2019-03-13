package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.GenericDao;
import travel.obj.Comment;
import travel.service.CommentService;

@Service
public class CommentServiceImpl extends GenericServiceImpl<Comment,Integer> implements CommentService {
    @Override
    @Autowired
    void setGenericDao(GenericDao<Comment, Integer> genericDao) {
        this.genericDao = genericDao;
    }
}
