import java.text.ParseException;  
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class leaseContract {
	private static int loanTime=180;
	public static int interval;//静态变量，数值表示还款时间间隔与标准收账时间间隔的差值
	public double borrowedCredit;//借出的金额，即应收回的帐款
	public double repayCredit;//已收回的帐款
	
	public String borrowDate;//借款日期
	public String repayDate;//还款日期
		
	
	public int id;//合同的编号
	
	
	
	/**
	 * 租赁合同类的构造方法
	 * @param borrowedCredit
	 * @param repayCredit
	 * @param id
	 * @param borrowDate
	 * @param repayDate
	 */
	
	public leaseContract(){
		
	}
	
	
	public leaseContract(int id,double borrowedCredit, double repayCredit,
			String borrowDate, String repayDate) {
		
		this.borrowedCredit = borrowedCredit;
		this.repayCredit = repayCredit;
		this.id = id;
		this.borrowDate = borrowDate;
		this.repayDate = repayDate;
	}

	
	/**
	 * 该函数判断是否已还清贷款
	 * @param borrowedCredit
	 * @param recivedCredit
	 * @return boolean
	 */
	public boolean isRetire(double borrowedCredit,double repayCredit){
		if((borrowedCredit-repayCredit)<0.0000001) return true;
		else return false;
	}
	
	
	/**
	 * 该函数计算业务员得到的奖励或惩罚的金额
	 * @param borrowDate
	 * @param repayDate
	 * @return reward
	 */
	public double isReward(String borrowDate,String repayDate){
		SimpleDateFormat date=new SimpleDateFormat("yyyy-mm-dd");
		long to = 0;  
        try {  
            to = date.parse(borrowDate).getTime();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        long from = 0;  
        try {  
            from = date.parse(repayDate).getTime();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        interval=loanTime-(int)((from - to) / (1000 * 60 * 60 * 24));
        
        double reward=0.0;
		if(interval>=0){
			reward=0.0001*interval;
		}
		else{
			reward=0.00008*interval;
		}
		return reward;
	}
       
}  
	
