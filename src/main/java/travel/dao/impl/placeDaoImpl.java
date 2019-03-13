package travel.dao.impl;

import org.springframework.stereotype.Repository;
import travel.dao.PlaceDao;
import travel.obj.Place;

@Repository
public class placeDaoImpl extends GenericDaoImpl<Place,Integer> implements PlaceDao {
}
