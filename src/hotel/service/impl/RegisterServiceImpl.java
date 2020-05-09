package hotel.service.impl;

import hotel.dao.RegisterDao;
import hotel.model.Register;
import hotel.service.RegisterService;

import java.util.List;

public class RegisterServiceImpl implements RegisterService {

    private RegisterDao registerDao;

    public RegisterServiceImpl(RegisterDao registerDao){
        this.registerDao = registerDao;
    }

    @Override
    public List<Register> getRegisterList() throws Exception {
        return registerDao.getRegisterList();
    }

    @Override
    public boolean addRegister(Register register) throws Exception {
        return registerDao.addRegister(register);
    }

    @Override
    public Register getRegisterById(Long registerId) throws Exception {
        return registerDao.getRegisterById(registerId);
    }

    @Override
    public boolean updateRegister(Register register) throws Exception {
        return registerDao.updateRegister(register);
    }

    @Override
    public boolean deleteRegister(Long registerId) throws Exception {
        return registerDao.deleteRegister(registerId);
    }

    @Override
    public List<Register> getRegisterByRoomId(Long roomId) throws Exception {
        return registerDao.getRegisterByRoomId(roomId);
    }
}
