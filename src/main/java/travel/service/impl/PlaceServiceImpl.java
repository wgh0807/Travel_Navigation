package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.GenericDao;
import travel.obj.Place;
import travel.service.PlaceService;

@Service
public class PlaceServiceImpl extends GenericServiceImpl<Place,Integer> implements PlaceService {
    @Override
    @Autowired
    void setGenericDao(GenericDao<Place, Integer> genericDao) {
        this.genericDao = genericDao;
    }
}
