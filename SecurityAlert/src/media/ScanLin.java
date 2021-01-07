/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ScanLin extends Thread{
    private String scanFile = "";
    private String username;
    int pos = 0;
    int ps;
    String Admin;
    boolean a = true;
    String id = "";
    /**
     *
     * @throws Exception
     */
    @Override
    public void run(){
    	try {
    		String st ;
    		String [] cmd2 ={"./scripts/countTel.sh",username}; 
    		Process q2 = Runtime.getRuntime().exec(cmd2);
			/*BufferedReader z = new BufferedReader(new FileReader("./ids/idLin.txt"));
			while((st = z.readLine())!=null) {
				String [] tp = st.split("=");
				if(username.equals(tp[0]))
					{if(tp.length==2)
						id=tp[1];
					else
						id="";
					}
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	while(a) {  
            String [] cmd ={"./scripts/createUsersLog.sh",username};
            try {
                Process q = Runtime.getRuntime().exec(cmd);
                
            } catch (IOException ex) {
                Logger.getLogger(ScanLin.class.getName()).log(Level.SEVERE, null, ex);
            }
            File f = new File("./position/"+username+"pos");
            if(f.exists() && !f.isDirectory()) { 
                //check if position file existes
            try {
            	BufferedReader num = new BufferedReader(new FileReader("./position/"+username+"pos"));
            pos = Integer.parseInt(num.readLine());
            num.close();
            } catch (Exception ex) {
            Logger.getLogger(ScanLin.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            int i;
            String str = "";
            /*
            File fi = new File("./programL/terminal/mots.txt");
            if(fi.length()==0){
                JOptionPane.showMessageDialog(null, "Pas de mots\nAjoutez des mots avant de démarrer!!");
                break;
            }*/
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader("./programL/terminal/mots.txt"));
                i=0;
                while((str = reader.readLine()) != null){
                ps = pos;
                BufferedReader read = new BufferedReader(new FileReader(scanFile));
                String line;
                for (int j = 1; j <= ps; j++)
                    read.readLine();
                while((line = read.readLine()) != null){                	
                	if(line.contains(str)) {
                		BufferedReader r = new BufferedReader(new FileReader("./analyseLin/count/"+username));
                		String sr = r.readLine();
                		if(!(sr.equals("3"))) {
                		System.out.println("commande détecté "+line+" à l'utilisateur : "+username);
                		sendMessage(str);}
                		
                	}
                	System.out.println(line);
                        ps++;
                	}
                }
            	}catch (Exception ex) {
            		Logger.getLogger(ScanLin.class.getName()).log(Level.SEVERE, null, ex);
            	}	
            	try {
            		Thread.sleep(500);
            	} catch (InterruptedException ex) {
            		Logger.getLogger(ScanLin.class.getName()).log(Level.SEVERE, null, ex);
            	}
            	pos = ps;
            	try {
            		BufferedWriter write = new BufferedWriter(new FileWriter("./position/"+username+"pos"));
            		write.write(pos+"");
            		write.close();
            	} catch (Exception ex) {
            		Logger.getLogger(ScanLin.class.getName()).log(Level.SEVERE, null, ex);
            	}
		}
    	}
	
	public void sendMessage(String str) throws Exception{	
		/*BufferedReader read = new BufferedReader(new FileReader("./analyseLin/count/"+username));
		int sr = Integer.parseInt(read.readLine());
		if(!(sr==3)) {*/
		BufferedReader z = new BufferedReader(new FileReader("./ids/idLin.txt"));
		String st;
		while((st = z.readLine())!=null) {
			String [] tp = st.split("=");
			if(username.equals(tp[0]))
				{if(tp.length==2)
					id=tp[1];
				else
					id="";
				}
		}
		if(id.equals("")) {
			String [] cmd = {"python","AlertAdmin.py",str,username,Admin};
			Runtime.getRuntime().exec(cmd);}
		else
		{
			String [] cmd = {"python","AlertUser.py",str,username,id,Admin};
			Runtime.getRuntime().exec(cmd);
		}
		//}
		//else
			//System.out.println("Bot est Arrêté....");
	}
        public void setScanfile(String str){
            this.scanFile="./analyseLin/"+str;
        }
        public void setUserName(String str){
            this.username=str;
        }
        public void setAdmin(String str){
            this.Admin=str;
        }
	}
        