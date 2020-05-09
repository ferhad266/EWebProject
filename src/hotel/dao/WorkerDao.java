package hotel.dao;

import hotel.model.Worker;

import java.util.List;

public interface WorkerDao {

    List<Worker> getWorkerList() throws Exception;

    boolean addWorker(Worker worker) throws Exception;

    Worker getWorkerById(Long workerId) throws Exception;

    List<Worker> searchWorkerData(String keyword) throws Exception;

    boolean updateWorker(Worker worker) throws Exception;

    boolean deleteWorker(Long workerId) throws Exception;

}
