package gtes.service;

import gtes.dao.common.IOperations;
import gtes.entity.Disposal;

import java.util.HashMap;
import java.util.List;

public interface DisposalService extends IOperations<Disposal> {
    List<Disposal> findDisposalSensorNumber(HashMap mapPredicates);
    List<String> numberList();
}
