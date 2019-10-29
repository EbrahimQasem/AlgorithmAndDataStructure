/*
 * Ebrahim Qasem, pp1008, COSC 311, Fall 2019
 * 
 * URL: 
 */

import java.util.*;
public class Main {
	//static random # generator
	//static final Random r = new Random(97);
	
	public static void main(String[] args) {
		Experiment[] exp = new Experiment[2];
		int index;
		for(index=0; index<exp.length; index++) {
			//(customer arrivals/min, service requirement, number of servers)
//			exp[index] = new Experiment(2, 12, 1);
//			exp[index] = new Experiment(2, 12, 2);
//			exp[index] = new Experiment(2, 12, 4);
//			exp[index] = new Experiment(2, 12, 8);
//			exp[index] = new Experiment(0.25, 3, 1);
//			exp[index] = new Experiment(0.25, 3, 2);
			exp[index] = new Experiment(2.0, 3, 4);//for the experiment
//			exp[index] = new Experiment(0.25, 3, 8);
			exp[index] = new Experiment(2, 3, 4);//for printing
		}
		
		for(int i=0; i< exp.length; i++) {
			
			Queue<Customer> queue = new LinkedList<>();
			Customer[] server = new Customer[exp[i].numServers];
			int completed = 0;
			int inService = 0;
			int minWait = Integer.MAX_VALUE;
			int maxWait = 0;
			int totalWait = 0;
			double avgWait = 0;
			
			//1 Tick = one minute
			for(int j=0; j<20; j++) {
				
				//get customers using getPoissonRandom
				int incoming = getPoissonRandom(exp[i].arrival_rate);
				for(int k=0; k<incoming; k++) {
					queue.add(new Customer(j, getRandom(exp[i].maxservice_time)));
				}
				
				//Assign customers to servers
				for(int s=0;s<server.length;s++){
                    //if server is idle.
                    if(server[s]==null){
                    	if(queue.isEmpty()) continue;
                        server[s] = queue.poll();
                        server[s].intime = j;
                        server[s].waitingtime = j-server[s].arrivaltime;
                        minWait = Math.min(minWait, server[s].waitingtime);
                        maxWait = Math.max(maxWait, server[s].waitingtime);
                        totalWait += server[s].waitingtime;
                        inService++;
                    } 
                    else if(server[s].intime+server[s].servicetime==j){
                        server[s]  = null;
                        completed++;
                        inService--;
                        if(queue.isEmpty()) continue;
                        server[s] = queue.poll();
                        inService++;
                        server[s].intime = j;
                        server[s].waitingtime = j-server[s].arrivaltime;
                        minWait = Math.min(minWait, server[s].waitingtime);
                        maxWait = Math.max(maxWait, server[s].waitingtime);
                        totalWait += server[s].waitingtime;
                    }
				}
				
				
				avgWait = 1.0*totalWait/(completed+inService);
				
				//print ticks
				if(i== index-1){
                    System.out.println("Tick #: "+j);
                    System.out.println("       # Customers in service: "+inService);
                    System.out.println("       # Customers with	completed service: "+completed);
                    System.out.println("       # Customers in queue: "+queue.size());
                    System.out.println("       Total wait time: "+totalWait);
                    System.out.println("       wait time: "+minWait+", "+avgWait+", "+maxWait);
                }
			}
		}
		
	}
	
	//random number generator
	public static Random random() {
		Random r = new Random(97);
		return r;
	}
	
	
	//getRandom for service requirements
	public static int getRandom(int a){
        return (1+random().nextInt(a));
    }
	
	//Code given by Professor Haynes
	private static int getPoissonRandom(double mean){
		
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
           p = p * random().nextDouble();
           k++;
        } while (p > L);
        return k - 1;
    }
}

/*
 ****************************OUTPUT SAMPLE ****************************
Tick #: 0
       # Customers in service: 4
       # Customers with	completed service: 0
       # Customers in queue: 2
       Total wait time: 0
       wait time: 0, 0.0, 0
Tick #: 1
       # Customers in service: 4
       # Customers with	completed service: 0
       # Customers in queue: 8
       Total wait time: 0
       wait time: 0, 0.0, 0
Tick #: 2
       # Customers in service: 4
       # Customers with	completed service: 0
       # Customers in queue: 14
       Total wait time: 0
       wait time: 0, 0.0, 0
Tick #: 3
       # Customers in service: 4
       # Customers with	completed service: 4
       # Customers in queue: 16
       Total wait time: 10
       wait time: 0, 1.25, 3
Tick #: 4
       # Customers in service: 4
       # Customers with	completed service: 4
       # Customers in queue: 22
       Total wait time: 10
       wait time: 0, 1.25, 3
Tick #: 5
       # Customers in service: 4
       # Customers with	completed service: 4
       # Customers in queue: 28
       Total wait time: 10
       wait time: 0, 1.25, 3
Tick #: 6
       # Customers in service: 4
       # Customers with	completed service: 8
       # Customers in queue: 30
       Total wait time: 30
       wait time: 0, 2.5, 5
Tick #: 7
       # Customers in service: 4
       # Customers with	completed service: 8
       # Customers in queue: 36
       Total wait time: 30
       wait time: 0, 2.5, 5
Tick #: 8
       # Customers in service: 4
       # Customers with	completed service: 8
       # Customers in queue: 42
       Total wait time: 30
       wait time: 0, 2.5, 5
Tick #: 9
       # Customers in service: 4
       # Customers with	completed service: 12
       # Customers in queue: 44
       Total wait time: 58
       wait time: 0, 3.625, 7
Tick #: 10
       # Customers in service: 4
       # Customers with	completed service: 12
       # Customers in queue: 50
       Total wait time: 58
       wait time: 0, 3.625, 7
Tick #: 11
       # Customers in service: 4
       # Customers with	completed service: 12
       # Customers in queue: 56
       Total wait time: 58
       wait time: 0, 3.625, 7
Tick #: 12
       # Customers in service: 4
       # Customers with	completed service: 16
       # Customers in queue: 58
       Total wait time: 96
       wait time: 0, 4.8, 10
Tick #: 13
       # Customers in service: 4
       # Customers with	completed service: 16
       # Customers in queue: 64
       Total wait time: 96
       wait time: 0, 4.8, 10
Tick #: 14
       # Customers in service: 4
       # Customers with	completed service: 16
       # Customers in queue: 70
       Total wait time: 96
       wait time: 0, 4.8, 10
Tick #: 15
       # Customers in service: 4
       # Customers with	completed service: 20
       # Customers in queue: 72
       Total wait time: 144
       wait time: 0, 6.0, 12
Tick #: 16
       # Customers in service: 4
       # Customers with	completed service: 20
       # Customers in queue: 78
       Total wait time: 144
       wait time: 0, 6.0, 12
Tick #: 17
       # Customers in service: 4
       # Customers with	completed service: 20
       # Customers in queue: 84
       Total wait time: 144
       wait time: 0, 6.0, 12
Tick #: 18
       # Customers in service: 4
       # Customers with	completed service: 24
       # Customers in queue: 86
       Total wait time: 200
       wait time: 0, 7.142857142857143, 14
Tick #: 19
       # Customers in service: 4
       # Customers with	completed service: 24
       # Customers in queue: 92
       Total wait time: 200
       wait time: 0, 7.142857142857143, 14
 */
