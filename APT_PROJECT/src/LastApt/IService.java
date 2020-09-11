package LastApt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IService {
	/**
	 * 로그인을 위한 메서드
	 * @param params mem_id 회원의 아이디, mem_pass 회원의 비밀번호
	 * @return String 아이디와 패스워드가 일치하는 한명의 아이디, 일치하는 사람이 없을때 는 null을 반환
	 * @author 박찬
	 */
	String residentlog_In(Map<String, String> params);

	/**
	 * 입주민 회원가입하는 메서드
	 * @param mb Resdint_id 입주민 아이디,Resdint_PW 입주민 비밀번호 ,Resdint_NAME 입주민 이름 ,ADDR_NUM 입주민 동호수  
	 * @return int 중복아이디가 없고 새로운 입주민 정보가 입력될때 , 중복된 아이디이면 0을 반환 
	 * @author 박찬
	 * @since 2020.09.07
	 */
	int createCitizen(ResidentVO mb);

	/**
	 * 입주민 아이디 중복 체크
	 * @param mb Resdint_id 입주민 아이디 
	 * @return boolean 아이디가 존재하면 true , 아이디가 존재하지 않고 만들수 있으면 false
 	 * @author 박찬
	 */
	boolean idcheak(ResidentVO mb); 


	/**
	    * 관리자 회원가입을 위한 메서드 
	    * @param mg
	    *        manager_id 관리자 아이디
	    *        manager_pass 관리자 비밀번호
	    *        manager_mane 관리자 이름 
	    *        manager_hp 관리자 전화번호
	    * @return int DB에 추가만 하면 되기 때문에 반환이 필요 없다
	    * @author 김성준
	    * @since 2020.09.09
	    * @see 수정내역 없음
	    */
	   int createManger(ManagerVO mg);
	/**
	 * 입주민이 등록한 차량 조회 
	 * @param av Car_num 차량번호, Car_type 차량 종류, Car_Owner 차주명 , Car_LOC 주차 공간 ,ADDR_NUM 동호수
	 * @return String 
	 * @author 박 찬
	 */
	String carnum(int addr_num); 

	/**
	 * 로그인된 회원에 아이디에 맞는 동호수를 받아와 그 동호수에 맞는 주차위치받아오는 메소드
	 * 주차장 테이블에서 주차정보를 받아와 get으로 뷰에 보여준다
	 * @param car
	 * @return Stinrg
	 * @author 박 찬
	 */
	String carLoc(CarVO car); // 
	/**
	    * 입주민 게시판에 민원글을 작성 하는 메소드 
	    * writeSC() 글을 작성하는 메소드 -> 입력을 받으면writerSuggestions변수에 글을 저장하고 put으로 Map에 저장
	    * resident_id를 매개변수로 두어 로그인시 입력한 id를 받아와 put으로 Map에 저장
	    * @author 전진원
	    * @param Params2 거주민 아이디 resident_id , 작성된 글 writerSuggestions
	    * @return int  값을 DB에 올려주고 리턴을 받아올 필요는 없다.
	    * 
	    */
	   int writing2(HashMap<String, String> params2); // 매개변수 게시판VO

	/**
	 * 입주민이 관리비 페이지에 들어가서 본인의 동호수에 맞는 전기세를 확인하는 메서드 
	 * 관리비 테이블에서 VO를이용하여 get으로 동호수가 일치하는 곳의 전기세 정보를 받아온다.
	 * @author 김성준
	 * @params ev
	 * @return int
	 */
//	SELECT EC
//	FROM  EXP
//	WHERE 동호수 = ?

	/**
	    * 입주민이 마이페이지에 들어가서 정보를 수정하는 메서드
	    * 
	    * @author 전진원
	    * @param params   무엇을 수정할지 정하는  input, 수정사항을 정하면 changePW,changeNAME,changeADDR 중에 받아간다.
	    * @return int 값을 DB에 올려주고 리턴을 받아올 필요는 없다.
	    */
	   int citizenRevise(Map<String, String> params);//회원의 정보 수정
	/**
	 * 차량 등록 하는 메서드
	 * @author 박찬
	 * @param params 차량번호 입력  
	 * @return int
	 */
	int carAdd(CarVO car);

	/**
	    * 입주민이 마이페이지에서 본인의 정보를 삭제하기 위해 사용하는 메서드
	    *  inputPW() 비밀번호를 재입력받고 로그인시
	    * 입력한Resident_pw를 매개변수로 받아 재입력한 비밀번호와 같을 시 회원삭제 진행 삭제확인을 재확인 하는 input을 입력
	    * 받아 1이면 삭제 2면 취소 한다. input이 1일시 Map저장 된 값을 service 로 전송
	    * 
	    * @author 김성준
	    * @param params
	    *         resident_id 입주민 아이디
	    *        resident_pass 입주민 비밀번호
	    * @return int DB에 추가만 하면 되기 때문에 반환이 필요 없다
	    * @since 2020.09.09
	    * @see 수정 내역 없음
	    */
	   int delete(Map<String, String> params);

	/**
    * 입주민이 게시판에서 본인이 작성한 민원글을 수정하는 메서드
    * 로그인시 입력한 resident_id를 매개변수로 받아와 put으로 Map 저장 
    * writeSC() 메소드 에서 입력 받은 민원 내용을 받아와 writerSuggestions 변수에 저장 하여 Put으로 Map 저장 
    * 저장된 Map을 service로 전송
    * @author 전진원
    * @param params2 입주민 아이디 resident_id, 작성해준 글 writerSuggestions
    * @return int  값을 DB에 올려주고 리턴을 받아올 필요는 없다.
    */
   int writeEdit(HashMap<String, String> params2);

   /**
    * 입주민이나 관리자가 게시판에 등록된 민원글을 삭제하는 메서드 
    * 
    * @author 전진원
    * @param input 거주민이 본인의 글중에 글번호 선택한 값 
    * @return int  값을 DB에 올려주고 리턴을 받아올 필요는 없다.
    */
   int writedelete(int input);

   /**
    * 관리자가 게시판에 입주민이 등록된 글에 민원 해결을 위해 댓글을 등록하는 메서드
    * @author 김성준
    * @param cv
    *        Writer_repl 민원글 댓글
    * @return int DB에 추가만 하면 되기 때문에 반환이 필요 없다
    * @since 2020.09.09
    * @see 수정 내역 없음
    */
   int writerepl(Map<String, String> params);

   /**
    * 입주민이 게시판에 등록된 민원글 조회 할 수 있는 메서드
    * @author 김성준
 * @param resident_id 
    * @return String 입주민이 글 조회 시에 민원글이 없으면 null
    * @since 2020.09.09
    * @see 수정 내역 없음
    */
   String writeList(String resident_id);
   
   /**
    * 입주민이 전기세를 확인하기 위한 메서드 
    * @author 김성준
    * @params ev
    *         addr 동/호수
    *         residenmt_addr 입주민 동/호수
    * @return int 일치하는 동호수의 전기세  값 반환
    * @since 2020.09.09
    * @see 수정 내역 없음
    */
   int elect(ResidentVO mb); 

   /**
    * 입주민이 수도세를 확인하기 위한 메서드 
    * @author 김성준
    * @param ev
    *        addr 동/호수
    *        resident_addr 입주민 동/호수
    * @return int  일치하는 동호수의 수도세 값 반환
    * @since 2020.09.09
    * @see 수정 내역 없음
    */
   int exWater(ResidentVO mb);

   /**
    * 입주민이 경비비를 확인하기 위한 메서드 
    * @author 김성준
    * @param ev
    *        addr 동/호수
    *        resident_addr 입주민 동/호수
    * @return int 일치하는 동호수의  경비비  값 반환
    * @since 2020.09.09
    * @see 수정 내역 없음
    */
   int exGuard(ResidentVO mb);

	   /**
	    * 입주민이 우편물 확인 페이지에 들어가서 본인의 동호수에 맞는 우편함에 우편물이 무엇이있는지 아니면 없는지 확인하는 메서드
	    * 로그인시 입력 받은 아이디를 매개변수 resident_id로 받아온다. 
	    * resident_id를 put으로 Map에 저장하여 service에 보내줘서
	    * 동호수 테이블에서 해당 id에 맞는 동호수를 찾아서 우편테이블에 동호수에 맞는 우편물을 가져온다. 
	    * @author 전진원
	    * @param params resident_id 거주민의 아이디
	    * @return String 우편물의 정보를 받아온다.   
	    */
	   String postCheck(Map<String, String> params);

	/**
	 * 입주민이 마이페이지에서 본인의 비밀번호, 주소 등 정보를 수정하는 메서드
	 * 
	 * @author 박 찬
	 * @param params1
	 * @return String
	 */
	String myPage(String resident_id);
	/**
	    * 관리자가 로그인 하기 위한 메서드 
	    * @author 김성준
	    * @param params
	    *        manager_id 관리자 아이디
	    *        manager_pass 관리자 비밀번호
	    * @return String 관리자 아이디와 비밀번호가 일치하지 않다면 null
	    * @since 2020.09.09
	    * @see 수정 내역 없음
	    */
	   String managerLogin(Map<String, String> params);

	   /**
	    * 관리자의 아이디 중복 체크를 위한 메소드
	    * 회원 목록에서도 같은 아이디 중복 체크 하여 회원가입
	    * 제한 아이디가 존재하면 true 없으면 false
	    * @author 김성준
	    * @param mg
	    *         manager_id
	    * @return boolean 목록에 아이디가 중복되면 false, 중복되지 않다면 true
	    * @since 2020.09.09
	    * @see 수정 내역 없음
	    */
	   boolean midcheck(ManagerVO mg);

	/**
	 * 관리자가 회원 목록을 출력하여 회원 정보를 확인 할 수 있는 메소드 회원정보 Resient id,Resient pw ,Resient
	 * name,Resient addr, Resient_CODE 조회
	 * @author 박 찬
	 * @param mb
	 * @return
	 */
	String residentList(ResidentVO mb);

	/**
	    * 관리자가 입주민로그인 여부 권한을 0으로 변경하여 입주민이 로그인 불가능하게 만듬 Resident_code 값이 0이면 삭제된 회원
	    * Resident_code 값이 1이면 로그인 가능한 회원 회원가입시에는 Resident_code 값이 기본 값1 로지정 관리자는
	    * 입주민의 로그인을 사용 여부 권한을 줄 수 있음.
	    * @author 김성준
	    * @param mb
	    *          resident_code 입주민 분류 코드
	    * @return int  기본 값은 0이며, 입주민 탈퇴시 1값을 가지며 로그인 불가
	    * @since 2020.09.09
	    * @see 수정 내역 없음
	    */
	   int residentDel(ResidentVO mb);

//	/**
//	 * 관리자가 삭제된 회원으 코드를 재수정 하여 재가입 가능하게 만드느 메소드 Resient의 정보에서 Resient_CODE 값이
//	 * 0이면 회원이 직접 삭제된 코드 0일시 회원 로그인 불가 하게 만들고 1이면 회원이 로그인 가능하게 함 초기 회원 가입시에는 1로
//	 * 기본값을 지정 관리자가 삭제된 회원을 사용 가능하게 하려면 CODE값을 0에서 1로 바꿔야함
//	 * @author 박 찬
//	 * @param mb
//	 * @return int
//	 */
	int residentCorrect(ResidentVO mb);

	/**
	    * 게시판에 접속시 관리자 모드와 거주민 모드르 구별 하기 위하여 거주민 로그인시 거주민 테이블에서 거주민 전용 코드 (code =1)를
	    *  받아와 거주민 전용 게시판 으로 접속 할 수 있게 해주는 메소드
	    * @author 전진원
	    * @param mb.getResident_code()  게시판에 접속하면 거주민 코드을 받는것 . 
	    * @return int 게시판 코드 1를 받아온다.
	    */
	   int userType(String iD2);

	   
	   /**
	    * 글을 삭제하기위해 거주민의 id에 맞는 글 즉 본인이 작성한 글의 글번호만 받아오는 메서드
	    * 
	    * @author 전진원
	    * @param iD2 거주민의 아이디
	    * @return List 글번호를 리스트로 받아와야한다.
	    */
	   List myWrite(String iD2);


	   int useraddr(String resident_id);

	String username(String resident_id);

	  /**
	    * 글을 삭제해주는 메서드
	    * 
	    * @author 전진원
	    * @param myNum 입력받은 글번호
	    * @return int 리턴이 궂이 필요하지는 않다.
	    */  
	 int wDelete(int myNum);

	   /**
	    * 입주민이나 관리자가 게시판에 등록된 민원글을 삭제하는 메서드 
	    * 
	    * @author 전진원
	    * @param input 거주민이 본인의 글중에 글번호 선택한 값 
	    * @return int  값을 DB에 올려주고 리턴을 받아올 필요는 없다.
	    */
	   int writeMine(int input);
	   
	   
	   
		/**
		 * 관리자가 글번호 전부를 받아오는 메서드
		 * 
		 * @author 전진원
		 * @param iD2  관리자 아이디
		 * @return List 글번호 받아온다
		 */
		List ManagerWrite(String iD2);
		
		
		 /**
		    * 관리자가 게시판에 등록된 민원글 조회 할 수 있는 메서드
		    * @author 전진원
		    * @return String 관리자가 글 조회 시에 민원글이 없으면 null
		    * @since 2020.09.10
		    * @see 수정 내역 없음
		    */
		String mangerWriteList(String iD2);

		 /**
		    * 방문겍의 정보를 등록하는 메서드
		    * 
		    * @author 김성준
		    * @param gv 방문객 이름 차번호 방문위치
		    * @return int  값을 DB에 올려주고 리턴을 받아올 필요는 없다.
		    */
		int guestLog(GuestVO gv);

		
		 /**
		    * 방문겍의 정보를 등록하는 메서드
		    * 
		    * @author 전진원
		    * @param visitName 방문객 이름 
	 	    * @return String 방문 위치 뜨면됨 
		    */
		String whereVisit(String visitName);


	

	String resiDentCar(ResidentVO mb);

	boolean carCheak(CarVO car);

	ArrayList parkingList(ParkingVO pv);

	int parkadd(ParkingVO pk);

	ArrayList<String> visitList();

	int parkingOut(int input);

	ArrayList<Integer> myCarNum(int addr);

	ArrayList<Integer> parkingCar();





}
