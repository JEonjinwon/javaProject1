package LastApt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDao {

	String residentlog_In(Map<String, String> params);

	int createCitizen(ResidentVO mb);

	int carAdd(CarVO car);

	boolean idcheak(ResidentVO mb);

	int createManger(ManagerVO mg);

	String carLoc(CarVO car);

	int write(CommunityVO cv);

	int writeedit(CommunityVO cv);

	int writedelete(int input);

	int elect(ResidentVO mb);

	int writerepl(Map<String, String> params);

	int exWater(ResidentVO mb);

	int exGuard(ResidentVO mb);

	String postCheck(Map<String, String> params);

	int CitizenRevise(Map<String, String> params);

	int writing2(HashMap<String, String> params2);

	String mypage(String resident_id);


	int writeEdit(HashMap<String, String> params2);

	boolean midcheck(ManagerVO mg);

	String managerLogin(Map<String, String> params);

	String residentList(ResidentVO mb);

	int residentDel(ResidentVO mb);

	int residentCorrect(ResidentVO mb);

	String writeList(String resident_id);

	int userType(String ID2);

	String carnum(int addr_num);

	List myWrite(String iD2);

	int useraddr(String resident_id);

	String username(String resident_id);

	int wDelete(int myNum);

	int writeMine(int input);

	int delete(Map<String, String> params);

	List ManagerWrite(String iD2);
	

	String mangerWriteList(String iD2);

	int guestLog(GuestVO gv);

	String whereVisit(String visitName);
	
	String resiDentCar(ResidentVO mb);

	boolean carCheak(CarVO car);


	ArrayList parkingList(ParkingVO pk);

	int parkadd(ParkingVO pk);

	ArrayList<String> visitList();

	int parkingOut(int input);

	ArrayList<Integer> myCarNum(int addr);

	ArrayList<Integer> parkingCar();



}