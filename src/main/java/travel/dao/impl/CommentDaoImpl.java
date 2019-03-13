package travel.dao.impl;

import org.springframework.stereotype.Repository;
import travel.dao.CommentDao;
import travel.dao.GenericDao;
import travel.obj.Comment;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment,Integer> implements CommentDao {
}
