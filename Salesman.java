
public class Salesman {
	public double income=0.0;
	public double reward=0.0001;//��ǰ���˵Ľ�����
	public double penalty=0.00008;//�������˵ĳͷ�����
	public double pushMoney=0.01;//�����
	public int employeeNo;//ҵ��Ա���
	
	leaseContract lc=new leaseContract();
	AssessClient ac=new AssessClient();
	
	/**
	 * ҵ��Ա��Ĺ��췽��
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
	 * ҵ��Ա����ļ��㷽��
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
