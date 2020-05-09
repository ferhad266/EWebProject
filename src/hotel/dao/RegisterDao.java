package hotel.dao;

import hotel.model.Register;

import java.util.List;

public interface RegisterDao {

    List<Register> getRegisterList() throws Exception;

    boolean addRegister(Register register) throws Exception;

    Register getRegisterById(Long registerId) throws Exception;

    boolean updateRegister(Register register) throws Exception;

    boolean deleteRegister(Long registerId) throws Exception;

    List<Register> getRegisterByRoomId(Long roomId) throws Exception;
}
