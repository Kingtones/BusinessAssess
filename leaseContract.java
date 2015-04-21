import java.text.ParseException;  
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class leaseContract {
	private static int loanTime=180;
	public static int interval;//��̬��������ֵ��ʾ����ʱ�������׼����ʱ�����Ĳ�ֵ
	public double borrowedCredit;//����Ľ���Ӧ�ջص��ʿ�
	public double repayCredit;//���ջص��ʿ�
	
	public String borrowDate;//�������
	public String repayDate;//��������
		
	
	public int id;//��ͬ�ı��
	
	
	
	/**
	 * ���޺�ͬ��Ĺ��췽��
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
	 * �ú����ж��Ƿ��ѻ������
	 * @param borrowedCredit
	 * @param recivedCredit
	 * @return boolean
	 */
	public boolean isRetire(double borrowedCredit,double repayCredit){
		if((borrowedCredit-repayCredit)<0.0000001) return true;
		else return false;
	}
	
	
	/**
	 * �ú�������ҵ��Ա�õ��Ľ�����ͷ��Ľ��
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
	
