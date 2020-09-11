package LastApt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.b;

public class IDaoimpl implements IDao {
	// 입주민 로그인
	@Override
	public String residentlog_In(Map<String, String> params) {

		String resident_id = params.get("resident_id");
		String resident_pass = params.get("resident_pass");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int as = 1;

		String logIn_ID = null;
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();

			String sql = "SELECT RESIDENT_ID " + "FROM  RESIDENT "
					+ "WHERE RESIDENT_ID ='" + resident_id
					+ "'AND RESIDENT_PASS ='" + resident_pass
					+ "'AND RESIDENT_CODE ='" + as + "'";

			rs = st.executeQuery(sql);

			while (rs.next()) {
				logIn_ID = rs.getString("RESIDENT_ID");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return logIn_ID;
	}

	// 입주민 회원가입
	@Override
	public int createCitizen(ResidentVO mb) {
		String resident_id = mb.getResident_id();
		String resident_pass = mb.getResident_pass();
		String resident_name = mb.getResident_name();
		int resident_addr = mb.getAddr_num();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		int updateCount = 0;
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();

			String sql = " INSERT INTO RESIDENT (RESIDENT_ID,RESIDENT_PASS,RESIDENT_NAME,ADDR_NUM) "
					+ " VALUES ('"
					+ resident_id
					+ "','"
					+ resident_pass
					+ "','" + resident_name + "','" + resident_addr + "')";

			updateCount = st.executeUpdate(sql);
			if (updateCount == 1) {
				System.out.println("성공적으로 회원 가입완료 하였습니다.");

			} else {
				System.out.println("가입 실패하였습니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return updateCount;

	}

	// 입주민 정보수정
	@Override
	public int CitizenRevise(Map<String, String> params) {
		String resident_id = params.get("resident_id");
		String inputPW = params.get("inputPW");
		String inputName = params.get("inputName");
		String inputAddr = params.get("inputAddr");
		String input = params.get("input");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql;

			/**
			 * @author 전진원
			 * @since 2020.09.07
			 * @see 입주민이 마이페이지에서 입주민정보 수정을 보내면 비밀번호 이름 동후수 중에 고르고 값을 수정해주는 쿼리문
			 */
			switch (input) {
			case "1":
				sql = "UPDATE RESIDENT " + " SET RESIDENT_PASS = '" + inputPW
						+ "'" + " WHERE RESIDENT_ID = '" + resident_id + "'";
				st.executeUpdate(sql);
				break;
			case "2":
				sql = "UPDATE RESIDENT " + " SET RESIDENT_NAME = '" + inputName
						+ "'" + " WHERE RESIDENT_ID = '" + resident_id + "'";

				st.executeUpdate(sql);
				break;
			case "3":
				sql = "UPDATE RESIDENT " + " SET ADDR_NUM = '" + inputAddr
						+ "'" + " WHERE RESIDENT_ID = '" + resident_id + "'";

				st.executeUpdate(sql);
				break;

			default:
				System.out.println("취소");
				break;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return 0;
	}

	// 차량등록
	@Override
	public int carAdd(CarVO car) {
		int updatecount = 0;
		int carnum = car.getCar_num();
		String owner = car.getCar_owner();
		String cartype = car.getCar_type();
		int addr = car.getAddr_num();

		// System.out.println(carnum);
		// System.out.println(owner);
		// System.out.println(cartype);
		// System.out.println(addr);
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = " INSERT INTO CAR (CAR_NUM,CAR_OWNER,CAR_TYPE,ADDR_NUM) "
					+ " VALUES ('"
					+ carnum
					+ "','"
					+ owner
					+ "','"
					+ cartype
					+ "','" + addr + "') ";
			st.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return updatecount;
	}

	// 입주민 정보 삭제
	@Override
	public int delete(Map<String, String> params) {
		String resident_id = params.get("resident_id");
		String inputPW = params.get("inputPW");
		String input = params.get("input");
		int updatecount = 0;

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "UPDATE RESIDENT " + " SET RESIDENT_CODE = '0'"
					+ " WHERE RESIDENT_ID = '" + resident_id + "'";
			st.executeUpdate(sql);
			if (updatecount != 1) {
				System.out.println("삭제가 완료 되었습니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return updatecount;

	}

	// 입주민 아이디 중복체크
	@Override
	public boolean idcheak(ResidentVO mb) {
		boolean result = false;

		String resident_id = mb.getResident_id();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
			String sql = "SELECT RESIDENT_ID FROM RESIDENT WHERE RESIDENT_ID ='"
					+ resident_id + "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return result;
	}

	// 관리자 회원가입
	@Override
	public int createManger(ManagerVO mg) {
		String manager_id = mg.getManager_id();
		String manager_pass = mg.getManager_pass();
		String manager_name = mg.getMamnager_name();
		String manager_hp = mg.getManager_hp();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		int updateCount = 0;
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();

			String sql = " INSERT INTO MANAGER (MANAGER_ID,MANAGER_PASS,MANAGER_NAME,MANAGER_HP) "
					+ " VALUES ('"
					+ manager_id
					+ "','"
					+ manager_pass
					+ "','"
					+ manager_name + "','" + manager_hp + "')";

			updateCount = st.executeUpdate(sql);
			if (updateCount == 1) {
				System.out.println("성공적으로 회원 가입완료 하였습니다.");

			} else {
				System.out.println("가입 실패하였습니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return updateCount;

	}

	@Override
	public String carLoc(CarVO car) {
		return null;
	}

	@Override
	public int write(CommunityVO cv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int elect(ResidentVO mb) {
	  int useraddr = mb.getAddr_num();
	  String name = mb.getResident_name();
	      Connection conn = null;
	      Statement st = null;
	      ResultSet rs = null;

	      int elec = 0;
	      
	      try {
	         // 1.드라이버로딩
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         // 2 접속
	         String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
	         String id = "APT2";
	         String pw = "java";

	         conn = DriverManager.getConnection(url, id, pw);
	         // 3.질의 및 결과실행
	         st = conn.createStatement();

	         String sql = "SELECT EXPENSES_ELECTRICITY FROM EXPENSES WHERE ADDR_NUM= '"+ useraddr + "'";
	         rs = st.executeQuery(sql);

	         
	         while(rs.next()){
	            System.out.println(name+"님"+useraddr+"동\n 이번달 전기 요금은 :"+rs.getInt("EXPENSES_ELECTRICITY"));
	         }
	         
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩 하지 못하였습니다.");

	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("접속을 실패하였습니다.");
	      } finally {
	         try {
	            if (rs != null) {
	               rs.close();
	            }
	            if (st != null) {
	               st.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }

	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환 실패");
	         }

	      }
	      return elec;
	}

	@Override
	public int writeedit(CommunityVO cv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int writedelete(int input) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int writerepl(Map<String, String> params) {
		String repl = params.get("repl");
		String a= params.get("input");
		int input = Integer.parseInt(a);
		String ID2 = params.get("ID2");
		
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "UPDATE  COMMUNITY "
					+ " SET WRITER_REPL = '"+repl+ "',"
					+ " MANAGER_ID = '"+ID2+ "'"
					+ " WHERE WRITER_NUM = '"+input+ "'";
							
			st.executeUpdate(sql);
			System.out.println("답글을 작성하였습니다.");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return 0;
	}

	//수도세
			@Override
			public int exWater(ResidentVO mb) {
				 int useraddr = mb.getAddr_num();
				  String name = mb.getResident_name();
				      Connection conn = null;
				      Statement st = null;
				      ResultSet rs = null;

				      int elec = 0;
				      
				      try {
				         // 1.드라이버로딩
				         Class.forName("oracle.jdbc.driver.OracleDriver");
				         // 2 접속
				         String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
				         String id = "APT2";
				         String pw = "java";

				         conn = DriverManager.getConnection(url, id, pw);
				         // 3.질의 및 결과실행
				         st = conn.createStatement();

				         String sql = "SELECT EXPENSES_WATER FROM EXPENSES WHERE ADDR_NUM= '"+ useraddr + "'";
				         rs = st.executeQuery(sql);

				         
				         while(rs.next()){
				            System.out.println(name+"님"+"("+useraddr+"동"+")"+" 이번달 전기 요금은 : "+rs.getInt("EXPENSES_WATER")+"원 입니다.");
				         }
				         
				      } catch (ClassNotFoundException e) {
				         e.printStackTrace();
				         System.out.println("드라이버 로딩 하지 못하였습니다.");

				      } catch (SQLException e) {
				         e.printStackTrace();
				         System.out.println("접속을 실패하였습니다.");
				      } finally {
				         try {
				            if (rs != null) {
				               rs.close();
				            }
				            if (st != null) {
				               st.close();
				            }
				            if (conn != null) {
				               conn.close();
				            }

				         } catch (SQLException e) {
				            e.printStackTrace();
				            System.out.println("반환 실패");
				         }

				      }
				      return elec;
				
			}

			//경비비
			@Override
			public int exGuard(ResidentVO mb) {
				int useraddr = mb.getAddr_num();
				  String name = mb.getResident_name();
				      Connection conn = null;
				      Statement st = null;
				      ResultSet rs = null;

				      int elec = 0;
				      
				      try {
				         // 1.드라이버로딩
				         Class.forName("oracle.jdbc.driver.OracleDriver");
				         // 2 접속
				         String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
				         String id = "APT2";
				         String pw = "java";

				         conn = DriverManager.getConnection(url, id, pw);
				         // 3.질의 및 결과실행
				         st = conn.createStatement();

				         String sql = "SELECT EXPENSES_GUARD FROM EXPENSES WHERE ADDR_NUM= '"+ useraddr + "'";
				         rs = st.executeQuery(sql);

				         
				         while(rs.next()){
				            System.out.println(name+"님"+"("+useraddr+"동"+")"+" 이번달 전기 요금은 : "+rs.getInt("EXPENSES_GUARD")+"원 입니다.");
				         }
				         
				      } catch (ClassNotFoundException e) {
				         e.printStackTrace();
				         System.out.println("드라이버 로딩 하지 못하였습니다.");

				      } catch (SQLException e) {
				         e.printStackTrace();
				         System.out.println("접속을 실패하였습니다.");
				      } finally {
				         try {
				            if (rs != null) {
				               rs.close();
				            }
				            if (st != null) {
				               st.close();
				            }
				            if (conn != null) {
				               conn.close();
				            }

				         } catch (SQLException e) {
				            e.printStackTrace();
				            System.out.println("반환 실패");
				         }

				      }
				      return elec;
		}

	// 회원정보 페이지
	@Override
	public String mypage(String resident_id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String mypage = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
			String sql = "SELECT * FROM RESIDENT WHERE RESIDENT_ID ='"
					+ resident_id + "'";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out
						.println("==========================================");
				System.out
						.println(rs.getString("RESIDENT_NAME") + "님의 정보 입니다.");
				System.out.println("아이디: " + rs.getString("RESIDENT_ID"));
				System.out.println("비밀번호 : " + rs.getString("RESIDENT_PASS"));
				System.out.println("이름: " + rs.getString("RESIDENT_NAME"));
				System.out.println("동/호수: " + rs.getString("ADDR_NUM"));
				System.out
						.println("==========================================");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return mypage;
	}

	//글작성(end_jeon)
		@Override
		public int writing2(HashMap<String, String> params2) {
			String resident_id = params2.get("resident_id");
			String writerSuggestions = params2.get("writerSuggestions");
			
		

			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;

			try {
				// 1.드라이버로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// 2 접속
				String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
				String id = "APT2";
				String pw = "java";

				int max = 1;
				
				conn = DriverManager.getConnection(url, id, pw);
				// 3.질의 및 결과실행
				st = conn.createStatement();

				String maxSql = "SELECT WRITER_NUM"
						+ "      FROM   ("
						+ "              SELECT WRITER_NUM"
						+ "              FROM   COMMUNITY"
						+ "              ORDER BY WRITER_NUM DESC"
						+ "		 		) "
						+ "      WHERE  ROWNUM = 1";
				rs=st.executeQuery(maxSql);
				while(rs.next()){
					max = rs.getInt("WRITER_NUM")+1;
				}
				System.out.println(max);
				String sql = "INSERT INTO COMMUNITY (RESIDENT_ID ,WRITER_SUGGESTIONS, WRITER_DATE, WRITER_NUM ) "
						+ " VALUES ('"+resident_id+"','"+ writerSuggestions+"', SYSDATE, "+max+") ";
				st.executeUpdate(sql);

				//SELECT COUNT(컬럼명) FROM 테이블명;
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("드라이버 로딩 하지 못하였습니다.");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("접속을 실패하였습니다.");
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (conn != null) {
						conn.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("반환 실패");
				}

			}
			return 0;

		}

	// 우편물 확인(
	@Override
	public String postCheck(Map<String, String> params) {
		String resident_id = params.get("resident_id");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();

			String sql = "SELECT A.POST_NAME " + " FROM POST A, RESIDENT B "
					+ " WHERE A.ADDR_NUM = B.ADDR_NUM "
					+ " AND B.RESIDENT_ID = '" + resident_id + "'";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String a = rs.getString("POST_NAME");
				if (a == null) {
					System.out
							.println("──────────────────────────────────────");
					System.out.println("\t배송온 택배가 없습니다.");
					System.out
							.println("───────────────────────────────────────");
				} else {
					System.out.println(a + " 상품이 택배함에 배송왔습니다");
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return null;
	}

	//글 수정(end_jeon)
			@Override
			public int writeEdit(HashMap<String, String> params2) {
				String resident_id = params2.get("resident_id");
				String writerSuggestions = params2.get("writerSuggestions");
				String a= params2.get("myNum");
				int myNum = Integer.parseInt(a);
				
				
				Connection conn = null;
				Statement st = null;
				ResultSet rs = null;

				try {
					// 1.드라이버로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					// 2 접속
					String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
					String id = "APT2";
					String pw = "java";

					conn = DriverManager.getConnection(url, id, pw);
					// 3.질의 및 결과실행
					st = conn.createStatement();
					String sql = "UPDATE  COMMUNITY "
							+ " SET WRITER_SUGGESTIONS = '"+writerSuggestions+"'"
							+ " WHERE RESIDENT_ID = '"+resident_id+"'"
									+ " AND WRITER_NUM = '"+myNum+"'";
					st.executeUpdate(sql);
					System.out.println("수정이 완료되었습니다.");

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					System.out.println("드라이버 로딩 하지 못하였습니다.");

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("접속을 실패하였습니다.");
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (st != null) {
							st.close();
						}
						if (conn != null) {
							conn.close();
						}

					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("반환 실패");
					}

				}
				return 0;
		}


	// 관리자아이디 중복 체크
	@Override
	public boolean midcheck(ManagerVO mg) {
		boolean result = false;

		String manager_id = mg.getManager_id();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
			String sql = "SELECT MANAGER_ID FROM MANAGER WHERE MANAGER_ID ='"
					+ manager_id + "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return result;
	}

	// 관리자 로그인
	@Override
	public String managerLogin(Map<String, String> params) {
		String manager_id = params.get("manager_id");
		String manager_pass = params.get("manager_pass");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String managerlogin = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();

			String sql = "SELECT MANAGER_ID " + "FROM  MANAGER "
					+ "WHERE MANAGER_ID ='" + manager_id
					+ "'AND MANAGER_PASS ='" + manager_pass + "'";

			// String sql = "SELECT A.RESIDENT_ID, B.MANAGER_ID "
			// + "FROM  RESIDENT A, MANAGER B "
			// + " WHERE A.RESIDENT_ID =" +"'"+resident_id+"'"
			// +"AND B.MANAGER_ID = " +"'"+manager_id+"'";

			rs = st.executeQuery(sql);

			while (rs.next()) {
				managerlogin = rs.getString("MANAGER_ID");

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return managerlogin;
	}

	@Override
	public String residentList(ResidentVO mb) {
		return null;
	}

	@Override
	public int residentDel(ResidentVO mb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int residentCorrect(ResidentVO mb) {
		// TODO Auto-generated method stub
		return 0;
	}




	//게시판 접속시 유저 타입 구별해주는 것 (end_jeon)
	@Override
	public int userType(String ID2) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int a =1;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "SELECT MANAGER_CODE " + " FROM MANAGER   "
						+ " WHERE MANAGER_ID = '" + ID2 + "'";
						
			rs = st.executeQuery(sql);
			while (rs.next()) {
				a = Integer.parseInt(rs.getString("MANAGER_CODE"));
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return a;
	}

	@Override
	public String carnum(int addr_num) {
		// TODO Auto-generated method stub
		return null;
	}

	//게시판의 글번호 받아오는 것 (end_jeon)
		@Override
		public List myWrite(String iD2) {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			ArrayList list = new ArrayList();

			try {
				// 1.드라이버로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// 2 접속
				String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
				String id = "APT2";
				String pw = "java";
				conn = DriverManager.getConnection(url, id, pw);
				// 3.질의 및 결과실행
				st = conn.createStatement();
				String sql = "SELECT WRITER_NUM " + " FROM COMMUNITY "
						+ " WHERE RESIDENT_ID = '" + iD2 + "'";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					list.add(rs.getInt("WRITER_NUM"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("드라이버 로딩 하지 못하였습니다.");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("접속을 실패하였습니다.");
			} finally {
				try {

					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (conn != null) {
						conn.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("반환 실패");
				}

			}

			return list;
		}

	// 회원 동호수
	@Override
	public int useraddr(String resident_id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String mypage = null;
		int addr = 0;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
			String sql = "SELECT ADDR_NUM FROM RESIDENT WHERE RESIDENT_ID ='"
					+ resident_id + "'";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				addr = rs.getInt("ADDR_NUM");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return addr;
	}
	//회원정보 이름
	@Override
	public String username(String resident_id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String mypage = null;
		String name = "";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
			String sql = "SELECT RESIDENT_NAME FROM RESIDENT WHERE RESIDENT_ID ='"
					+ resident_id + "'";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				name = rs.getString("RESIDENT_NAME");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return name;

	}

	// 게시판의 글 삭제해주는것 (end_jeon)
		@Override
		public int wDelete(int myNum) {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;

			try {
				// 1.드라이버로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// 2 접속
				String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
				String id = "APT2";
				String pw = "java";

				conn = DriverManager.getConnection(url, id, pw);
				// 3.질의 및 결과실행
				st = conn.createStatement();
				String sql = "UPDATE COMMUNITY "
						+ " SET WRITER_SUGGESTIONS = NULL, WRITER_REPL = NULL, RESIDENT_ID = NULL"
						+ " WHERE WRITER_NUM = '" + myNum + "'";
				st.executeUpdate(sql);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("드라이버 로딩 하지 못하였습니다.");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("접속을 실패하였습니다.");
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (conn != null) {
						conn.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("반환 실패");
				}

			}
			return 0;

		}

	// 구현안됨
	@Override
	public int writeMine(int input) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			// String sql = "SELECT A.POST_NAME " + " FROM COMMUNITY "
			// + " WHERE A.ADDR_NUM = B.ADDR_NUM "
			// + " AND B.RESIDENT_ID = '" + resident_id + "'";
			// rs = st.executeQuery(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return 0;

	}

	//입주민 차량 정보
	@Override
	public String resiDentCar(ResidentVO mb) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String op = null;
		String Owner = mb.getResident_name();
		int addr = mb.getAddr_num();

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
			String sql = "SELECT CAR_NUM,CAR_TYPE FROM CAR WHERE CAR_OWNER ='"
					+ Owner + "'" + " AND  ADDR_NUM ='" + addr + "'";

			rs = st.executeQuery(sql);

			while (rs.next()) {

				System.out.println("차량번호:" + rs.getInt("CAR_NUM") + "\t 차량종류:"
						+ rs.getString("CAR_TYPE"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return op;

	}
	
	//자동차 중복체크
	@Override
	public boolean carCheak(CarVO car) {
		boolean result = false;

		int carnum = car.getCar_num();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
			String sql = "SELECT CAR_NUM FROM CAR WHERE CAR_NUM ='"
					+ carnum + "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return result;

	}
	//주차장 List
	@Override
	public ArrayList parkingList(ParkingVO pv) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList();

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String Sid = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, Sid, pw);
			st = conn.createStatement();
	//
			String sql = " SELECT PARKING_LOC,NVL(CAR_NUM,0) FROM PARKING ";
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				list.add(rs.getString("PARKING_LOC")+"번["+rs.getString("NVL(CAR_NUM,0)")+"].");
				
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return list;
	}

	@Override
	public int parkadd(ParkingVO pk) {
		int carnum =pk.getCar_num();
		int addr=pk.getParking_addr();
		int loc=pk.getParking_loc();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "UPDATE PARKING "
					+ " SET PARKING_ADDR = '"+addr+"', CAR_NUM = '"+carnum+"'WHERE PARKING_LOC='"+loc+"'";
			st.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return 0;
		
	}
	
	// 본인 글 내용 확인 하는 것 (end_jeon)
	@Override
	public String writeList(String resident_id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "SELECT WRITER_SUGGESTIONS, WRITER_NUM, WRITER_REPL FROM COMMUNITY WHERE RESIDENT_ID ='"
					+ resident_id + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String repl = rs.getString("WRITER_REPL");
				if(repl!=null){
					System.out.println("─────────────────────────────────────────────");
					System.out.println(rs.getString("WRITER_NUM") + "번글 : \t"
						+ rs.getString("WRITER_SUGGESTIONS")
						+"\n답변 :\t "+repl);
					System.out.println("─────────────────────────────────────────────");
				}else{
					System.out.println("─────────────────────────────────────────────");
					System.out.println(rs.getString("WRITER_NUM") + "번글 : \t"
							+ rs.getString("WRITER_SUGGESTIONS")
							+"\n\t 아직 관리자의 답변이 없습니다. ");
					System.out.println("─────────────────────────────────────────────");
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return null;
	}

	
	
	//관리자가 글번호 모두를 받아오는 것 (end_jeon)
	@Override
	public List ManagerWrite(String iD2) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "SELECT WRITER_NUM " + " FROM COMMUNITY "
					+ " WHERE RESIDENT_ID IS NOT NULL";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getInt("WRITER_NUM"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return list;
	}

	//관리자가 글내용 모두를 받아오는 것 (end_jeon)
	@Override
	public String mangerWriteList(String iD2) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "SELECT WRITER_SUGGESTIONS, WRITER_NUM FROM COMMUNITY "
					+ " WHERE WRITER_SUGGESTIONS IS NOT NULL"; 
					
			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("WRITER_NUM") + "번글 : \t"
						+ rs.getString("WRITER_SUGGESTIONS"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return null;
	}

	//방문객의 방명록을 등록해주는 것 
	@Override
	public int guestLog(GuestVO gv) {
		String guest_name = gv.getGuest_name();
		int guest_carNum = gv.getGuest_carnum();
		int addr_num = gv.getAddr_num();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		int updateCount = 0;
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
		
			String sql = "INSERT INTO GUEST(GUEST_CARNUM, GUEST_NAME, ADDR_NUM) "
					+ "VALUES ('"+guest_carNum+"','"+guest_name+"','"+addr_num+"')";

			st.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return 0;
		
	}
	//방문객이 어디에 있는지 찾아온다.
	@Override
	public String whereVisit(String visitName) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String visitAddr = null;
		
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "SELECT ADDR_NUM FROM GUEST "
					+ " WHERE GUEST_NAME = '" +visitName+ "'"; 
					
			rs = st.executeQuery(sql);
			while (rs.next()) {
				visitAddr = rs.getString("ADDR_NUM");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return visitAddr;
	}
	
	//방문객의 이름을 리스트로 전부 저장해서 반원 (end_jeon)
	   @Override
	   public ArrayList<String> visitList() {
	      ArrayList<String> vList = new ArrayList<>();
	      Connection conn = null;
	      Statement st = null;
	      ResultSet rs = null;

	      String visitAddr = null;
	      
	      try {
	         // 1.드라이버로딩
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         // 2 접속
	         String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
	         String id = "APT2";
	         String pw = "java";

	         conn = DriverManager.getConnection(url, id, pw);
	         // 3.질의 및 결과실행
	         st = conn.createStatement();
	         
	         
	         String sql = " SELECT GUEST_NAME FROM GUEST ";
	                 
	         rs = st.executeQuery(sql);
	         System.out.print("방문객 :  ");
	         while (rs.next()) {
	            vList.add(rs.getString("GUEST_NAME"));
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩 하지 못하였습니다.");

	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("접속을 실패하였습니다.");
	      } finally {
	         try {
	            if (rs != null) {
	               rs.close();
	            }
	            if (st != null) {
	               st.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }

	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환 실패");
	         }

	      }
	      return vList;
	   }

	// 출차
	@Override
	public int parkingOut(int input) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql;

			sql = "UPDATE PARKING " + " SET PARKING_ADDR = NULL, CAR_NUM = NULL  "
					+ " WHERE CAR_NUM = '" + input + "'";
			st.executeUpdate(sql);
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}

		return 0;
	}
	
	//내 동호수에 맞는 차번호 다 갖고온다.
	@Override
	public ArrayList<Integer> myCarNum(int addr) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		String visitAddr = null;
		
		ArrayList<Integer> list = new ArrayList<>();
		
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql = "SELECT CAR_NUM FROM CAR  "
					+ " WHERE ADDR_NUM = '" +addr+ "'"; 
			rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getInt("CAR_NUM"));
			}
		

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return list;
	}

	//주차된 차번호 모두 갖고오는 것
	@Override
	public ArrayList<Integer> parkingCar() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		String visitAddr = null;
		
		ArrayList<Integer> list = new ArrayList<>();
		
		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2 접속
			String url = "jdbc:oracle:thin:@192.168.137.1:1521/XE";
			String id = "APT2";
			String pw = "java";

			conn = DriverManager.getConnection(url, id, pw);
			// 3.질의 및 결과실행
			st = conn.createStatement();
			String sql ="SELECT CAR_NUM FROM PARKING WHERE CAR_NUM IS NOT NULL";		

			rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getInt("CAR_NUM"));
			}
		

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 하지 못하였습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속을 실패하였습니다.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}

		}
		return list;
	}

}
