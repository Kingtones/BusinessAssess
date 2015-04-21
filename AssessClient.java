import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;


public class AssessClient {

	/**
	 * ҵ�񿼺˵�����
	 * @param args
	 * created on 15-04-21
	 */
	
	public static String url="jdbc:oracle:"+"thin:@127.0.0.1:1521:XE";
	public static String userName="system";
	public static String passWord="oracle";
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Connection conn=null;
	public static Statement stmt=null;
	public static ResultSet rs=null;
	
	static leaseContract lc=new leaseContract();
	
	
	
	/**
	 * �洢���޺�ͬ��Ϣ����������
	 */
	List<String> borrowDate=new ArrayList<String>();
	List<String> repayDate=new ArrayList<String>();
	List<Double> borrowCredit=new ArrayList<Double>();
	List<Double> repayCredit=new ArrayList<Double>();
	List<Integer> ID=new ArrayList<Integer>();
	
	
	/**
	 * �����main����
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		AssessClient ac=new AssessClient();
		
		System.out.println("������ҵ��Ա��Ա����:");
		Scanner in=new Scanner(System.in);
		int employeeNo=in.nextInt();
		Salesman sm=new Salesman(employeeNo,lc,ac);
		connect();
		ac.getID(employeeNo);
		for(int i=0;i<ac.ID.size();i++){
			ac.getleaseContract(ac.ID.get(i));
		}
		
		System.out.println("Ա��["+employeeNo+"] ���µ������ǣ� "+sm.income());
	
		
	}
	/**
	 * ����oracle���ݿ�ľ�̬����
	 * ��������void
	 */
	
	public static void connect(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url, userName, passWord);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * �õ�ҵ��Ա������ĺ�ͬ��ŵķ���
	 * @param employeeNo
	 * @return Ϊ��
	 * ��������������idд�뵽ArrayList ID��
	 */
	public void getID(int employeeNo){
		try {
			String sql="select * from Salesman where employeeNo="+employeeNo;
			conn=DriverManager.getConnection(url, userName, passWord);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				this.ID.add(rs.getInt("id"));
				//System.out.println(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(stmt!=null){
					stmt.close();
					stmt=null;
				}
				if(conn!=null){
					conn.close();
					conn=null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	/**
	 * ����ͬ���������Ϣд����Ӧ����ķ���
	 * @param id
	 * ��id�����ݿ���м�����������Ϣд����Ӧ��ArrayList������ֵΪ��
	 */
	public void getleaseContract(int id){
		try {
			String sql="select * from leaseContract where id="+id;
			conn=DriverManager.getConnection(url, userName, passWord);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				this.borrowDate.add(rs.getString("borrowDate").substring(0,10));
				if(rs.getString("repayDate")!=null){
					leaseContract.isRepay=true;
					this.repayDate.add(rs.getString("repayDate").substring(0,10));
				}else {
					this.repayDate.add(df.format(new Date()));
				}
				this.borrowCredit.add(rs.getDouble("borrowCredit"));
				if(rs.getDouble("repayCredit")!=0.0){
					this.repayCredit.add(rs.getDouble("repayCredit"));
				}else{
					this.repayCredit.add(0.0);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(stmt!=null){
					stmt.close();
					stmt=null;
				}
				if(conn!=null){
					conn.close();
					conn=null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	} 
	
	
}
