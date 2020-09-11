package LastApt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.spi.MixerProvider;

public class ViewClass {
	private IService service = new IServiceImpl();
	private String login_id;
	private int createCitizen;
	private String managerlogin;
	private int addr_num;
	private int createManger;

	ResidentVO mb = new ResidentVO();
	ManagerVO mg = new ManagerVO();
	AddrVO av = new AddrVO();
	CarVO car = new CarVO();
	CommunityVO cv = new CommunityVO();
	ExpensesVO ev = new ExpensesVO();
	PostVO pv = new PostVO();
	ParkingVO pk = new ParkingVO();
	GuestVO gv = new GuestVO();

	// 시작시

	void start() {
		while (true) {
			try {
				System.out.println("1.회원가입  2.입주민 로그인  3.방문객 4.관리자 로그인  5.종료");
				Scanner sc = new Scanner(System.in);

				int num = sc.nextInt();
				switch (num) {
				case 1:
					// 회원 가입
					// create_id();
					create_id();
					break;
				case 2:
					// 입주민 로그인
					residentlog_In();
					break;
				case 3:
					// 방문객
					visit_Log();
					break;
				case 4:
					// 관리자로그인
					manager_Login();
					break;

				case 5:
					// 종료
					System.out.println("프로그램이 종료됩니다.");
					System.exit(0);// 종료
				default:
					System.out.println("잘못 입력했습니다.");
					System.out.println("프로그램이 종료됩니다.");
					System.exit(0);// 종료
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}
		}
	}

	// 1. 회원가입
	private void create_id() {
		while (true) {
			try {
				System.out.println("1.입주민, 2.관리자, 3.뒤로가기");
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// 입주민 회원가입
					create_Citizen();
					break;
				case 2:
					// 관리자 회원가입
					create_Manager();
					break;
				case 3:
					return;
					// 뒤로가기
				default:
					System.out.println("잘못 입력하였습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");

			}
		}

	}

	// 1.1 입주민 회원가입
	private void create_Citizen() {
		boolean in = false;

		// 아이디 입력
		// 비밀빈호 입력
		// 이름 입력
		// 동/호수 입력
		do {
			String residentID = inputID();// 아이디 입력 메소드
			mb.setResident_id(residentID);
			mg.setManager_id(residentID);
			in = idcheak();
		} while (in == true);

		String residentPW = inputPW();// 비밀번호 입력 메소드
		mb.setResident_pass(residentPW);

		String residentNAME = inputNAME();// 이름 입력 메소드
		mb.setResident_name(residentNAME);

		int residentADDR = inputADDR();// 주소 입력 메소드
		mb.setAddr_num(residentADDR);

		createCitizen = service.createCitizen(mb);
		start();

	}

	private int inputADDR() {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("동입력해주세요. ex)2동 102호 => 2102");
				System.out.println("1동 2동,1층에서 9층까지 각동의 각층은 2집씩 존재한다.");
				int addr = sc.nextInt();
				Pattern p = Pattern.compile("^[1-2][1-9][0][1-2]$");
				Matcher m = p.matcher(Integer.toString(addr));
				if (m.matches()) {
					return addr;
				}
				System.out.println("동호수 입력 잘해라고 ^<^");
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}

	}

