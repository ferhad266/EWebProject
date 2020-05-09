package hotel.service.impl;

import hotel.dao.WorkerDao;
import hotel.model.Worker;
import hotel.service.WorkerService;

import java.util.List;

public class WorkerServiceImpl implements WorkerService {

    private WorkerDao workerDao;

    public WorkerServiceImpl(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    @Override
    public List<Worker> getWorkerList() throws Exception {
        return workerDao.getWorkerList();
    }

    @Override
    public boolean addWorker(Worker worker) throws Exception {
        return workerDao.addWorker(worker);
    }

    @Override
    public Worker getWorkerById(Long workerId) throws Exception {
        return workerDao.getWorkerById(workerId);
    }

    @Override
    public List<Worker> searchWorkerData(String keyword) throws Exception {
        return workerDao.searchWorkerData(keyword);
    }

    @Override
    public boolean updateWorker(Worker worker) throws Exception {
        return workerDao.updateWorker(worker);
    }

    @Override
    public boolean deleteWorker(Long workerId) throws Exception {
        return workerDao.deleteWorker(workerId);
    }
}
