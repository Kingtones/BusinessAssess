
public class Salesman {
	public double income=0.0;
	public double reward=0.0001;//提前收账的奖励率
	public double penalty=0.00008;//延期收账的惩罚利率
	public double pushMoney=0.01;//提成率
	public int employeeNo;//业务员编号
	
	leaseContract lc=new leaseContract();
	AssessClient ac=new AssessClient();
	
	/**
	 * 业务员类的构造方法
	 * @param employeeNo
	 * @param lc
	 * @param ac
	 */
	public Salesman(int employeeNo, leaseContract lc, AssessClient ac) {
		this.employeeNo = employeeNo;
		this.lc = lc;
		this.ac = ac;
	}

	public Salesman() {
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * 业务员收入的计算方法
	 * @return income
	 */
	public double income(){
		for(int i=0;i<ac.ID.size();i++){
			if(lc.isRetire(ac.borrowCredit.get(i), ac.repayCredit.get(i))){
				income=income+pushMoney*ac.repayCredit.get(i)+lc.isReward(ac.borrowDate.get(i), ac.repayDate.get(i));
			}else {
				income=income+lc.isReward(ac.borrowDate.get(i), ac.repayDate.get(i));
			}
			
		}
		return income;
	}
	
}
