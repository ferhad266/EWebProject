package hotel.web;

import hotel.dao.*;
import hotel.dao.impl.PaymentDaoImpl;
import hotel.dao.impl.RegisterDaoImpl;
import hotel.dao.impl.RoomDaoImpl;
import hotel.dao.impl.WorkerDaoImpl;
import hotel.model.*;
import hotel.service.PaymentService;
import hotel.service.RegisterService;
import hotel.service.RoomService;
import hotel.service.impl.PaymentServiceImpl;
import hotel.service.impl.RegisterServiceImpl;
import hotel.service.impl.RoomServiceImpl;
import hotel.service.WorkerService;
import hotel.service.impl.WorkerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ControllerServlet", urlPatterns = "/cs")
public class ControllerServlet extends HttpServlet {
    WorkerDao workerDao = new WorkerDaoImpl();
    WorkerService workerService = new WorkerServiceImpl(workerDao);

    RoomDao roomDao = new RoomDaoImpl();
    RoomService roomService = new RoomServiceImpl(roomDao);

    RegisterDao registerDao = new RegisterDaoImpl();
    RegisterService registerService = new RegisterServiceImpl(registerDao);

    PaymentDao paymentDao = new PaymentDaoImpl();
    PaymentService paymentService = new PaymentServiceImpl(paymentDao);

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String action = null;
        String address = null;
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("register")) { //FORM SUBMIT
                BufferedWriter bf = new BufferedWriter(new FileWriter("D:\\qrup40.txt"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String password = request.getParameter("pass");
                bf.write("name " + name);
                bf.newLine();
                bf.write(" surname" + surname);
                bf.newLine();
                bf.write("pass:" + password);
                bf.newLine();
                bf.close();
                pw.write("<h1>Melumat ugurla yazildi</h1>");
            } else if (action.equalsIgnoreCase("getWorkerList1")) { // LINK
                List<Worker> workerList = workerDao.getWorkerList();
                request.setAttribute("workerList", workerList);
                address = "/WEB-INF/pages/workerList1.jsp";
//                response.sendRedirect("/WEB-INF/pages/workerList1.jsp");
            } else if (action.equalsIgnoreCase("getRoomList1")) { //LINK
                List<Room> roomList = roomService.getRoomList();
                request.setAttribute("roomList", roomList);
                System.out.println("roomList "+ roomList);
                address = "/WEB-INF/pages/roomList1.jsp";
            } else if (action.equalsIgnoreCase("getWorkerList")) { //AJAX
                List<Worker> workerList = workerDao.getWorkerList();
                request.setAttribute("workerList", workerList);
                address = "/WEB-INF/pages/workerList.jsp";
            } else if (action.equalsIgnoreCase("getRoomList")) { //AJAX
                List<Room> roomList = roomDao.getRoomList();
                request.setAttribute("roomList", roomList);
                System.out.println("roomList"+ roomList);
                address = "/WEB-INF/pages/roomList.jsp";
            } else if (action.equalsIgnoreCase("getPaymentList")) {
                List<Payment> paymentList = paymentService.getPaymentList();
//                List<Worker> workerList = workerService.getWorkerList();
                List<Room> roomList = roomService.getRoomList();
                List<Register> registerList = registerService.getRegisterList();
                request.setAttribute("registerList", registerList);
                request.setAttribute("paymentList", paymentList);
//                request.setAttribute("workerList", workerList);
                request.setAttribute("roomList", roomList);
                address = "/WEB-INF/pages/paymentList.jsp";
            } else if (action.equalsIgnoreCase("getRegisterList")) {
                List<Register> registerList = registerService.getRegisterList();
                request.setAttribute("registerList", registerList);
                address = "/WEB-INF/pages/registerList.jsp";
            } else if (action.equalsIgnoreCase("addWorker")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String dob = request.getParameter("dob");
                String fatherName = request.getParameter("fatherName");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                Worker worker = new Worker();
                worker.setName(name);
                worker.setSurname(surname);
                worker.setDob(df.parse(dob));
                worker.setFatherName(fatherName);
                worker.setPhone(phone);
                worker.setEmail(email);
                boolean isAdded = workerService.addWorker(worker);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("addRoom")) {
                String roomNumber = request.getParameter("roomNumber");
                String roomSituation = request.getParameter("roomSituation");
                Float price = Float.parseFloat(request.getParameter("price"));
                Integer humanCount = Integer.parseInt(request.getParameter("humanCount"));
                String roomType = request.getParameter("roomType");
                Room room = new Room();
                room.setRoomNumber(roomNumber);
                room.setRoomSituation(roomSituation);
                room.setPrice(price);
                room.setHumanCount(humanCount);
                room.setRoomType(roomType);
                boolean isAdded = roomService.addRoom(room);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("addPayment")) {
                Long workerCombo = Long.parseLong(request.getParameter("workerCmb"));
                Long roomCombo = Long.parseLong(request.getParameter("roomCmb"));
                Long registerCombo = Long.parseLong(request.getParameter("registerCmb"));
                Float amount = Float.parseFloat(request.getParameter("amount"));
                Date payDate = df.parse(request.getParameter("payDate"));
                Payment payment = new Payment();
                Worker worker = new Worker();
                worker.setId(workerCombo);
                Room room = new Room();
                room.setId(roomCombo);
                Register register = new Register();
                register.setId(registerCombo);
                payment.setWorker(worker);
                payment.setRegister(register);
                payment.setRoom(room);
                payment.setAmount(amount);
                payment.setPayDate(payDate);
                boolean isAdded = paymentService.addPayment(payment);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("addRegister")) {
                Long workerCombo = Long.parseLong(request.getParameter("workerCmb"));
                Long roomCombo = Long.parseLong(request.getParameter("roomCmb"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                Date dob = df.parse(request.getParameter("dob"));
                String fatherName = request.getParameter("fatherName");
                String adultCount = request.getParameter("adultCount");
                String childCount = request.getParameter("childCount");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                Date check_in = df.parse("check_in");
                Date check_out = df.parse("check_out");
                Worker worker = new Worker();
                worker.setId(workerCombo);
                Room room = new Room();
                room.setId(roomCombo);
                Register register = new Register();
                register.setWorker(worker);
                register.setRoom(room);
                register.setName(name);
                register.setSurname(surname);
                register.setDob(dob);
                register.setFatherName(fatherName);
                register.setAdultCount(adultCount);
                register.setChildCount(childCount);
                register.setPhone(phone);
                register.setEmail(email);
                register.setCheck_in(check_in);
                register.setCheck_out(check_out);
                boolean isAdded = registerService.addRegister(register);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("newPayment")) {
                List<Register> registerList = registerService.getRegisterList();
                List<Room> roomList = roomService.getRoomList();
                List<Worker> workerList = workerService.getWorkerList();
                request.setAttribute("roomList", roomList);
                request.setAttribute("registerList", registerList);
                request.setAttribute("workerList", workerList);
                address = "WEB-INF/pages/newPayment.jsp";
            } else if (action.equalsIgnoreCase("newRegister")) {
                List<Worker> workerList = workerService.getWorkerList();
                List<Room> roomList = roomService.getRoomList();
                request.setAttribute("roomList", roomList);
                request.setAttribute("workerList", workerList);
                address = "WEB-INF/pages/newRegister.jsp";
            } else if (action.equalsIgnoreCase("editPayment")) {
                Long paymentId = Long.parseLong(request.getParameter("paymentId"));
                Payment payment = paymentService.getPaymentById(paymentId);
                List<Register> registerList = registerService.getRegisterList();
                List<Room> roomList = roomService.getRoomList();
                List<Worker> workerList = workerService.getWorkerList();
                request.setAttribute("roomList", roomList);
                request.setAttribute("registerList", registerList);
                request.setAttribute("workerList", workerList);
                request.setAttribute("payment", payment);
                address = "WEB-INF/pages/editPayment.jsp";
            } else if (action.equalsIgnoreCase("editRegister")) {
                Long registerId = Long.parseLong(request.getParameter("registerId"));
                Register register = registerService.getRegisterById(registerId);
                System.out.println(" Register "+register);
                List<Room> roomList = roomService.getRoomList();
                List<Worker> workerList = workerService.getWorkerList();
                address = "WEB-INF/pages/editRegister.jsp";
            } else if (action.equalsIgnoreCase("editWorker")) {
                Long workerId = Long.parseLong(request.getParameter("workerId"));
                Worker worker = workerService.getWorkerById(workerId);
                request.setAttribute("worker", worker);
                address = "WEB-INF/pages/editWorker.jsp";
            } else if (action.equalsIgnoreCase("editRoom")) {
                Long roomId = Long.parseLong(request.getParameter("roomId"));
                System.out.println("roomId = "+roomId);
                Room room = roomService.getRoomById(roomId);
                request.setAttribute("room", room);
                address = "WEB-INF/pages/editRoom.jsp";
            } else if (action.equalsIgnoreCase("updateWorker")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String dob = request.getParameter("dob");
                String fatherName = request.getParameter("fatherName");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                Long workerId = Long.parseLong(request.getParameter("workerId"));
                Worker worker = new Worker();
                worker.setId(workerId);
                worker.setName(name);
                worker.setSurname(surname);
                worker.setDob(df.parse(dob));
                worker.setFatherName(fatherName);
                worker.setPhone(phone);
                worker.setEmail(email);
                boolean isUpdated = workerService.updateWorker(worker);
                response.setContentType("text/html");
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateRoom")) {
                String roomNumber = request.getParameter("roomNumber");
                String roomSituation = request.getParameter("roomSituation");
                Float price = Float.parseFloat(request.getParameter("price"));
                Integer humanCount = Integer.parseInt(request.getParameter("humanCount"));
                String roomType = request.getParameter("roomType");
                Long roomId = Long.parseLong(request.getParameter("roomId"));
                Room room = new Room();
                room.setId(roomId);
                room.setRoomNumber(roomNumber);
                room.setRoomSituation(roomSituation);
                room.setPrice(price);
                room.setHumanCount(humanCount);
                room.setRoomType(roomType);
                boolean isUpdated = roomService.updateRoom(room);
                response.setContentType("text/html");
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updatePayment")) {
                Long paymentId = Long.parseLong(request.getParameter("paymentId"));
                Long workerCombo = Long.parseLong(request.getParameter("workerCmb"));
                Long roomCombo = Long.parseLong(request.getParameter("roomCmb"));
                Long registerCombo = Long.parseLong(request.getParameter("registerCmb"));
                Float amount = Float.parseFloat(request.getParameter("amount"));
                Date payDate = df.parse(request.getParameter("payDate"));
                Payment payment = new Payment();
                Worker worker = new Worker();
                worker.setId(workerCombo);
                Room room = new Room();
                room.setId(roomCombo);
                Register register = new Register();
                register.setId(registerCombo);
                payment.setId(paymentId);
                payment.setWorker(worker);
                payment.setRegister(register);
                payment.setRoom(room);
                payment.setAmount(amount);
                payment.setPayDate(payDate);
                boolean isUpdated = paymentService.updatePayment(payment);
                response.setContentType("text/html");
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateRegister")) {
                Long registerId = Long.parseLong(request.getParameter("registerId"));
                Long workerCombo = Long.parseLong(request.getParameter("workerCmb"));
                Long roomCombo = Long.parseLong(request.getParameter("roomCmb"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                Date dob = df.parse(request.getParameter("dob"));
                String fatherName = request.getParameter("fatherName");
                String adultCount = request.getParameter("adultCount");
                String childCount = request.getParameter("childCount");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                Date check_in = df.parse("check_in");
                Date check_out = df.parse("check_out");
                Worker worker = new Worker();
                worker.setId(workerCombo);
                Room room = new Room();
                room.setId(roomCombo);
                Register register = new Register();
                register.setId(registerId);
                register.setWorker(worker);
                register.setRoom(room);
                register.setName(name);
                register.setSurname(surname);
                register.setDob(dob);
                register.setFatherName(fatherName);
                register.setAdultCount(adultCount);
                register.setChildCount(childCount);
                register.setPhone(phone);
                register.setEmail(email);
                register.setCheck_in(check_in);
                register.setCheck_out(check_out);
                boolean isUpdated = registerService.updateRegister(register);
                response.setContentType("text/html");
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteWorker")) {
                Long workerId = Long.parseLong(request.getParameter("workerId"));
                boolean isDeleted = workerService.deleteWorker(workerId);
                response.setContentType("text/html");
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deletePayment")) {
                Long paymentId = Long.parseLong(request.getParameter("paymentId"));
                boolean isDeleted = paymentService.deletePayment(paymentId);
                response.setContentType("text/html");
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteRegister")) {
                Long registerId = Long.parseLong(request.getParameter("registerId"));
                boolean isDeleted = registerService.deleteRegister(registerId);
                response.setContentType("text/html");
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteRoom")) {
                Long roomId = Long.parseLong(request.getParameter("roomId"));
                boolean isDeleted = roomService.deleteRoom(roomId);
                response.setContentType("text/html");
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("searchRoomData")) {
                String keyword = request.getParameter("keyword");
                List<Room> roomList = roomService.searchRoomData(keyword);
                request.setAttribute("roomList", roomList);
                address = "/WEB-INF/pages/roomList.jsp";
            } else if (action.equalsIgnoreCase("searchWorkerData")) {
                String keyword = request.getParameter("keyword");
                List<Worker> workerList = workerService.searchWorkerData(keyword);
                request.setAttribute("workerList", workerList);
                address = "/WEB-INF/pages/workerList.jsp";
            } else if (action.equalsIgnoreCase("getRegisterListByRoomId")) {
                Long roomId = Long.parseLong(request.getParameter("roomId"));
                List<Register> registerList = registerService.getRegisterByRoomId(roomId);
                request.setAttribute("registerList", registerList);
                address = "/WEB-INF/pages/registerCombo.jsp";
            } else if (action.equalsIgnoreCase("advancedSearchPaymentData")) {
                Long roomCombo = Long.parseLong(request.getParameter("roomCombo"));
                Long registerCombo = Long.parseLong(request.getParameter("registerCombo"));
                String minAmount = request.getParameter("advMinAmount");
                String  maxAmount = request.getParameter("advMaxAmount");
                String beginDate = request.getParameter("advBeginDate");
                String endDate = request.getParameter("advEndDate");
                AdvancedSearch advancedSearch = new AdvancedSearch();
                advancedSearch.setRoomId(roomCombo);
                advancedSearch.setRegisterId(registerCombo);
                advancedSearch.setMinAmount(minAmount);
                advancedSearch.setMaxAmount(maxAmount);
                advancedSearch.setBeginDate(beginDate);
                advancedSearch.setEndDate(endDate);
                List<Payment> paymentList = paymentService.advancedSearchPaymentData(advancedSearch);
                request.setAttribute("paymentList", paymentList);
                address = "/WEB-INF/pages/paymentData.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (address != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }


    }
}