	private String inputNAME() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("이름을 입력해주세요");
			System.out.println("2글자에서 5글자만 가능 ");
			String name = sc.next();
			Pattern p = Pattern.compile("^[가-힣]{2,5}$");
			Matcher m = p.matcher(name);
			if (m.matches()) {
				return name;
			}
			System.out.println("형식에 위배되는 이름입니다. ^_^ㅗ   ");

		}
	}

	private String inputPW() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("비밀번호를 입력해주세요");
			System.out.println("8자 이상 20자 이하 숫자와 문자를 입력하세요.");
			String pw = sc.nextLine();

			Pattern p = Pattern.compile("^[A-Za-z0-9]{8,20}$");
			Matcher m = p.matcher(pw);
			if (m.matches()) {
				return pw;
			}
			System.out.println("글자수 맞춰서 숫자와 문자 입력하라했잖아 ㅡㅡ;");
		}
	}

	private String inputID() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("아이디를 입력해주세요");
			System.out.println("3글자에서 10글자 문자와 숫자 ");
			String id = sc.nextLine();
			Pattern p = Pattern.compile("[a-zA-z][a-zA-Z0-9]{3,10}");
			Matcher m = p.matcher(id);
			if (m.matches()) {
				return id;
			}
			System.out.println("아이디가 잘못된 형식입니다.");
		}
	}

	private boolean idcheak() {
		while (true) {
			boolean idcheak = service.idcheak(mb);
			if (idcheak == true) {
				System.out.println("중복된 아이디 입니다.");
				return idcheak;
			} else {
				System.out.println("사용가능한 아이디입니다.");
				return idcheak;
			}

		}
	}

	// 1.2 관리자 회원가입
	// 1.2 관리자 회원가입
	private void create_Manager() {

		boolean in = false;

		// 아이디를 입력받는다.
		do {
			String managerID = inputID();
			mg.setManager_id(managerID);
			mb.setResident_id(managerID);
			in = idcheak();
			// 비밀번호 입력받는다.
		} while (in == true);
		String managerPW = inputPW();
		mg.setManager_pass(managerPW);
		// 이름을 입력 받는다.
		String managerNAME = inputNAME();
		mg.setMamnager_name(managerNAME);
		// 전화번호 입력 받는다.
		String managerHP = managerHP();
		mg.setManager_hp(managerHP);
		// sql
		// int createManager(관리자VO vo);
		createManger = service.createManger(mg);
		start();

	}

	private String managerHP() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("형식에 맞는 전화번호를 입력해주세요");
			System.out.println("형식 : 01x-xxxx-xxxx");
			String HP = sc.nextLine();
			Pattern p = Pattern.compile("^01[0-17-9](-)[0-9]{4}(-)[0-9]{4}$");
			Matcher m = p.matcher(HP);
			if (m.matches()) {
				return HP;
			}
			System.out.println("형식에 위배하는 번호입니다. 혹시 - 는 입력하셨나요?? (^>^;;)");
		}
	}

	private String inputManagerId() {
		// id 중복체크
		// sql
		// int createManager(관리자VO vo);
		// 정규식

		return null;
	}

	// 2. 입주민 로그인
	private void residentlog_In() {
		// ID 입력
		// PW 입력
		// inputID();
		// inputPW();
		String resident_id = inputID();
		String resident_pw = inputPW();
		Map<String, String> parms = new HashMap<>();

		parms.put("resident_id", resident_id);
		parms.put("resident_pass", resident_pw);

		login_id = service.residentlog_In(parms);

		// 로그아웃 null넣어야한다.
		if (login_id == null) {
			System.out.println("회원정보가 없습니다.");

		} else {
			System.out.println(resident_id + "회원님 어서오세요");
			int addr = service.useraddr(resident_id);
			String name = service.username(resident_id);
			mb.setAddr_num(addr);
			mb.setResident_name(name);
			mb.setResident_id(resident_id);
			mb.setResident_pass(resident_pw);
			citizen(resident_id);
		}

		// 로그인 구현 후 입주민 모드

	}

	// 2.1. 입주민 모드
	private void citizen(String resident_id) {

		while (true) {
			try {

				System.out
						.println("1.차량조회, 2.게시판 , 3.관리비 확인, 4.우편물 확인  5.마이페이지 6.뒤로 가기");
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// 1.차량 조회
					car_Look();
					break;
				case 2:
					// 2.게시판
					community(resident_id);
					break;
				case 3:
					// 3.관리비 확인
					expenses();
					break;
				case 4:
					// 4.우편물 확인
					delivery(resident_id);
					break;
				case 5:
					// 마이페이지
					myPage(resident_id);
					break;
				case 6:
					// 뒤로가기
					return;
				default:
					System.out.println("잘못 입력 했습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}
	}

	// 2.1.1. 차량 조회
	private void car_Look() {
		while (true) {
			try {
				System.out.println("1.차량 번호 확인, 2.주차위치확인,3.주차, 4.출차, 5.뒤로가기 ");
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// 차량번호 와 입주자의 정보 출력
					resiDentCar(mb);
					break;
				case 2:
					// 주차 위치 확인
					car_Loc(pk);
					break;
				case 3:
					//주차
					parkadd();
				
					break;
				case 4:
					//출차
					parkingOut(mb);
					break;
				case 5:
					return;
				default:
					System.out.println("잘못 눌렀습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}
	}

	// 2.1.1.1.차량 번호 확인
	private String resiDentCar(ResidentVO mb) {
		// 아이디를 받아와서 동호수를 확인하고 해당하는 차량번호, 차량종 확인
		System.out.println("===================================");
		System.out.println(mb.getResident_name() + "  님의 차량 조회입니다.");
		System.out.println("===================================");
		String carop = service.resiDentCar(mb);
		System.out.println("===================================");

		return carop;

	}

	// 2.1.1.2.주차 위치 확인
	private void car_Loc(ParkingVO pk) {
		// 아이디를 받아와서 동호수를 매칭하여 지정된 주차공간 번호 확인
		ArrayList a = new ArrayList<>();
		a = service.parkingList(pk);
		int b = 0;
		int c = 0;
		String zxc = "□";
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(a.get(j + 10 * i) + "\t");

			}
			System.out.println();

		}
	}

	// 주차장 공간 표시
	private List parkingList(ParkingVO pk) {

		String park[] = new String[50];
		List parking = service.parkingList(pk);

		return parking;

	}
	//차량 출차
	private void parkingOut(ResidentVO mb){
		while(true){
			try{
				ArrayList<Integer> list = new ArrayList<>();
				ArrayList<Integer> list2 = new ArrayList<>();
				list = service.myCarNum(mb.getAddr_num());
				list2 = service.parkingCar();
				ArrayList<Integer> list3 = new ArrayList<>();
				int c = 0, b = 0;
				boolean check = false; //같은차가 있으면 트루
				for (int i = 0; i < list2.size(); i++) {
					check = false;
					c = list2.get(i);
					for (int j = 0; j < list.size(); j++) {
						b = list.get(j);
							if (c == b) {
								check = true;
							}
					}
					if(check == true){
						list3.add(c);
					}
				}
				
				car_Loc(pk);
				Scanner sc = new Scanner(System.in);
				// String userCar = service.resiDentCar(mb);
				System.out.println("출차 가능 차량 : " + list3);
				System.out.println("출차할 차량의 번호를 입력하세요. ");
				for (int i = 0; i < list3.size(); i++) {
					int input = sc.nextInt();
					if (list3.get(i) == input) {
						service.parkingOut(input);
					}else{
						System.out.println("본인의 차가 아닙니다.");
						System.out.println("다시 입력 하세요");
					}
				}
				return;
			}catch(InputMismatchException e){
				System.out.println("형식에 맞지 않습니다.");
			}catch(NumberFormatException e){
				System.out.println("형식에 맞지 않습니다.");
				
			}
			
		}
		
	}
	

	private int userType(String ID2) {
		int code = service.userType(ID2);

		return code;
	}

	// 2.1.2. 소통게시판
	private void community(String ID2) {
		while (true) {
			try {

				int code = userType(ID2);
				// 거주민일때
				if (code == 1) {
					System.out.println("거주민 민원 게시판 ");
					System.out
							.println("1.민원글작성, 2.민원글수정, 3.민원글삭제,4.민원글조회 5.뒤로가기");
					Scanner sc = new Scanner(System.in);
					int input = sc.nextInt();
					switch (input) {
					case 1:
						// 민원글 작성
						writing(ID2);
						break;
					case 2:
						// 민원글 수정
						edit(ID2, code);
						break;
					case 3:
						// 민원글 삭제
						wDelete(ID2, code);
						break;

					case 4:
						// 민원글 조회
						writeList(ID2, code);
						break;

					case 5:
						// 뒤로가기
						return;
					default:
						System.out.println("잘못 눌렀습니다.");
						break;
					}
				}
				// 관리자
				else if (code == 2) {
					System.out.println("관리자 민원 게시판 ");
					System.out.println("1. 민원 답변 작성, 2.민원글삭제, 3.민원글 조회 4.뒤로가기");
					Scanner sc = new Scanner(System.in);
					int input = sc.nextInt();
					switch (input) {
					case 1:
						// 민원글 답변 작성
						comment(ID2, code);
						break;
					case 2:
						// 민원글 삭제
						wDelete(ID2, code);
						break;
					case 3:
						// 글 번호 확인
						writeList(ID2, code);
						break;
					case 4:
						// 뒤로가기
						return;
					default:
						System.out.println("잘못 눌렀습니다.");
						break;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("형식이 맞지않습니다.");
			}

		}
	}

	// 본인 글 조회하는것
	private void writeList(String ID2, int code) {
		if (code == 1) {
			service.writeList(ID2);

		} else if (code == 2) {
			service.mangerWriteList(ID2);
		}
	}

	// 2.1.2.1.민원글 작성(거주민)
	private void writing(String resident_id) {
		String writerSuggestions = writeSC();
		HashMap<String, String> params2 = new HashMap<>();
		params2.put("resident_id", resident_id);
		params2.put("writerSuggestions", writerSuggestions);

		service.writing2(params2);
	}

	// 2.1.2.2.민원글 수정(거주민)
	private void edit(String resident_id, int code) {
		System.out.println("민원글을 수정하는 페이지입니다");
		int myNum = writeCheck(resident_id, code);
		if (myNum != 0) {
			HashMap<String, String> params2 = new HashMap<>();
			String writerSuggestions = writeSC();
			params2.put("resident_id", resident_id);
			params2.put("writerSuggestions", writerSuggestions);
			params2.put("myNum", Integer.toString(myNum));
			service.writeEdit(params2);
		}

	}

	// 2.1.2.2.민원글 삭제(거주민)
	private void wDelete(String resident_id, int code) {
		System.out.println("민원글을 삭제하는 페이지입니다.");
		int myNum = writeCheck(resident_id, code);
		if (myNum != 0) {
			System.out.println("민원글이 삭제되었습니다.");
			service.wDelete(myNum);
		}

	}

	// 게시판에 글쓸떄 사용하는 메서드
	private String writeSC() {
		Scanner sc = new Scanner(System.in);
		System.out.println("민원 내용을 작성하세요.");
		String a = sc.nextLine();
		return a;
	}

	// 본인글 확인
	private int writeCheck(String iD2, int code) {
		while (true) {
			try {
				cv.getWriter_suggestions();
				Scanner sc = new Scanner(System.in);
				System.out.println("1.작성글 확인 2.뒤로가기");
				int input = sc.nextInt();
				switch (input) {
				case 1:
					return myWrite(iD2, code);
				case 2:
					return 0;
				default:
					System.out.println("잘 못 입력 되었습니다. ");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}
	}

	private int myWrite(String iD2, int code) {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				int input = 0;
				System.out.println("글  번호를 입력하세요");
				List list = new ArrayList();
				if (code == 1) {
					list = service.myWrite(iD2);
					System.out.println(list);
					input = sc.nextInt();
				} else if (code == 2) {
					list = service.ManagerWrite(iD2);
					System.out.println(list);
					input = sc.nextInt();
				}

				int listIndex = 0;
				for (int i = 0; i < list.size(); i++) {
					listIndex = (int) list.get(i);

					if (input == listIndex) {
						service.writeMine(input);
						return input;
					}
				}
				System.out.println("잘못된 글 번호 입니다.");

			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}
	}

	// 2.1.2.4.민원글 댓글 작성(관리자)
	private void comment(String ID2, int code) {
		cv.getWriter_repl();
		int input = myWrite(ID2, code);
		Scanner sc = new Scanner(System.in);
		System.out.println("답변을 입력해주세요.");
		String repl = sc.nextLine();
		HashMap<String, String> params = new HashMap<>();
		params.put("repl", repl);
		params.put("input", Integer.toString(input));
		params.put("ID2", ID2);

		service.writerepl(params);

	}

	// 2.1.3. 관리비 확인
	private void expenses() {
		while (true) {
			try {
				System.out.println("1.전기세, 2.수도세, 3.경비비, 4.뒤로가기");
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// 전기세 확인
					electrivity();
					break;
				case 2:
					// 수도세 확인
					water();
					break;
				case 3:
					// 경비비 확인
					guard();
					break;
				case 4:
					// 뒤로가기
					return;
				default:
					System.out.println("잘못 눌렀습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}
	}

	// 2.1.3.1. 전기세
	private void electrivity() {
		ev.getExpenses_electricity();
		service.elect(mb);
	}

	// 2.1.3.2. 수도세
	private void water() {
		ev.getExpenses_water();
		service.exWater(mb);
	}

	// 2.1.3.3. 경비비
	private void guard() {
		ev.getExpenses_guard();
		service.exGuard(mb);
	}

	// 2.1.4. 우편물 확인
	private void delivery(String resident_id) {
		while (true) {
			try {
				System.out.println("1.우편물 확인, 2.뒤로가기");
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// 우편함에 택배 여부 확인
					post_Check(resident_id);
					break;
				case 2:
					// 뒤로가기
					return;
				default:
					System.out.println("잘못 눌렀습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}
	}

	// 2.1.4.1. 우편함에 택배 여부 확인
	private void post_Check(String resident_id) {
		Map<String, String> params = new HashMap<>();
		params.put("resident_id", resident_id);
		service.postCheck(params);
	}

	private void page(String resident_id) {
		service.myPage(resident_id);

	}

	// 2.1.5 마이페이지
	private void myPage(String resident_id) {
		while (true) {
			try {
				page(resident_id);

				System.out.println("1.입주민정보 수정, 2.입주민정보 삭제, 3.차량등록, 4.뒤로가기");
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch (input) {
				case 1:

					// 입주민정보 수정
					citizenRevise(resident_id);
					break;
				case 2:
					// 입주민정보 삭제
					citizenDelete(resident_id);
					break;
				case 3:
					// 차량등록
					carAdd(resident_id);
					break;
				case 4:
					// 뒤로가기
					return;
				default:
					System.out.println("잘못 눌렀습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}

	}

	// 2.1.5.1 입주민 정보수정

	private void citizenRevise(String resident_id) {
		while (true) {
			try {

				System.out.println("1. 비밀번호수정, 2.이름 수정, 3.동호수변경, 4.취소");
				Scanner sc = new Scanner(System.in);
				Map<String, String> params = new HashMap<>();

				params.put("resident_id", resident_id);
				String input = sc.next();
				params.put("input", input);
				switch (input) {
				case "1":
					String changePW = inputPW();
					params.put("inputPW", changePW);
					mb.setResident_pass(changePW);
					break;
				case "2":
					String changeNAME = inputNAME();
					params.put("inputName", changeNAME);
					break;
				case "3":
					int changeADDR = inputADDR();
					params.put("inputAddr", Integer.toString(changeADDR));
					break;
				case "4":
					return;
				default:
					break;
				}
				service.citizenRevise(params);
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}

		}

	}

	// 2.1.5.2 입주민 정보 삭제

	private void citizenDelete(String resident_id) {
		try {

			Scanner sc = new Scanner(System.in);
			Map<String, String> params = new HashMap<>();
			String userpw = mb.getResident_pass();
			String deletePW = inputPW();
			params.put("resident_id", resident_id);

			if (deletePW.equals(userpw)) {
				System.out.println("확인되셨습니다. 탈퇴하시겠습니까?");
				System.out.println("1. 예, 2. 아니오");

				String input = sc.next();

				switch (input) {
				case "1":
					service.delete(params);
					start();
					break;

				case "2":
					System.out.println("취소하셨습니다.");
					break;

				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
				}

			} else {
				System.out.println("비밀번호가 틀리셨습니다. 다시 입력해주세요.");

			}
		} catch (InputMismatchException e) {
			System.out.println("형식에 맞지않습니다.");
		}

	}

	// 2.1.5.3.입주민 차량 추가등록

	private void carAdd(String resident_id) {
		boolean cars = false;
		System.out.println("입주민 차량 추가등록");
		do {
			int carnum = inputCarNum();
			car.setCar_num(carnum);
			pk.setCar_num(carnum);
			System.out.println(carnum);
			cars = carCheak();

		} while (cars == true);

		String cartype = inputCarType();
		car.setCar_type(cartype);

		int addr = mb.getAddr_num();
		car.setAddr_num(addr);
		pk.setParking_addr(addr);
		System.out.println(addr);

		String owner = mb.getResident_name();
		car.setCar_owner(owner);

		service.carAdd(car);
	
		System.out.println("차량등록이 완료 되었습니다.");
	}

	// 주차
	private int parkadd() {
		while (true) {
			try {
				car_Loc(pk);
				Scanner sc = new Scanner(System.in);
				int addr = mb.getAddr_num();
				ArrayList<Integer> list = new ArrayList<>();
				ArrayList<Integer> list2 = new ArrayList<>();
				ArrayList<Integer> list3 = new ArrayList<>();
			
				pk.setParking_addr(addr);
				list = service.myCarNum(addr);
				list2 = service.parkingCar();
				int c = 0, b = 0;
				boolean check = false; //같은차가 있으면 트루
				for (int i = 0; i < list.size(); i++) {
					check = false;
					c = list.get(i);
					for (int j = 0; j < list2.size(); j++) {
						b = list2.get(j);
							if (c == b) {
								check = true;
							}
					}
					if(check == false){
						list3.add(c);
					}
				}
				
				System.out.println("주차 가능 차량 : " + list3);
				System.out.println("본인의 차량 번호를 입력해주세요.");
				int carnum = sc.nextInt();
				for (int i = 0; i < list3.size(); i++) {
					int d = list3.get(i);
					if (d == carnum) {
						pk.setCar_num(carnum);

						System.out.println("주차장 번호를 입력해주세요");
						int parkinginput = sc.nextInt();
//						Pattern p = Pattern
//								.compile("^[1-2][0-2][0-9]||[1][3][0]||[2][3][0]$");
//						Matcher m = p.matcher(Integer.toString(parkinginput));
//						if (m.matches()) {
							pk.setParking_loc(parkinginput);
							System.out.println(parkinginput);
							int parkadd = service.parkadd(pk);
							return parkadd;
//						}

					} else {
						System.out.println("주차가능 차량이 아닙니다");
						return 0;
					}
				}
			}
			 catch (InputMismatchException e) {
				System.out.println("잘못된 형식입니다.");
			}
		}
	}

	// 차 종류 입력
	private String inputCarType() {
		while (true) {

			Scanner sc = new Scanner(System.in);

			System.out.println("차량명을 입력해주세요 ex)펠리세이드 ");
			String carType = sc.nextLine();
			Pattern p = Pattern.compile("[가-힣0-9a-zA-Z]*");
			Matcher m = p.matcher(carType);
			if (m.matches()) {
				return carType;
			}
			System.out.println("차량명을 다시 입력해주세요.");
		}
	}

	// 차량 번호 중복 체크
	private boolean carCheak() {
		while (true) {
			boolean carCheak = service.carCheak(car);
			if (carCheak == true) {
				System.out.println("이미 등록된 차량 번호입니다.");
				return carCheak;
			} else {
				System.out.println("차량등록 가능한 번호입니다.");
				return carCheak;

			}
		}

	}

	// 차 번호 입력
	private int inputCarNum() {
		while (true) {
			try {

				Scanner sc = new Scanner(System.in);
				System.out.println("차량 번호 숫자 4자리를 입력해주세요");
				System.out.println("없을시 0000");
				int carNum = sc.nextInt();
				Pattern p = Pattern.compile("[0-9]{4}");
				Matcher m = p.matcher(Integer.toString(carNum));
				if (m.matches()) {

					return carNum;
				} else if (carNum == 0000) {
					return carNum;
				}
				System.out.println("차번호를 다시 입력하세요");
			} catch (InputMismatchException e) {
				System.out.println("잘못된 형식입니다.");
			}
		}
	}

	// 3. 방문객
	private void visit_Log() {
		String guestNAME = inputNAME();
		gv.setGuest_name(guestNAME);
		int guestADDR = inputADDR();
		gv.setAddr_num(guestADDR);
		int guest_carNum = inputCarNum();
		gv.setGuest_carnum(guest_carNum);
		service.guestLog(gv);
	}

	// 4. 관리자 로그인
	private void manager_Login() {
		String manager_id = inputID();
		String manager_pass = inputPW();
		Map<String, String> params = new HashMap<>();
		params.put("manager_id", manager_id);
		params.put("manager_pass", manager_pass);

		managerlogin = service.managerLogin(params);

		if (managerlogin == null) {
			System.out.println("정보가 없습니다");

		} else {
			System.out.println("관리자님, 어서오세요");
			manager(manager_id, manager_pass);
		}
		// 로그인 구현 후 관리자 모드

	}

	// 4.1 관리자 모드
	private void manager(String managerID, String manager_pass) {
		while (true) {
			try {

				System.out.println("1.민원게시판 2. 방문객 정보  3.뒤로가기  ");
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// 민원 게시판
					community(managerID);
					break;
				case 2:
					// 방문객이 방문지 어디 인지
					whereVisit();
					break;
				case 3:
					// 뒤로가기
					return;
				default:
					System.out.println("잘못 눌렀습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("형식에 맞지않습니다.");
			}
		}
	}

	// 방문객이 방문지 어디 인지
	private void whereVisit() {
		visitList();
		String VisitName = inputNAME();
		String visitAddr = service.whereVisit(VisitName);
		if (visitAddr != null) {
			System.out.println(VisitName + "님은 " + visitAddr + " 동/호수 에 있습니다.");
		} else {
			System.out.println("그런 사람은 우리 아파트에 방문하지 않았습니다.");
		}

	}

	// 방문객의 이름을 전부 출력
	private void visitList() {
		ArrayList<String> vList = new ArrayList<>();
		vList = service.visitList();
		System.out.println(vList);

	}
}
