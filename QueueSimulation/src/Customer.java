
public class Customer {
	
	int arrivaltime, waitingtime, servicetime, intime;
    public Customer(int a, int b){
        arrivaltime = a;
        waitingtime = 0;
        intime = a;
        servicetime = b;
    }
}
