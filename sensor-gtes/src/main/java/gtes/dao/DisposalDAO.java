package gtes.dao;

import gtes.dao.common.IGenericDAO;
import gtes.entity.Disposal;

import java.util.HashMap;
import java.util.List;

public interface DisposalDAO extends IGenericDAO<Disposal> {
List<Disposal> findDisposalSensorNumber(HashMap mapPredicates);
List<String> numberList();

}
