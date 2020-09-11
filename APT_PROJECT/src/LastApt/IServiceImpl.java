package LastApt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IServiceImpl implements IService {

	private IDao dao = new IDaoimpl();

	@Override
	public String residentlog_In(Map<String, String> params) {

		return dao.residentlog_In(params);
		// 오라클 덤프
	}

	@Override
	public int createCitizen(ResidentVO mb) {

		return dao.createCitizen(mb);
	}


	@Override
	public int carAdd(CarVO car) {
		return dao.carAdd(car);
	}

	@Override
	public int delete(Map<String, String> params) {

		return dao.delete(params);
	}

	@Override
	public boolean idcheak(ResidentVO mb) {

		return dao.idcheak(mb);
	}

	@Override
	public int createManger(ManagerVO mg) {
		return dao.createManger(mg);

	}

	@Override
	public String carnum(int addr_num) {
		
		return dao.carnum(addr_num);
	}
	

	@Override
	public String carLoc(CarVO car) {
		return dao.carLoc(car);
	}
	
	@Override
	public int elect(ResidentVO mb) {
		return dao.elect(mb);
	}
	


	@Override
	public int writedelete(int input) {
		return dao.writedelete(input);
	}

	@Override
	public int writerepl(Map<String, String> params) {
		return dao.writerepl(params);
	}

	@Override
	public int exWater(ResidentVO mb) {
		return dao.exWater(mb);
	}

	@Override
	public int exGuard(ResidentVO mb) {
		return dao.exGuard(mb);
	}

	@Override
	public String postCheck(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.postCheck(params);
	}


	@Override
	public int citizenRevise(Map<String, String> params) {
		return dao.CitizenRevise(params);
	}

	@Override
	public int writing2(HashMap<String, String> params2) {
		return dao.writing2(params2);
	}

	@Override
	public String myPage(String resident_id) {
		return dao.mypage(resident_id);
	}

	@Override
	public int writeEdit(HashMap<String, String> params2) {
		return dao.writeEdit(params2);
		
	}
	@Override
	public boolean midcheck(ManagerVO mg) {
		return dao.midcheck(mg);
	}

	@Override
	public String managerLogin(Map<String, String> params) {
		
		return dao.managerLogin(params);
	}

	@Override
	public String residentList(ResidentVO mb) {
		// TODO Auto-generated method stub
		return dao.residentList(mb);
	}

	@Override
	public int residentDel(ResidentVO mb) {
		// TODO Auto-generated method stub
		return dao.residentDel(mb);
	}

	@Override
	public int residentCorrect(ResidentVO mb) {
		// TODO Auto-generated method stub
		return dao.residentCorrect(mb);
	}

	@Override
	public String writeList(String resident_id) {
		// TODO Auto-generated method stub
		return dao.writeList(resident_id);
	}

	@Override
	public int userType(String ID2) {
		// TODO Auto-generated method stub
		return dao.userType(ID2);
	}


	@Override
	public List myWrite(String iD2) {
		// TODO Auto-generated method stub
		return dao.myWrite(iD2);
	}

	@Override
	public int useraddr(String resident_id) {
		// TODO Auto-generated method stub
		return dao.useraddr(resident_id);
	}

	@Override
	public String username(String resident_id) {
		// TODO Auto-generated method stub
		return dao.username(resident_id);
	}

	@Override
	public int wDelete(int myNum) {
		
		return dao.wDelete(myNum);
	}

	@Override
	public int writeMine(int input) {
		return dao.writeMine(input);
	}
	
	@Override
	public List ManagerWrite(String iD2) {
		
		return dao.ManagerWrite(iD2);
	}

	@Override
	public String resiDentCar(ResidentVO mb) {
		return dao.resiDentCar(mb);
		
	}

	@Override
	public boolean carCheak(CarVO car) {
		// TODO Auto-generated method stub
		return dao.carCheak(car);
	}



	@Override
	public ArrayList parkingList(ParkingVO pk) {
		
		return dao.parkingList(pk);
		
	}

	@Override
	public int parkadd(ParkingVO pk) {
		return dao.parkadd(pk);
		
	}
	@Override
	public String mangerWriteList(String iD2) {
		
		return dao.mangerWriteList(iD2);
	}

	@Override
	public int guestLog(GuestVO gv) {
		
		return dao.guestLog(gv);
	}

	@Override
	public String whereVisit(String visitName) {
		
		return dao.whereVisit(visitName);
	}

	@Override
	public ArrayList<String> visitList() {
		
		return dao.visitList();
	}

	@Override
	public int parkingOut(int input) {
		return dao.parkingOut(input);
	}

	@Override
	public ArrayList<Integer> myCarNum(int addr) {
		
		return dao.myCarNum(addr);
	}

	@Override
	public ArrayList<Integer> parkingCar() {
		
		return dao.parkingCar();
	}


	

	




	

}
